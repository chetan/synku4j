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

package org.synku4j.wbxml.marshal.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.ObjectInputStream.GetField;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

import static org.synku4j.wbxml.encoder.WbxmlEncoder.inlineString;
import static org.synku4j.wbxml.encoder.WbxmlEncoder.opaque;
import static org.synku4j.wbxml.encoder.WbxmlEncoder.popElement;
import static org.synku4j.wbxml.encoder.WbxmlEncoder.pushElement;
import static org.synku4j.wbxml.encoder.WbxmlEncoder.pushOpaque;
import static org.synku4j.wbxml.encoder.WbxmlEncoder.switchCodePage;
import static org.synku4j.wbxml.encoder.WbxmlEncoder.writeEncoding;
import static org.synku4j.wbxml.encoder.WbxmlEncoder.writePublicId;
import static org.synku4j.wbxml.encoder.WbxmlEncoder.writeStringTable;
import static org.synku4j.wbxml.encoder.WbxmlEncoder.writeWbxmlVersion;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;
import org.synku4j.wbxml.core.WbxmlConstants;
import org.synku4j.wbxml.core.context.WbxmlContext;
import org.synku4j.wbxml.marshal.WbxmlMarshallerException;
import org.synku4j.wbxml.util.WbxmlUtil;

/**
 * The <code>MarshalDelegate</code> class manages the marshaling of data to the supplied output stream.
 * 
 * 
 * 
 * @author Jools Enticknap
 */
class MarshalDelegate {
	
	private static final Log log = LogFactory.getLog(MarshalDelegate.class);
	
	
	
	protected MarshalDelegate() {
	}
	
	protected void marshal(final WbxmlContext cntx, final OutputStream os, final Object source, final WbxmlPage currentPage, final String... filter)
	throws IOException, WbxmlMarshallerException
	{
		cntx.reset();
		final Class<? extends Object> clazz = source.getClass();
		final WbxmlPage page = getWbxmlPage(clazz, true);
		final WbxmlField root = getWbxmlField(clazz, true);
		
		writePreamble(cntx, os, page);
		pushElement(cntx, os, root.index(), true);
		doMarshal(cntx, os, source, page, filter);
		popElement(cntx, os);
	}
	
	private void doMarshal(WbxmlContext cntx, OutputStream os, Object target, WbxmlPage currentPage, String[] filters) throws WbxmlMarshallerException, IOException {
		final Class<? extends Object> clazz = target.getClass();
		final WbxmlPage page = getWbxmlPage(clazz);

		if (log.isDebugEnabled()) {
			log.debug("target class = " + clazz + ", page = " + page);
		}

		if (currentPage == null && page == null) {
			throw new WbxmlMarshallerException("Unable to determin current codepage");
		}
		
		boolean popCodePage = false;
		if (page != null && currentPage == null || !(currentPage.index() ==	page.index())) {
			popCodePage = true;
		}
		
		 for (Field field : WbxmlUtil.getFields(target)) {
			 processField(cntx, os, field, target, (page == null ? currentPage : page), filters);
		 }
		 
		 if (popCodePage) {
			 switchCodePage(os, page.index());
		 }
	}

	private void writePreamble(WbxmlContext cntx, OutputStream os, WbxmlPage page) throws IOException {
		int version = cntx.getWbxmlVersion();
		int encoding = cntx.getWbxmlEncoding();
		final int publicId = page.publicId();

		if (version == 0) {
			if (log.isWarnEnabled()) {
				log.warn("No WBXML version specified in context, default to 1.2");
			}
			version = WbxmlConstants.WBXML_VERSION_1_2;
		}

		if (publicId == 0) {
			if (log.isWarnEnabled()) {
				log.warn("Unknown public id for document, recipient may reject");
			}
		}

		if (encoding == 0) {
			if (log.isWarnEnabled()) {
				log.warn("Unspecified document encoding, falling back to UTF-8");
			}
			encoding = WbxmlConstants.WBXML_ENCODING_UTF8;
		}

		writeWbxmlVersion(os, version);
		writePublicId(os, publicId);
		writeEncoding(os, encoding);

		// TODO : Add support in the context for creating a string table.
		// This will require all strings to be cached then referenced into this
		// table.
		writeStringTable(os, 0);
	}
	
	
	/**
	 */
	private void processField(final WbxmlContext cntx, OutputStream os, Field field, Object target, WbxmlPage currentPage, String[] filters) 
	throws IOException, WbxmlMarshallerException
	{
		if (log.isDebugEnabled()) {
			 log.debug("processField field = " + field);
		}
		
		// TODO: Check to see if the filters match.
		
		field.setAccessible(true);
		
		final WbxmlField wbxmlField = field.getAnnotation(WbxmlField.class);
		
		//
		// A Ghost element is a container for elements which will be parsed.
		// Do not generate a start or end element for this field.
		//
		final boolean ghostElement = wbxmlField.index() == WbxmlField.NO_INDEX;
		if (ghostElement) {
			if (log.isDebugEnabled()) {
				log.debug("ghost element :" + wbxmlField);
			}
		}

		final Type fieldType = field.getGenericType();

		Object value = null;
		try {
			value = field.get(target);
		} catch (Exception e) {
			// This will terminate the marshaling. Is this always right ?
			if (log.isWarnEnabled()) {
				log.warn("Exception thrown getting value via field ("+field+")", e);
			}
			throw new WbxmlMarshallerException(e);
		}

		if (log.isDebugEnabled()) {
			log.debug("Value of field (" + field + ") = " + value);
		}

		if (value == null) {
			if (wbxmlField != null && wbxmlField.required()) {
				throw new WbxmlMarshallerException("Field (" + wbxmlField.name() +"), is marked required but is null. Processing cannot continue.");
			}
			
			if (log.isDebugEnabled()) {
				log.debug("Field ("+wbxmlField.name()+") is null, it will not be written to the stream.");
			}
			
			return;
		} else if (Collection.class.isAssignableFrom(value.getClass())) {
			if (log.isDebugEnabled()) {
				log.debug("Field ("+wbxmlField.name()+") is a collection");
			}

			// TODO :
			//
			// in the case of a collection of strings we need to
			// wrap each entry in the defined wbxml field.
			// Objects need to be marshalled.
			//

			// We have a collection, marshal through the values;
			final Collection<?> values = (Collection<?>) value;
			if (wbxmlField.required() && values.isEmpty()) {
				throw new WbxmlMarshallerException("Field (" + wbxmlField.name() +"), is marked required but is empty. Processing cannot continue.");
			}

			if (!ghostElement) {
				pushElement(cntx, os, wbxmlField.index(), true);
			}

			// Get field type for each item as it may not be declared on the 
			WbxmlField innerField;
			for (Object obj : values) {
				innerField = getWbxmlField(obj.getClass(), false);

				if (ghostElement && innerField != null) {
					pushElement(cntx, os, innerField.index(), true);
				}

				if (obj instanceof String) {
					if (cntx.isOpaqueStrings()) {
						pushOpaque(cntx, os, innerField.index(), obj.toString().getBytes());
					} else {
						inlineString(cntx, os, obj.toString());
					}
				} else {
					doMarshal(cntx, os, obj, currentPage, filters);
				}

				if (ghostElement && innerField != null) {
					popElement(cntx, os);
				}
			}

			if (!ghostElement) {
				popElement(cntx, os);
			}
		} else if (value instanceof byte[]) {
			pushOpaque(cntx, os, wbxmlField.index(), (byte[]) value);
		} else if (value instanceof Boolean) {
			pushElement(cntx, os, wbxmlField.index(), false);
		} else {
			final Class<?> valueClass = value.getClass();

			if (!ghostElement) {
				pushElement(cntx, os, wbxmlField.index(), true);
			}

			final WbxmlPage page = valueClass.getAnnotation(WbxmlPage.class);
			if (page != null) {
				doMarshal(cntx, os, value, page, filters);
			} else {
				if (cntx.isOpaqueStrings()) {
					opaque(cntx, os, value.toString().getBytes());
				} else {
					inlineString(cntx, os, value.toString());
				}
			}

			if (!ghostElement) {
				popElement(cntx, os);
			}
		}
	}
	
	private static final WbxmlField getWbxmlField(Class<?> clazz, boolean isRoot) throws WbxmlMarshallerException {
		// Check to see if the root field is specified on the class
		final WbxmlField root = clazz.getAnnotation(WbxmlField.class);
		if (isRoot && root == null) {
			throw new WbxmlMarshallerException("Root object must be annoted with @WbxlField");
		}

		return root;
	}

	private static final WbxmlPage getWbxmlPage(Class<?> clazz) throws WbxmlMarshallerException {
		return getWbxmlPage(clazz, false);
	}

	private static final WbxmlPage getWbxmlPage(Class<?> clazz, boolean root) throws WbxmlMarshallerException {
		final WbxmlPage page = clazz.getAnnotation(WbxmlPage.class);

		if (root && page == null) {
			throw new WbxmlMarshallerException("Object has not been annotated with a @WbxmlPage");
		}

		return page;
	}
	
}
