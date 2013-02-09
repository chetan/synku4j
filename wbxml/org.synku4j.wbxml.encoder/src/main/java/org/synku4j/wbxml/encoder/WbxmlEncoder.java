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

package org.synku4j.wbxml.encoder;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.synku4j.wbxml.core.WbxmlConstants;
import org.synku4j.wbxml.core.context.WbxmlContext;

public final class WbxmlEncoder {

	public static final Log log = LogFactory.getLog(WbxmlEncoder.class);

	private WbxmlEncoder() {
	}

	/**
	 * 5.8.4.7.2.
	 * 
	 * Code Page Switch Token switchPage = SWTICH_PAGE pageindex pageindex =
	 * u_int8
	 */
	public static void switchCodePage(final OutputStream os, final int codepage) throws IOException {
		if (log.isDebugEnabled()) {
			log.debug("START switchCodePage [ 0x" + Integer.toHexString(codepage) + "]");
		}

		writeByte(os, WbxmlConstants.SWITCH_PAGE);
		writeByte(os, codepage);
		
		if (log.isDebugEnabled()) {
			log.debug("END switchCodePage");
		}
	}

	/**
	 * 5.4. Version Number version = u_int8 // WBXML version number All WBXML
	 * documents contain a version number in their initial byte. This version
	 * specifies the WBXML specification version. The version byte contains the
	 * major version minus one in the upper four bits and the minor version in
	 * the lower four bits. For example, the version number 1.3 would be encoded
	 * as 0x03, and version number 2.7 as 0x17.
	 */
	public static void writeWbxmlVersion(final OutputStream os, final int version) throws IOException {
		if (log.isDebugEnabled()) {
			final int majorVersion = (version >>> 4) + 1;
			final int minorVersion = (version & 0xF);
			log.debug("writeWbxmlVersion [0x" + Integer.toHexString(version) + "] [" + majorVersion + "].[" + minorVersion + "]");
		}
		writeByte(os, version);
	}

	/**
	 * 5.8.4.1. Strings string = inline | tableref inline = STR_I termstr
	 * tableref = STR_T index Strings encode inline character data or references
	 * into a string table. The string table is a concatenation of individual
	 * strings. String termination is dependent on the character document
	 * encoding and should not be presumed to include NULL termination.
	 * References to each string include an offset into the table, indicating
	 * the string being referenced.
	 */
	public static void inlineString(WbxmlContext cntx, final OutputStream os, final String value) throws IOException {
		if (log.isDebugEnabled()) {
			log.debug("inlineString [" + value + "]");
		}

		writeByte(os, WbxmlConstants.STR_I);
		writeBytes(os, value.getBytes());
		writeByte(os, WbxmlConstants.TERMNIATOR); // Should be encoding specific, but in practice its normally 0.
	}

	public static void popElement(final WbxmlContext cntx, final OutputStream os) throws IOException {
		if (log.isDebugEnabled()) {
			log.debug("popElement ");
		}
		
		writeByte(os, WbxmlConstants.END);
	}

	public static void writeEncoding(final OutputStream os, final int encoding) throws IOException {
		if (log.isDebugEnabled()) {
			log.debug("writeEncoding ["+Integer.toHexString(encoding)+"]");
		}
		
		writeByte(os, encoding);
	}

	public static void pushElement(final WbxmlContext cntx, final OutputStream os, int token, final boolean hasContent) throws IOException {
		if (log.isDebugEnabled()) {
			log.debug("pushElement [index = "+Integer.toHexString(token)+"] [has content ="+hasContent+"]");
		}

		if (hasContent) {
			token |= 0x40;
		}

		writeByte(os, token);
	}

	public static void pushElement(final WbxmlContext cntx, final OutputStream os, final int token, final String content) throws IOException {
		if (content == null || content.length() == 0) {
			return;
		}

		pushElement(cntx, os, token, true);
		inlineString(cntx, os, content);
		popElement(cntx, os);
	}

	public static void pushOpaque(final WbxmlContext cntx, final OutputStream os, final int token, final byte[] content) throws IOException {
		if (content == null || content.length == 0) {
			return;
		}

		pushElement(cntx, os, token, true);
		opaque(cntx, os, content);
		popElement(cntx, os);
	}

	public static void opaque(final WbxmlContext cntx, final OutputStream os, final byte[] content) throws IOException {
		if (log.isDebugEnabled()) {
			log.debug("opaque data");
		}
		
		writeByte(os, WbxmlConstants.OPAQUE);
		writeInt(os, content.length);
		writeBytes(os, content);
	}

	public static void writePublicId(OutputStream os, int publicId) throws IOException {
		if (log.isDebugEnabled()) {
			log.debug("writePublicId [0x"+Integer.toHexString(publicId)+"]");
		}
		writeInt(os, publicId);
	}

	public static void writeStringTable(OutputStream os, int len) throws IOException {
		if (log.isDebugEnabled()) {
			log.debug("writeStringTable ["+len+"]");
		}
		writeInt(os, len);
	}

	private static void writeBytes(final OutputStream os, final byte[] bytes) throws IOException {
		if (log.isDebugEnabled()) {
			final StringBuilder sb = new StringBuilder("writeBytes ");
			for (byte b : bytes) {
				sb.append("[0x").append(Integer.toHexString(b)).append("]");
			}
			log.debug(sb.toString());
		}

		os.write(bytes);
	}

	
	private static void writeByte(final OutputStream os, int value) throws IOException {
		if (log.isDebugEnabled()) {
			log.debug("writeByte [0x" + Integer.toHexString(value) + "]");
		}

		os.write(value);
	}

	/**
	 * write an octet encoded integer.
	 */
	private static void writeInt(final OutputStream out, int value) throws IOException {
		if (log.isDebugEnabled()) {
			log.debug("writeInt ["+value+"]");
		}
		final byte[] buf = new byte[5];
		int cnt = 1;
		int idx = 0;

		do {
			buf[idx++] = (byte) (value & 0x7f);
			value = value >> 7;
		} while (value != 0);

		while (idx > 1) {
			writeByte(out, buf[--idx] | 0x80);
			cnt++;
		}
		writeByte(out, buf[0]);
	}
}
