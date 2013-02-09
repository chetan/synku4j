package org.synku4j.wbxml.core.impl;

import org.synku4j.wbxml.core.WbxmlCodePage;
import org.synku4j.wbxml.core.WbxmlCodePageField;

public class DefaultWbxmlCodePage implements WbxmlCodePage {
	
	private final String formalPublicId;
	private final int publicId;
	private final int index;
	private final WbxmlCodePageField[] fields;

	public DefaultWbxmlCodePage(String formalPublicId, int index, WbxmlCodePageField[] fields) {
		this(formalPublicId, 0, index, fields);
	}
	
	public DefaultWbxmlCodePage(String formalPublicId, int publicId, int index, WbxmlCodePageField[] fields) {
		this.formalPublicId = formalPublicId;
		this.publicId = publicId;
		this.index = index;
		this.fields = fields;
		
		if (publicId == 0 && formalPublicId != null && formalPublicId.length() > 0) {
			throw new IllegalArgumentException("publicId cannot be 0 if a formalPublicId is supplied");
		}
		
	}

	@Override
	public WbxmlCodePageField[] getFields() {
		return fields;
	}

	@Override
	public String getFormalPublicID() {
		return formalPublicId;
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public int getPublicID() {
		return publicId;
	}
}
