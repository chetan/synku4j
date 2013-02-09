package com.zynku.sync.wbxml.decoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.zynku.sync.wbxml.CodePage;
import com.zynku.sync.wbxml.CodePageField;
import com.zynku.sync.wbxml.CodePageLookup;

/**
 * The <code>WbxmlDecoder</code> class is a pull oriented WBXML decoder for
 * generating events based on a WBXML stream.
 * 
 * NOTE: This decoder only implements the requirements for an active sync based
 * WBXML stream, notably the absence of string tables.
 * 
 * @author Jools Enticknap
 */
@SuppressWarnings("unused")
public final class WbxmlDecoder {

	private final byte[] stream;
	private int offset;

	private int majorVersion;
	private int minorVersion;
	private int publicId;
	private int charSet;

	private int codepage;
	
	/** The stack of elements */
	private Stack<CodePageField> elementStack = new Stack<CodePageField>();

	/** The current event */
	private WbxmlEvent current;

	/** The text, if the element is TEXT, otherwise null */
	private String text;

	private String path;
	private CodePageLookup lookup;

	public WbxmlDecoder(final InputStream is, final CodePageLookup lookup) throws IOException, WbxmlDecoderException {
		this(toByteArray(is), lookup);
	}

	public WbxmlDecoder(byte[] stream, final CodePageLookup lookup) throws WbxmlDecoderException {
		this.stream = stream;
		this.lookup = lookup;
		
		
		readPramble();
	}

	private void readPramble() throws WbxmlDecoderException {
		final int version = readByte();
		/* Major version is the high 4 bits + 1 */
		majorVersion = (version >>> 4) + 1;
		/* Minor version is the low 4 bits */
		minorVersion = (version & 15);
		publicId = readByte();
		charSet = readByte();
		readByte(); // read in the empty string table
	}

	public WbxmlEvent next() throws WbxmlDecoderException {
		path = null;

		if (offset == stream.length) {
			if (elementStack.isEmpty()) {
				current = null;
				return null;
			} else {
				final CodePageField field = elementStack.pop();
				current = new WbxmlEvent(this, WbxmlEvent.END_ELEMENT, field.getFieldName(), field.getNameSpace());
				return current;
			}
		} else if (current != null && !current.hasContent()) {
			final CodePageField field = elementStack.pop();
			current = new WbxmlEvent(this, WbxmlEvent.END_ELEMENT, field.getFieldName(), field.getNameSpace());
			return current;
		}

		boolean process = true;
		while (process) {
			int streamByte = readByte();

			if ((streamByte & 15) <= 0x4 && ((streamByte >>> 4) % 4) == 0) {
				switch (streamByte) {
					case 0x00: { // Codepage Change
						codepage = readByte();
						break;
					}
					case 0x01: { // End of element
						final CodePageField field = elementStack.pop();
						current = new WbxmlEvent(this, WbxmlEvent.END_ELEMENT, field.getFieldName(), field.getNameSpace());
						process = false;
						break;
					}
					case 0x03: { // inline string
						final StringBuilder sb = new StringBuilder();
						while ((streamByte = readByte()) != 0) {
							sb.append((char) streamByte);
						}

						final CodePageField field = elementStack.peek();
						current = new WbxmlEvent(this, WbxmlEvent.TEXT_CONTENT, field.getFieldName(), field.getNameSpace());
						text = sb.toString();
						process = false;
						break;
					}
					default:
						throw new WbxmlDecoderException("Unknown stream operation (" + streamByte + ")");
				}
			} else {
				text = null; // reset the text content

				final byte hasContent = (byte) (streamByte & 64);

				if (hasContent > 0) {
					streamByte = streamByte ^ 64;
				}

				final CodePageField field = lookup.lookup(codepage, streamByte);
				if (field == null) {
					throw new WbxmlDecoderException("Failed to locate WBXML definition for code page("+codepage+") field ("+streamByte+")");
				}
				
				elementStack.push(field);

				current = new WbxmlEvent(this, WbxmlEvent.START_ELEMENT, field.getFieldName(), field.getNameSpace(), hasContent > 0);

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
		return stream[offset++];
	}

	public WbxmlEvent current() {
		return current;
	}

	public String text() {
		return text;
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

	public String getPath() {
		if (path != null) {
			return path;
		}

		final StringBuilder sb = new StringBuilder("/");
		final int size = elementStack.size();
		int cnt = 0;
		for (CodePageField fld : elementStack) {
			sb.append(fld.getFieldName());
			if (++cnt < size) {
				sb.append("/");
			}
		}

		return (path = sb.toString());
	}
}
