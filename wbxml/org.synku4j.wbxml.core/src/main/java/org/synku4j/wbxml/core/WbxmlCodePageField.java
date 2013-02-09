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
 * The <code>CodePageField</code> interface defines the mapping of a field
 * to it's name, supplying where specified a class which represents the field.
 * 
 * As a general rule, only composite fields (fields that have children) will have a model class.
 * 
 * @author Jools Enticknap
 */
public interface WbxmlCodePageField {
	/**
	 * @return the name of the field, as it would appear in the DTD
	 */
	String getFieldName();
	
	/**
	 * @return the WBXML integer which represents this field.
	 */
	int getToken();
	
	/**
	 * @return the integer which represents this namespace this page resides in.
	 */
	int getPage();
	
	/**
	 * @return the class which represents this field.
	 */
	Class<?> getModelClass();
}
