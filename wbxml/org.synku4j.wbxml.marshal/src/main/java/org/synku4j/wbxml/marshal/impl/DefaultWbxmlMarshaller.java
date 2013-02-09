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
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;
import org.synku4j.wbxml.core.WbxmlCodePageField;
import org.synku4j.wbxml.core.context.WbxmlContext;
import org.synku4j.wbxml.marshal.WbxmlMarshaller;
import org.synku4j.wbxml.marshal.WbxmlMarshallerException;
import org.synku4j.wbxml.util.WbxmlUtil;

/**
 * The <code>DefaultWbxmlMarshaller</code> class marshals object to and from
 * WBML based on the annotations on the classes, and hints located in the
 * context.
 * 
 * @author Jools Enticknap
 */
public class DefaultWbxmlMarshaller implements WbxmlMarshaller {

	private static final Log log = LogFactory.getLog(DefaultWbxmlMarshaller.class);

	@Override
	public <T> void marshal(final WbxmlContext cntx, final OutputStream os, final T t, final String... filter) throws IOException,
			WbxmlMarshallerException {
		final Class<? extends Object> clazz = t.getClass();
		final WbxmlField root = getWbxmlField(clazz, true);
		final WbxmlPage page = getWbxmlPage(clazz, true);

		if (log.isDebugEnabled()) {
			log.debug("Start marshaling. top class = " + clazz + ", root =" + root + ", page=" + page);
		}
		
		new MarshalDelegate().marshal(cntx, os, t, page, filter);
	}

	
	@Override
	public <T> T unmarshal(WbxmlContext cntx, InputStream is, Class<T> targetClass, String... filter) throws IOException, WbxmlMarshallerException {
		 T target;
		 try {
			 target = targetClass.newInstance();
		 } catch (Exception e) {
			 if (log.isWarnEnabled()) {
				 log.warn("Unable to create an instance of the target class", e);
			 }
			 
			 throw new WbxmlMarshallerException("Exception raised creating new instance of(" + targetClass + ")", e);
		 }
		 
		 
		 final ParseStackEntry pse = new ParseStackEntry(target);
		 
		 
		 final Deque<ParseStackEntry> parseStack = new ArrayDeque<ParseStackEntry>();
		 parseStack.push(pse);		 
		
		
		return target;
	}


	//
	// final boolean captureXML = cntx.captureXML();
	// final DefaultCodePageFinder finder = new
	// DefaultCodePageFinder(targetClass, cntx.getCodePageFields());
	// try {
	// final WbxmlDecoder decoder = new WbxmlDecoder(is, finder);
	//
	// boolean root = true;
	// WbxmlEvent event = null;
	// while ((event = decoder.next()) != null) {
	// if (captureXML) {
	// captureXML(cntx, event);
	// }
	// switch (event.getEventType()) {
	// case StartElement: {
	// if (root) {
	// root = false;
	// } else {
	// doStartElement(cntx, event, parseStack);
	// }
	// break;
	// }
	// case Opaque: {
	// doOpaque(cntx, event, parseStack);
	// break;
	// }
	// case Text: {
	// doText(cntx, event, parseStack);
	// break;
	// }
	// case EndElement: {
	// doEndElement(cntx, event, parseStack);
	// break;
	// }
	// default:
	// throw new IllegalStateException("Unhandled state:" + event);
	// }
	// }
	//
	// } catch (Exception e) {
	// if (log.isWarnEnabled()) {
	// log.warn("Exception raised decoding stream", e);
	// }
	// throw new WbxmlMarshallerException(e);
	// }
	//
	// return target;
	// }
	//
	// private static void captureXML(final WbxmlContext cntx, final WbxmlEvent
	// event) {
	// final StringBuilder sb = cntx.getXml();
	// final WbxmlCodePageField field = event.getField();
	// switch (event.getEventType()) {
	// case StartElement:
	// sb.append("<").append(field.getFieldName()).append(">");
	// break;
	// case Text:
	// sb.append(event.getSourceAsWbxmlDecoder().text());
	// break;
	// case Opaque:
	// // should really try to convert to a string
	// break;
	// case EndElement:
	// sb.append("</").append(field.getFieldName()).append(">");
	// break;
	// }
	// }
	//
	// /**
	// * When a new event occurs create a new entry on the stack to contain the
	// * data for the subsequent events.
	// *
	// * @param cntx
	// * @param event
	// * @param parseStack
	// * @throws Exception
	// */
	// protected static void doStartElement(final WbxmlContext cntx, final
	// WbxmlEvent event, final Stack<ParseStackEntry> parseStack) throws
	// Exception {
	// final WbxmlCodePageField cp = event.getField();
	//
	// if (log.isDebugEnabled()) {
	// log.debug("StartElement :" + cp.getFieldName());
	// }
	//
	// if (cntx.captureXML()) {
	// captureXML(cntx, event);
	// }
	//
	// // First we must locate the field in the parent object to which this
	// // element relates.
	// final ParseStackEntry parentEntry = parseStack.peek();
	// final Field field = parentEntry.findField(cp);
	//
	// if (field == null) {
	// // TODO: Add path to the element which has no mapping.
	// if (log.isDebugEnabled()) {
	// log.warn("Failed to locate a mapping for field :" + cp);
	// }
	// throw new
	// WbxmlMarshallerException("Failed to locate a mapping for field :" + cp +
	// ", parent:" + parentEntry.target);
	// }
	//
	// final Type genericType = field.getGenericType();
	// if (genericType instanceof Class<?>) {
	// final ParseStackEntry child = new ParseStackEntry();
	//
	// final Class<?> cls = (Class<?>) genericType;
	// final WbxmlPage p = cls.getAnnotation(WbxmlPage.class);
	//
	// if (p != null) {
	// // Create a new instance, and set it back on the parent
	// child.target = cls.newInstance();
	// child.fields = WbxmlUtil.getFields(child.target);
	//
	// field.set(parentEntry.target, child.target);
	// } else {
	// if (Boolean.class.isAssignableFrom((Class<?>) genericType)) {
	// field.setAccessible(true);
	// field.set(parentEntry.target, Boolean.TRUE);
	// } else {
	// // Create a new entry with the field which
	// // need to be populated.
	// child.target = parentEntry.target;
	// child.fields = new Field[] { field };
	// }
	// }
	//
	// parseStack.push(child);
	// } else if (genericType instanceof ParameterizedType) {
	// final ParameterizedType ptype = (ParameterizedType) genericType;
	// final Type[] args = ptype.getActualTypeArguments();
	// final Type rawType = ptype.getRawType();
	//
	// if (Collection.class.isAssignableFrom((Class<?>) rawType)) {
	// field.setAccessible(true);
	// Collection<Object> collection = (Collection<Object>)
	// field.get(parentEntry.target);
	// if (collection == null) {
	// collection = new ArrayList<Object>();
	// field.set(parentEntry.target, collection);
	// }
	//
	// Class<?> gtype = (Class<?>) args[0];
	//
	// final ParseStackEntry entry = new ParseStackEntry();
	// final Class<?> modelClass = cp.getModelClass();
	//
	// if (String.class.equals(gtype)) {
	// entry.target = collection;
	// } else {
	// if (WbxmlValue.class.equals(gtype)) {
	// entry.target = new WbxmlValue(cp);
	// } else {
	// entry.target = modelClass == null ? gtype.newInstance() :
	// modelClass.newInstance();
	// }
	// collection.add(entry.target);
	// }
	//
	// entry.fields = WbxmlUtil.getFields(modelClass == null ? gtype :
	// modelClass);
	// parseStack.push(entry);
	// }
	// }
	// }
	//
	// protected void doEndElement(final WbxmlContext cntx, final WbxmlEvent
	// event, final Stack<ParseStackEntry> parseStack) {
	// final ParseStackEntry entry = parseStack.pop();
	// final WbxmlCodePageField cp = event.getField();
	// if (log.isDebugEnabled()) {
	// log.debug("EndElement :" + cp.getFieldName() );
	// }
	// }
	//
	// protected void doText(final WbxmlContext cntx, final WbxmlEvent event,
	// final Stack<ParseStackEntry> parseStack) throws Exception {
	// final WbxmlCodePageField cp = event.getField();
	// final String text = event.getSourceAsWbxmlDecoder().text();
	//
	// final ParseStackEntry parent = parseStack.peek();
	//
	// if (parent.target instanceof Collection<?>) {
	// Collection<String> c = (Collection<String>) parent.target;
	// c.add(text);
	// } else {
	// final Field field = parent.findField(cp);
	// if (field != null) {
	// field.setAccessible(true);
	// field.set(parent.target, text);
	// }
	// }
	// }
	//
	// protected void doOpaque(final WbxmlContext cntx, final WbxmlEvent event,
	// final Stack<ParseStackEntry> parseStack) throws Exception {
	// final WbxmlCodePageField cp = event.getField();
	// final byte[] opaque = event.getSourceAsWbxmlDecoder().opaque();
	//
	// final ParseStackEntry entry = parseStack.peek();
	//
	// // if the target field is a string, then we convert the opaque data.
	// final Field field = entry.findField(cp);
	// if (field == null) {
	// if (log.isDebugEnabled()) {
	// log.warn("Failed to locate a mapping for field :" + cp);
	// }
	// if (entry.target instanceof WbxmlValue) {
	// ((WbxmlValue)entry.target).setValue(opaque);
	// } else {
	// Collection<String> c = (Collection<String>) entry.target;
	// c.add(new String(opaque));
	// }
	// } else {
	// final Type t = field.getType();
	// if (t instanceof Class<?>) {
	// final Class<?> clz = (Class<?>) t;
	//
	// if (String.class.equals(clz)) {
	// // We'll have to take a flyer on this, as we only have
	// // the target field to go by.
	// if (field != null) {
	// field.setAccessible(true);
	// field.set(entry.target, new String(opaque));
	// }
	// } else if (Object.class.equals(clz)) {
	// if (field != null) {
	// field.setAccessible(true);
	// field.set(entry.target, isDocument(opaque) ? opaque : new
	// String(opaque));
	// }
	// } else {
	// // Create the object type
	// }
	// }
	// }
	// }
	//
	// private boolean isDocument(byte[] data) {
	// try {
	// final WbxmlDecoder decoder = new WbxmlDecoder(data, null);
	// decoder.next();
	// } catch (WbxmlDecoderException e) {
	// return false;
	// }
	//
	// return true;
	// }
	//
	// private void pushCodePage(WbxmlContext cntx, OutputStream os, WbxmlPage
	// page) throws IOException {
	// WbxmlEncoder.switchCodePage(os, page.index());
	// cntx.pushCodePage(page);
	// }
	//
	// private void popCodePage(WbxmlContext cntx, OutputStream os) throws
	// IOException {
	// final WbxmlPage popped = cntx.popCodePage();
	// final WbxmlPage peek = cntx.peekCodePage();
	//
	// WbxmlEncoder.switchCodePage(os, peek.index());
	// }
	//
	// protected WbxmlPage getPage(Class<?> clazz) throws
	// WbxmlMarshallerException {
	// final WbxmlPage page = clazz.getAnnotation(WbxmlPage.class);
	//
	// if (page == null) {
	// throw new
	// WbxmlMarshallerException("Object has not been annotated with a @WbxmlPage");
	// }
	//
	// return page;
	// }
	//
	// /**
	// * Write out the wbxml preamble.
	// */
	// protected void writePreamble(final WbxmlContext cntx, final OutputStream
	// os, final WbxmlPage page) throws IOException {
	// int version = cntx.getWbxmlVersion();
	// int encoding = cntx.getWbxmlEncoding();
	// final int publicId = page.publicId();
	//
	// if (version == 0) {
	// if (log.isWarnEnabled()) {
	// log.warn("No WBXML version specified in context, default to 1.2");
	// }
	// version = WbxmlConstants.WBXML_VERSION_1_2;
	// }
	//
	// if (publicId == 0) {
	// if (log.isWarnEnabled()) {
	// log.warn("Unknown public id for document, recipient may reject");
	// }
	// }
	//
	// if (encoding == 0) {
	// if (log.isWarnEnabled()) {
	// log.warn("Unspecified document encoding, falling back to UTF-8");
	// }
	// encoding = WbxmlConstants.WBXML_ENCODING_UTF8;
	// }
	//
	// WbxmlEncoder.writeWbxmlVersion(os, version);
	// WbxmlEncoder.writePublicId(os, publicId);
	// WbxmlEncoder.writeEncoding(os, encoding);
	//
	// // TODO : Add support in the context for creating a string table.
	// // This will require all strings to be cached then referenced into this
	// // table.
	// WbxmlEncoder.writeStringTable(os, 0);
	//
	// }
	//
	// protected boolean matchFilter(final WbxmlField wbxmlField, final
	// String... filters) {
	// if (wbxmlField != null) {
	// if (filters == null || filters.length > 0) {
	// boolean match = false;
	// final String[] wbxmlFilters = wbxmlField.filters();
	// for (String wbxmlFilter : wbxmlFilters) {
	// for (String filter : filters) {
	// if (wbxmlFilter.equals(filter)) {
	// match = true;
	// break;
	// }
	// }
	// }
	// // No filters match.
	// if (!match) {
	// return false;
	// }
	// }
	// }
	//
	// return true;
	// }
	//
	// protected void processField(final WbxmlContext cntx, final OutputStream
	// os, final Field field, final Object target, final String... filters)
	// throws WbxmlMarshallerException, IOException {
	//
	// if (log.isDebugEnabled()) {
	// log.debug("processField field = " + field);
	// }
	//
	// field.setAccessible(true);
	//
	// final WbxmlField wbxmlField = field.getAnnotation(WbxmlField.class);
	// final boolean ghostElement = wbxmlField.index() == WbxmlField.NO_INDEX;
	// if (ghostElement) {
	// if (log.isDebugEnabled()) {
	// log.debug("ghost element :" + wbxmlField);
	// }
	// }
	//
	// final Type fieldType = field.getGenericType();
	// // final Class<?>[] validClasses = wbxmlField.classes();
	//
	// Object value = null;
	// try {
	// value = field.get(target);
	// } catch (Exception e) {
	// throw new WbxmlMarshallerException(e);
	// }
	//
	// if (log.isDebugEnabled()) {
	// log.debug("Value of field (" + field + ") = " + value);
	// }
	//
	// if (value == null) {
	// if (wbxmlField != null && wbxmlField.required()) {
	// throw new WbxmlMarshallerException("Field (" + wbxmlField.name() +
	// "), is marked required but is null");
	// }
	// return;
	// } else if (Collection.class.isAssignableFrom(value.getClass())) {
	// final ParameterizedType ptype = (ParameterizedType) fieldType;
	//
	// // TODO :
	// //
	// // in the case of a collection of strings we need to
	// // wrap each entry in the defined wbxml field.
	// // Objects need to be marshalled.
	// //
	//
	// // We have a collection, marshal through the values;
	// final Collection<?> values = (Collection<?>) value;
	// if (wbxmlField.required() && values.isEmpty()) {
	// throw new WbxmlMarshallerException("Field (" + wbxmlField.name() +
	// "), is marked required but is empty");
	// }
	//
	// if (!ghostElement) {
	// WbxmlEncoder.pushElement(cntx, os, wbxmlField, true);
	// }
	//
	// WbxmlField innerField;
	// for (Object obj : values) {
	// innerField = obj.getClass().getAnnotation(WbxmlField.class);
	//
	// if (ghostElement && innerField != null) {
	// WbxmlEncoder.pushElement(cntx, os, innerField, true);
	// }
	//
	// if (obj instanceof String) {
	// if (cntx.isOpaqueStrings()) {
	// WbxmlEncoder.opaque(cntx, os, obj.toString().getBytes());
	// } else {
	// WbxmlEncoder.inlineString(cntx, os, obj.toString());
	// }
	//
	// } else {
	// doMarshal(cntx, os, obj, filters);
	// }
	//
	// if (ghostElement && innerField != null) {
	// WbxmlEncoder.popElement(cntx, os);
	// }
	// }
	// if (!ghostElement) {
	// WbxmlEncoder.popElement(cntx, os);
	// }
	// } else if (value instanceof byte[]) {
	// WbxmlEncoder.pushOpaque(cntx, os, wbxmlField, (byte[]) value);
	// } else if (value instanceof Boolean) {
	// WbxmlEncoder.pushElement(cntx, os, wbxmlField, false);
	// } else {
	// final Class<?> valueClass = value.getClass();
	//
	// if (!ghostElement) {
	// WbxmlEncoder.pushElement(cntx, os, wbxmlField, true);
	// }
	//
	// final WbxmlPage page = valueClass.getAnnotation(WbxmlPage.class);
	// if (page != null) {
	// doMarshal(cntx, os, value, filters);
	// } else {
	// if (cntx.isOpaqueStrings()) {
	// WbxmlEncoder.opaque(cntx, os, value.toString().getBytes());
	// } else {
	// WbxmlEncoder.inlineString(cntx, os, value.toString());
	// }
	// }
	//
	// if (!ghostElement) {
	// WbxmlEncoder.popElement(cntx, os);
	// }
	// }
	// }
	//
	// private static final class ParseStackEntry {
	// Object target;
	//
	// Field[] fields;
	//
	// ParseStackEntry() {
	// }
	//
	// ParseStackEntry(final Object target) {
	// fields = WbxmlUtil.getFields(target);
	// this.target = target;
	// }
	//
	// public Field findField(final WbxmlCodePageField cp) {
	// // Find order.
	// // 1) The codepage index matches.
	// // 2) The codepage model matches the classes declared on the field.
	// // 3) There is only one field, and it's an object field.
	// // 4) There is only one field, and it's been annotated with a class
	// WbxmlValue
	//			
	// for (Field f : fields) {
	// final WbxmlField wbxmlField = f.getAnnotation(WbxmlField.class);
	// final int idx = wbxmlField.index();
	// if (idx != -1 && idx == cp.getToken()) {
	// f.setAccessible(true);
	// return f;
	// } else {
	// // Check to see if the classes match
	// Class<?> modelClass = cp.getModelClass();
	//					
	// if (modelClass != null) {
	// for (Class c : wbxmlField.classes()) {
	// if (c.equals(modelClass)) {
	// return f;
	// }
	// }
	// }
	// }
	// }
	//			
	// // last gasp.....
	// if (fields.length == 1) {
	// // Object fields can contain any of the model entities.
	// Type t = fields[0].getType();
	// if (t.equals(Object.class)) {
	// return fields[0];
	// }
	//				
	// final WbxmlField wbxmlField = fields[0].getAnnotation(WbxmlField.class);
	// Class<?>[] classes = wbxmlField.classes();
	// for (Class<?> cls : classes) {
	// if (WbxmlValue.class.equals(cls)) {
	// return fields[0];
	// }
	// }
	// }
	//
	// return null;
	// }
	// }


	
	private static final class ParseStackEntry {
		private final Object target;

		private final Map<Integer, Field> fields;

		ParseStackEntry(final Object target) {
			fields = WbxmlUtil.getFieldsMappedByToken(target);
			this.target = target;
		}

		public boolean hasFields() {
			return fields.isEmpty();
		}
		
		public Field findField(final WbxmlCodePageField cp) {
			// Find order.
			// 1) The codepage index matches.
			// 2) The codepage model matches the classes declared on the field.
			// 3) There is only one field, and it's an object field.
			// 4) There is only one field, and it's been annotated with a WbxmlField
			final int token = cp.getToken();
			final Field fld = fields.get(token);
				
			
			
			
			
//			for (Field f : fields) {
//				final WbxmlField wbxmlField = f.getAnnotation(WbxmlField.class);
//				final int idx = wbxmlField.index();
//				if (idx != -1 && idx == cp.getToken()) {
//					f.setAccessible(true);
//					return f;
//				} else {
//					// Check to see if the classes match
//					Class<?> modelClass = cp.getModelClass();
//
//					if (modelClass != null) {
//						for (Class<?> c : wbxmlField.classes()) {
//							if (c.equals(modelClass)) {
//								return f;
//							}
//						}
//					}
//				}
//			}

			// last gasp.....
//			if (fields.length == 1) {
//				// Object fields can contain any of the model entities.
//				Type t = fields[0].getType();
//				if (t.equals(Object.class)) {
//					return fields[0];
//				}
//
//				final WbxmlField wbxmlField = fields[0].getAnnotation(WbxmlField.class);
//				Class<?>[] classes = wbxmlField.classes();
//				for (Class<?> cls : classes) {
//					if (WbxmlValue.class.equals(cls)) {
//						return fields[0];
//					}
//				}
//			}

			return null;
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
