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

package org.synku4j.wbxml.marshal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.synku4j.wbxml.core.context.WbxmlContext;

/**
 * The <code>WbxmlMarshaller</code> interface 
 * 
 * @author Jools Enticknap
 */
public interface WbxmlMarshaller {
	
	/**
	 * Unmarshal from the supplied stream to an object represented by <T>.
	 * 
	 * @param <T> return type.
	 * @param cntx marshaling context. 
	 * @param is input stream to read from.
	 * @param t the type to create.
	 * @param filter filters used to include/exclude fields
	 * @return an unmarshaled instance of <T>
	 * @throws IOException thrown via the input stream.
	 * @throws WbxmlMarshallerException thrown if the stream is not well formed.
	 */
	<T> T unmarshal(WbxmlContext cntx, InputStream is, Class<T> t,  String...filter) 
	throws IOException, WbxmlMarshallerException;
	
	/**
	 * Marshal to the output stream the supplied object as a wbxml stream.
	 * 
	 * @param <T> the type to marshal.
	 * @param cntx marshaling context.
	 * @param os the output stream to write to.
	 * @param t the object to marshal.
	 * @param filter filters used to include/exclude fields
	 * @throws IOException thrown via the input stream.
	 * @throws WbxmlMarshallerException thrown if the stream is not well formed.
	 */
	<T> void marshal(WbxmlContext cntx, OutputStream os,  T t, String...filter) 
	throws IOException, WbxmlMarshallerException;
}
