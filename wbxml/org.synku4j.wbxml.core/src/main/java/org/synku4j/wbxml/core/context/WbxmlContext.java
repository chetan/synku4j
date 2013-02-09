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

package org.synku4j.wbxml.core.context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.synku4j.wbxml.core.WbxmlCodePage;
import org.synku4j.wbxml.core.WbxmlCodePageField;
import org.synku4j.wbxml.core.WbxmlDocument;

/**
 * The <code>WbxmlContext</code> class manages the state.
 * 
 * @author Jools Enticknap
 */
public class WbxmlContext {

	private int wbxmlVersion;
	private int wbxmlEncoding;
	
	/** Require that all strings are encoded as opqque values */
	private boolean opaqueStrings;
	
	private Stack<WbxmlCodePageField> elements = new Stack<WbxmlCodePageField>();
	private boolean captureXML;
	private StringBuilder xml;
	private List<WbxmlCodePageField> codePageFields = new ArrayList<WbxmlCodePageField>();
	
	public WbxmlContext() {
	}
	
	public boolean captureXML() {
		return captureXML;
	}

	public StringBuilder getXml() {
		return xml;
	}

	public Stack<WbxmlCodePageField> getElements() {
		return null;
	}

	public void reset() {
		elements.clear();
		if (captureXML) {
			xml = new StringBuilder();
		}
	}

	public void setCaptureXML(boolean capture) {
		captureXML = capture;
	}

	public Class<?> classFor(WbxmlCodePageField cp) {
		return null;
	}

	public void addCodePageFields(WbxmlCodePageField[] fields) {
		codePageFields.addAll(Arrays.asList(fields));
	}

	public List<WbxmlCodePageField> getCodePageFields() {
		return codePageFields;
	}

	public int getWbxmlVersion() {
		return wbxmlVersion;
	}

	public void setWbxmlVersion(int wbxmlVersion) {
		this.wbxmlVersion = wbxmlVersion;
	}

	public int getWbxmlEncoding() {
		return wbxmlEncoding;
	}

	public void setWbxmlEncoding(int wbxmlEncoding) {
		this.wbxmlEncoding = wbxmlEncoding;
	}

	public boolean isOpaqueStrings() {
		return opaqueStrings;
	}

	public void setOpaqueStrings(boolean opaqueStrings) {
		this.opaqueStrings = opaqueStrings;
	}

	public WbxmlCodePage lookupCodePage(final String name, final int index, final int publicId) {
		// TODO Auto-generated method stub
		return null;
	}

	public WbxmlDocument lookupWbxmlDocument(final String name, final int index, final int publicId) {
		return null;
	}
}
