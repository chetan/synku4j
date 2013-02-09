package org.synku4j.wbxml.core.impl;

import org.synku4j.wbxml.core.WbxmlCodePageField;

public class DefaultWbxmlCodePageField implements WbxmlCodePageField {
	
	private final String fieldName;
	private final Class<?> modelClass;
	private final int page;
	private final int token;
	
	public DefaultWbxmlCodePageField(String fieldName, Class<?> modelClass, int page, int token) {
		this.fieldName = fieldName;
		this.modelClass = modelClass;
		this.page = page;
		this.token = token;
	}

	@Override
	public String getFieldName() {
		return fieldName;
	}

	@Override
	public Class<?> getModelClass() {
		return modelClass;
	}

	@Override
	public int getPage() {
		return page;
	}

	@Override
	public int getToken() {
		return token;
	}
}
