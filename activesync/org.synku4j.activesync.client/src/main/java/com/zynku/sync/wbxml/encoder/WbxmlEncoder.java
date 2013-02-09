package com.zynku.sync.wbxml.encoder;

import java.io.IOException;
import java.io.OutputStream;

import com.zynku.sync.wbxml.CodePage;
import com.zynku.sync.wbxml.CodePageField;

public final class WbxmlEncoder {

	public static final int WBXML_VERSION_1_3 = 0x03;

	public static final int WBXML_ENCODING_UTF_8 = 0x6A;

	private WbxmlEncoder() {
	}

	public static void writeWbxmlVersion(final OutputStream os, final int version) throws IOException {
		os.write(version);
	}
	
	public static void switchCodePage(final OutputStream os, final CodePage codepage) throws IOException {
		os.write(0x00);
		os.write(codepage.getCodePageIndex().intValue());
	}
	
	public static void pushElement(final OutputStream os, final CodePage codePage, final String elementName, final boolean hasContent) 
	throws IOException
	{
		int tagKey = codePage.getCodePageToken(elementName);
		if (tagKey == -1) {
			throw new IllegalArgumentException("CodePage ("+codePage.getCodePageName()+") does not specify element ("+elementName+")");
		}
		
		if (hasContent) {
			tagKey |= 64;
		}
		
		os.write(tagKey);
	}
	
	public static void pushElement(final OutputStream os, final CodePage codePage, final String elementName, final String content)
	throws IOException
	{
		if (content == null || content.length() == 0) {
			return;
		}
		
		pushElement(os, codePage, elementName, true);
		inlineString(os, content);
		popElement(os);
	}
	
	public static void inlineString(final OutputStream os, final String value) throws IOException {
		os.write(0x03);
		for (byte b : value.getBytes()) {
			os.write(b);
		}
		os.write(0x00);
	}
	
	public static void popElement(final OutputStream os) throws IOException {
		os.write(0x01);
	}

	public static void writeUnknownPublicId(OutputStream os) throws IOException {
		os.write(0x01);
	}

	public static void writeEncoding(OutputStream os, int encoding) throws IOException {
		os.write(encoding);
	}

	public static void noStringTable(OutputStream os) throws IOException {
		os.write(0x00);
	}
	
	public static void writePreamble(OutputStream os) throws IOException {
		writeWbxmlVersion(os, WbxmlEncoder.WBXML_VERSION_1_3);
		writeUnknownPublicId(os);
		writeEncoding(os, WbxmlEncoder.WBXML_ENCODING_UTF_8);
		noStringTable(os);
	}

	public static void pushElement(final OutputStream os, final CodePageField field, final boolean hasContent) throws IOException {
		int tagKey = field.getCodePageIndex();
		
		if (hasContent) {
			tagKey |= 64;
		}
		
		os.write(tagKey);
	}
	
	public static void pushElement(final OutputStream os, final CodePageField field, final String content) throws IOException {
		if (content == null || content.length() == 0) {
			return;
		}
		
		pushElement(os, field, true);
		inlineString(os, content);
		popElement(os);
	}
}
