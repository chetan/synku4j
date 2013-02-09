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

package org.synku4j.wbxml.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Stack;

import org.synku4j.wbxml.core.WbxmlConstants;

/**
 * @author joolski
 *
 */
@SuppressWarnings("unused")
public class WbxmlNmenonicLogger {
	
	
	public static void log(final InputStream in, PrintStream ps) throws IOException {
		ps.printf("start");
		
		// version first
		final int version = readByte(in);
		int major = (version >>> 4) + 1;
		int minor = version & 0xF;
		ps.printf("version [version %d %d]\n", major, minor);

		int publicIdIdx = -1;
		int publicId = readByte(in);
		ps.printf("publicId [%d]\n", publicId);
		if (publicId == 0) {
			publicIdIdx = readInt(in);
			ps.printf("publicId index [%d]\n", publicIdIdx);
		}
		
		int charset = readInt(in);
		ps.printf("charset [%d]\n", charset);
		
		int tableSize = readInt(in);
		ps.printf("string table size [%d]\n", tableSize);
		if (tableSize > 0) {
			final byte[] buf = new byte[tableSize];
			in.read(buf);
			StringBuilder sb = new StringBuilder();
			for (int i=0; i < tableSize; i++) {
				byte b = buf[i];
				if (b != 0) {
					sb.append((char) b);
				} else {
					ps.printf("string table entry [%d][%s]\n", i, sb.toString());
					sb = new StringBuilder();
				}
			}
		}
		
		int streambyte;
		Stack<Integer> elementIds = new Stack<Integer>();
		while((streambyte =readByte(in)) != -1) {
			//ps.printf("streambyte: [%2h %2d]\n", streambyte, streambyte);
			switch (streambyte) {
				case WbxmlConstants.SWITCH_PAGE:
					final int page = readByte(in);
					ps.printf("T SWITCH_PAGE [%h]\n", page);
					break;
				case WbxmlConstants.END:{
					int tagid = elementIds.pop();
					ps.printf("T END [%h]\n", tagid);
					break;
				}
				case WbxmlConstants.ENTITY: {
					int tagid = elementIds.peek();
					int entity = readByte(in);
					ps.printf("T ENTITY [%h %h]\n", tagid, entity);
					break;
				}
				case WbxmlConstants.STR_I: { // inline string
					int tagid = elementIds.peek();
					final String result = inlineString(in);
					ps.printf("T STR_I [%h \"%s\" ]\n", tagid, result);
					break;
				}
				case WbxmlConstants.LITERAL: {
					int offset = readByte(in);
					ps.printf("T LITERAL [%d]\n", offset);
					break;
				}
				case WbxmlConstants.EXT_I_0: {
					int tagid = elementIds.peek();
					final String result = inlineString(in);
					ps.printf("T EXT_I_0 [%h \"%s\" ]\n", tagid, result);
					break;
				}
				case WbxmlConstants.EXT_I_1: {
					int tagid = elementIds.peek();
					final String result = inlineString(in);
					ps.printf("T EXT_I_1 [%h \"%s\" ]\n", tagid, result);
					break;
				}
				case WbxmlConstants.EXT_I_2: {
					int tagid = elementIds.peek();
					final String result = inlineString(in);
					ps.printf("T EXT_I_2 [%h \"%s\" ]\n", tagid, result);
					break;
				}
				case WbxmlConstants.PI: {
					break;
				}
				case WbxmlConstants.LITERAL_C: {
					ps.printf("T LITERAL_C \n");
					break;
				}
				case WbxmlConstants.EXT_T_0: {
					final int mb = readInt(in);
					ps.printf("T EXT_T_0 [%d]\n",mb);
					break;
				}
				case WbxmlConstants.EXT_T_2: {
					final int mb = readInt(in);
					ps.printf("T EXT_T_2 [%d]\n",mb);
					break;
				}
				case WbxmlConstants.STR_T: {
					final int offset = readInt(in);
					ps.printf("T STR_T [%d]\n", offset);
					break;
				}
				case WbxmlConstants.LITERAL_A: {
					ps.printf("T LITERAL_A \n");
					break;
				}
				case WbxmlConstants.EXT_0: {
					ps.printf("T EXT_0 \n");
					break;
				}
				case WbxmlConstants.EXT_1: {
					ps.printf("T EXT_1 \n");
					break;
				}
				case WbxmlConstants.EXT_2: {
					ps.printf("T EXT_2 \n");
					break;
				}
				case WbxmlConstants.OPAQUE:{
					int length = readInt(in);
					byte[] data = new byte[length];
					
					for (int i=0; i < length; i++) {
						data[i] = (byte) readByte(in);
					}
					
					ps.printf("T OPAQUE [%d]\n", length);
					ByteArrayInputStream is = new ByteArrayInputStream(data);
					log(is, System.err);
					break;
				}
				case WbxmlConstants.LITERAL_AC:
					break;
				default : { // no exact match means this is the start of an element
					int tagid = (streambyte & 0x3F);
					//tagid = (tagid >>> 4);
					
					boolean hasContent = (streambyte & 0x40) == 0x40;
					boolean hasAtributes = (streambyte & 0x80) == 0x80;
					
					ps.printf("T START [%h %b %b]\n", tagid, hasContent, hasAtributes);
					elementIds.push(tagid);
				}
			}
			
		}
		
		ps.printf("end");
	}
	
	
	
	private static int readByte(final InputStream in) throws IOException {
		int i = in.read();
		
		if (i == -1) {
			return -1;
		}
		
		return i;
	}
	
	private static int readInt(InputStream in) throws IOException {
		int result = 0;
		int i;

		do {
			i = readByte(in);
			result = (result << 7) | (i & 0x7f);
		} while ((i & 0x80) != 0);

		return result;
	}

	
	public static final void main(String[] args) throws IOException {
		final FileInputStream fis = new FileInputStream(new File(args[0]));
		
		log(fis, System.out);
		
		fis.close();
	}
	
	private static String inlineString(InputStream in) throws IOException {
		final ByteArrayOutputStream buf = new ByteArrayOutputStream();
		boolean wsp = true;
		while (true) {
			int i = readByte(in);
			if (i == 0) {
				break;
			}
			if (i == -1) {
				throw new IOException("eof");
			}
			if (i > 32) {
				wsp = false;
			}
			buf.write((char)i);
		}
		String result = new String(buf.toByteArray(), "UTF-8");
		buf.close();
		
		return result;
	}
	
}
