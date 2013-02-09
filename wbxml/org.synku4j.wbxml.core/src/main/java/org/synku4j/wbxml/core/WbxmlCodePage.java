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

/**
 * The <code>WbxmlCodePage</code> interface defines the attributes of a WBXML codepage. 
 * 
 * @author Jools Enticknap
 */
public interface WbxmlCodePage {
	/**
	 * @return the global public identifier for this codepage, if 0 then the
	 *         formalPublicID MUST no return a null, or an empty string
	 */
	int getPublicID();

	/**
	 * @return return the formal public id of a code page, for example "-//SYNCML//DTD SyncML 1.0//EN"
	 */
	String getFormalPublicID();

	/**
	 * @return the code page index.
	 */
	int getIndex();

	/**
	 * @return the fields which are defined for this code page.
	 */
	WbxmlCodePageField[] getFields();
}
