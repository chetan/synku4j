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

import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;
import org.synku4j.wbxml.core.WbxmlCodePageField;

public final class WbxmlCodePageFieldWrapper implements WbxmlCodePageField {
	private final int pageIdx;
	private final int fieldIdx;
	private final String fieldName;
	private final Class<?> modelClass;
	
	public WbxmlCodePageFieldWrapper(final WbxmlPage page, final WbxmlField field, final Class<?> modelClass) {
		this(page.index(), field.index(), field.name(), modelClass);
	}
	
	public WbxmlCodePageFieldWrapper(final int pageIdx, final int fieldIdx, final String fieldName, final Class<?> modelClass) {
		this.pageIdx = pageIdx;
		this.fieldIdx = fieldIdx;
		this.fieldName = fieldName;
		this.modelClass = modelClass;
	}

	@Override
	public int getToken() {
		return fieldIdx;
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
		return pageIdx;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fieldIdx;
		result = prime * result + ((fieldName == null) ? 0 : fieldName.hashCode());
		result = prime * result + ((modelClass == null) ? 0 : modelClass.hashCode());
		result = prime * result + pageIdx;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof WbxmlCodePageFieldWrapper)) {
			return false;
		}
		WbxmlCodePageFieldWrapper other = (WbxmlCodePageFieldWrapper) obj;
		if (fieldIdx != other.fieldIdx) {
			return false;
		}
		if (fieldName == null) {
			if (other.fieldName != null) {
				return false;
			}
		} else if (!fieldName.equals(other.fieldName)) {
			return false;
		}
		if (modelClass == null) {
			if (other.modelClass != null) {
				return false;
			}
		} else if (!modelClass.equals(other.modelClass)) {
			return false;
		}
		if (pageIdx != other.pageIdx) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "CodePageFieldWrapper [fieldIdx=" + fieldIdx + ", fieldName=" + fieldName + ", modelClass=" + modelClass + ", pageIdx=" + pageIdx + "]";
	}
	
	
}
