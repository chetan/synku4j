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


package org.synku4j.wbxml.core;

import java.util.Arrays;

/**
 * The <code>WbxmlValue</code> class
 * 
 * @author Jools Enticknap
 */
public final class WbxmlValue {
	private WbxmlCodePageField field;
	private byte[] value;
	
	public WbxmlValue(final WbxmlCodePageField field) {
		this.field = field;
	}

	public void setValue(final byte[] value) {
		this.value = value;
	}

	public byte[] getValue() {
		return value;
	}
	
	public WbxmlCodePageField getField() {
		return field;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((field == null) ? 0 : field.hashCode());
		result = prime * result + Arrays.hashCode(value);
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
		if (!(obj instanceof WbxmlValue)) {
			return false;
		}
		WbxmlValue other = (WbxmlValue) obj;
		if (field == null) {
			if (other.field != null) {
				return false;
			}
		} else if (!field.equals(other.field)) {
			return false;
		}
		if (!Arrays.equals(value, other.value)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "WbxmlValue [field=" + field + ", value=" + Arrays.toString(value) + "]";
	}
}
