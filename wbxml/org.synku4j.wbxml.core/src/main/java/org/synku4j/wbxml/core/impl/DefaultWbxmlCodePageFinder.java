package org.synku4j.wbxml.core.impl;

import org.synku4j.wbxml.core.WbxmlCodePage;
import org.synku4j.wbxml.core.WbxmlCodePageField;
import org.synku4j.wbxml.core.WbxmlCodePageFinder;
import org.synku4j.wbxml.core.WbxmlDocument;

public class DefaultWbxmlCodePageFinder implements WbxmlCodePageFinder {

	private final WbxmlDocument wbxmlDocument;

	public DefaultWbxmlCodePageFinder(WbxmlDocument wbxmlDocument) {
		this.wbxmlDocument = wbxmlDocument;
	}

	@Override
	public WbxmlCodePageField find(final int codePageIdx, final int fieldId) {
		for (WbxmlCodePage page :wbxmlDocument.getCodePages()) {
			if (page.getIndex() == codePageIdx) {
				for (WbxmlCodePageField codePageField : page.getFields()) {
					if (codePageField.getToken() == fieldId) {
						return codePageField;
					}
				}
			}
		}
		
		return null;
	}

	@Override
	public WbxmlCodePageField find(final int codePageIdx, final String fieldName) {
		for (WbxmlCodePage page :wbxmlDocument.getCodePages()) {
			if (page.getIndex() == codePageIdx) {
				for (WbxmlCodePageField codePageField : page.getFields()) {
					if (fieldName.equals(codePageField.getFieldName())) {
						return codePageField;
					}
				}
			}
		}

		return null;
	}

}
