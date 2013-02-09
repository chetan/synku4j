package org.synku4j.wbxml.decoder;
/*
 * Copyright (C) 2009 Jools Enticknap
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.synku4j.wbxml.core.WbxmlCodePageField;
import org.synku4j.wbxml.core.WbxmlCodePageFinder;
import org.synku4j.wbxml.core.WbxmlConstants;
import org.synku4j.wbxml.core.impl.DefaultWbxmlCodePageField;
import org.synku4j.wbxml.decoder.event.WbxmlEvent;
import org.synku4j.wbxml.decoder.event.WbxmlEvent.EventType;

/**
 * @author Jools Enticknap
 */
public class WbxmlDecoder {

	private static final Log log = LogFactory.getLog(WbxmlDecoder.class);
	
	private final byte[] stream;
	private int offset;
	private byte[] stringTable;

	private int majorVersion;
	private int minorVersion;
	private int publicId;
	private int charSet;
	private String documentId;

	private int codepage;

	/** The stack of elements */
	private Stack<WbxmlCodePageField> elementStack = new Stack<WbxmlCodePageField>();

	/** The current event */
	private WbxmlEvent current;

	/** The text, if the element is TEXT, otherwise null */
	private String text;
	
	/** The opaque data, otherwise null */
	private byte[] opaque;

	private WbxmlCodePageFinder finder;

	public WbxmlDecoder(final InputStream is, final WbxmlCodePageFinder lookup) throws IOException, WbxmlDecoderException {
		this(toByteArray(is), lookup);
	}

	public WbxmlDecoder(byte[] stream, final WbxmlCodePageFinder lookup) throws WbxmlDecoderException {
		this.stream = stream;
		this.finder = lookup;

		readPramble();
	}

	private void readPramble() throws WbxmlDecoderException {
		final int version = readByte();
		/* Major version is the high 4 bits + 1 */
		majorVersion = (version >>> 4) + 1;
		/* Minor version is the low 4 bits */
		minorVersion = (version & 15);
		publicId = readInt();
		int docOffset = 0;
		if (publicId == 0) {
			docOffset = readInt();
		}
		
		charSet = readInt();

		int stSize = readInt(); 
		if (stSize > 0) {
			stringTable = new byte[stSize];
			for (int i=0; i < stSize; i++) {
				stringTable[i] = (byte) readByte();
			}
		}
		
		if (log.isDebugEnabled()) {
			log.debug("majorVersion="+majorVersion+", minorVersion="+minorVersion+", publicId="+publicId);
		}
	}

	public WbxmlEvent next() throws WbxmlDecoderException {
		text = null;
		opaque = null;
		if (offset == stream.length) {
			if (log.isDebugEnabled()) {
				log.debug("offset == stream.length end of stream");
			}
			return null;
		} else if (current != null) {
			if (EventType.StartElement.equals(current.getEventType()) && !current.hasContent()) {
				final WbxmlCodePageField field = elementStack.pop();
				current = new WbxmlEvent(this, EventType.EndElement, field);
				
				if (log.isDebugEnabled()) {
					log.debug("Generating virtual end for non content element "+field);
				}
				
				return current;
			}
		}
		
		boolean process = true;
		while (process) {
			int streamByte = readByte() & 0xff;

			if ((streamByte & 15) <= 0x4 && ((streamByte >>> 4) % 4) == 0) {
				switch (streamByte) {
					case WbxmlConstants.SWITCH_PAGE: { // Codepage Change
						codepage = readByte();
						if (log.isDebugEnabled()) {
							log.debug("SWITCH_PAGE "+codepage);
						}
						break;
					}
					case WbxmlConstants.END: { // End of element
						final WbxmlCodePageField field = elementStack.pop();
						current = new WbxmlEvent(this, EventType.EndElement, field);
						if (log.isDebugEnabled()) {
							log.debug("END "+field);
						}
						process = false;
						break;
					}
					case WbxmlConstants.STR_I: { // inline string
						final StringBuilder sb = new StringBuilder();
						while ((streamByte = readByte()) != 0) {
							sb.append((char) streamByte);
						}

						final WbxmlCodePageField field = elementStack.peek();
						current = new WbxmlEvent(this, EventType.Text, field);
						text = sb.toString();

						if (log.isDebugEnabled()) {
							log.debug("STR_I "+field+" ("+text+")");
						}
						
						process = false;
						break;
					} 
					case WbxmlConstants.STR_T: { 
						final int pos = readInt();
						StringBuilder sb = new StringBuilder();
						for (int i = pos; stringTable[i] != 0; i++) {
							sb.append((char)stringTable[i]);
						}

						final WbxmlCodePageField field = elementStack.peek();
						current = new WbxmlEvent(this, EventType.Text, field);
						
						text = sb.toString();
						if (log.isDebugEnabled()) {
							log.debug("STR_T "+field+" ("+text+")");
						}
						
						process = false;
						break;
					}
					case WbxmlConstants.PI: {
						break;
					}
					case WbxmlConstants.OPAQUE: { // User specific data, just notify and pass back.
						int count = readInt();
						byte[] buf = new byte[count];

						for (int i = 0; i < count; i++) {
							buf[i] = (byte) readByte();
						}
						
						opaque = buf;
						final WbxmlCodePageField field = elementStack.peek();
						current = new WbxmlEvent(this, EventType.Opaque, field, false);
						
						if (log.isDebugEnabled()) {
							log.debug("OPAQUE "+field);
						}
						
						process = false;
						break;
					}
					default:
						throw new WbxmlDecoderException("Unknown stream operation (" + Integer.toHexString(streamByte) + ")");
				}
			} else {
				text = null; // reset the text content

				final byte hasContent = (byte) (streamByte & 64);

				if (hasContent > 0) {
					streamByte = streamByte ^ 64;
				}

				
				WbxmlCodePageField field = null;
				if (finder != null) {
					field = finder.find(codepage, streamByte);
				} else {
					int code = streamByte;
					field = new DefaultWbxmlCodePageField("unknown_"+Integer.toHexString(code), null, codepage, code);
				}

				if (field == null) {
					throw new WbxmlDecoderException("Failed to locate WBXML definition for code page(" + codepage + ") field (" + Integer.toHexString(streamByte) + ")");
				}

				elementStack.push(field);

				current = new WbxmlEvent(this, EventType.StartElement, field, hasContent > 0);

				// stop processing
				process = false;
			}
		}

		return current;
	}

	private final int readByte() throws WbxmlDecoderException {
		if (offset == stream.length) {
			throw new WbxmlDecoderException("End of stream reached");
		}
		if (log.isDebugEnabled()) {
			log.debug("readByte offset=[0x"+Integer.toHexString(offset)+"] = [0x"+Integer.toHexString(stream[offset] & 0xFF)+"]");
		}
		
		return stream[offset++];
	}

	int readInt() throws WbxmlDecoderException {
		int result = 0;
		int i;

		do {
			i = readByte();
			result = (result << 7) | (i & 0x7f);
		} while ((i & 0x80) != 0);

		return result;
	}

	public WbxmlEvent current() {
		return current;
	}

	public String text() {
		return text;
	}
	
	public byte[] opaque() {
		return opaque;
	}
	
	public int getDepth() {
		return elementStack.size();
	}
	
	public WbxmlCodePageFinder getCodePageFinder() {
		return finder;
	}
	
	public void setCodePageFinder(WbxmlCodePageFinder finder) {
		this.finder = finder;
	}
	
	public int getOffset() {
		return offset;
	}
	
	public int getBufferSize() {
		return stream.length;
	}

	private static final byte[] toByteArray(final InputStream is) throws IOException {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[2048];
		int cnt;
		while ((cnt = is.read(buffer)) != -1) {
			baos.write(buffer, 0, cnt);
		}

		return baos.toByteArray();
	}
}
