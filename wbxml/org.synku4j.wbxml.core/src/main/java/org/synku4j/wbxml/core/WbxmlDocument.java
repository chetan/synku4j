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
 * The <code>WbxmlDocument</code> represents a set of code pages and the root 
 * definition.
 *
 * @author Jools Enticknap
 */
public final class WbxmlDocument {
	private final int publicID;
	private final String formalPublicID;
	private final WbxmlCodePage[] codePages;
	
	public WbxmlDocument(final int publicID, final String formalPublicID, final WbxmlCodePage[] codePages) {
		this.publicID = publicID;
		this.formalPublicID = formalPublicID;
		this.codePages = codePages;
	}

	public int getPublicID() {
		return publicID;
	}

	public String getFormalPublicID() {
		return formalPublicID;
	}

	public WbxmlCodePage[] getCodePages() {
		return codePages;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(codePages);
		result = prime * result + ((formalPublicID == null) ? 0 : formalPublicID.hashCode());
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
		if (!(obj instanceof WbxmlDocument)) {
			return false;
		}
		WbxmlDocument other = (WbxmlDocument) obj;
		if (!Arrays.equals(codePages, other.codePages)) {
			return false;
		}
		if (formalPublicID == null) {
			if (other.formalPublicID != null) {
				return false;
			}
		} else if (!formalPublicID.equals(other.formalPublicID)) {
			return false;
		}
		if (publicID != other.publicID) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "WbxmlDocument [codePages=" + Arrays.toString(codePages) + ", formalPublicID=" + formalPublicID + ", publicID=" + publicID + "]";
	}
}
