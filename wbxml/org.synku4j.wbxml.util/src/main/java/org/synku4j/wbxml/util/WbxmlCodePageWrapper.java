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

import java.util.LinkedHashMap;
import java.util.Map;

import org.synku4j.wbxml.annotations.WbxmlPage;
import org.synku4j.wbxml.core.WbxmlCodePage;
import org.synku4j.wbxml.core.WbxmlCodePageField;

/**
 * The <code>CodePageWrapper</code> class is a simple implementation of the <code>CodePage</code> interface.
 * 
 * @author Jools Enticknap
 */
public final class WbxmlCodePageWrapper implements WbxmlCodePage {
	private int index;
	private Map<Integer, WbxmlCodePageField> fields = new LinkedHashMap<Integer, WbxmlCodePageField>();
	private final int publicID;
	private final String formalPublicID;
	
	public WbxmlCodePageWrapper(WbxmlPage page) {
		this(page.index());
	}
	
	public WbxmlCodePageWrapper(final int index) {
		this(index, 0, null);
	}

	public WbxmlCodePageWrapper(final int index, final int publicID, final String formalPublicID) {
		this.index = index;
		this.publicID = publicID;
		this.formalPublicID = formalPublicID;
	}
	
	@Override
	public WbxmlCodePageField[] getFields() {
		return fields.values().toArray(new WbxmlCodePageField[fields.size()]);
	}
	
	public boolean addCodePageField(final WbxmlCodePageField codePageField) {
		final int key = codePageField.getToken();
		if (key == -1) {
			return false;
		}
		
		final WbxmlCodePageField fld = fields.get(key);
		if (fld != null) {
			return false;
		}
		
		return fields.put(key, codePageField) == null;
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public String getFormalPublicID() {
		return formalPublicID;
	}

	@Override
	public int getPublicID() {
		return publicID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fields == null) ? 0 : fields.hashCode());
		result = prime * result + ((formalPublicID == null) ? 0 : formalPublicID.hashCode());
		result = prime * result + index;
		result = prime * result + publicID;
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
		if (!(obj instanceof WbxmlCodePageWrapper)) {
			return false;
		}
		WbxmlCodePageWrapper other = (WbxmlCodePageWrapper) obj;
		if (fields == null) {
			if (other.fields != null) {
				return false;
			}
		} else if (!fields.equals(other.fields)) {
			return false;
		}
		if (formalPublicID == null) {
			if (other.formalPublicID != null) {
				return false;
			}
		} else if (!formalPublicID.equals(other.formalPublicID)) {
			return false;
		}
		if (index != other.index) {
			return false;
		}
		if (publicID != other.publicID) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "CodePageWrapper [fields=" + fields + ", formalPublicID=" + formalPublicID + ", index=" + index + ", publicID=" + publicID + "]";
	}
}
