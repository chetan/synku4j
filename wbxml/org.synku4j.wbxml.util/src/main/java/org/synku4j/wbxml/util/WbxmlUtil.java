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

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;
import org.synku4j.wbxml.core.WbxmlCodePage;
import org.synku4j.wbxml.core.WbxmlCodePageField;

/**
 * The <code>WbxmlUtil</code> class.
 * 
 * @author Jools Enticknap
 * @version $Revision:$
 */
public final class WbxmlUtil {

	private static final Log log = LogFactory.getLog(WbxmlUtil.class);

	private WbxmlUtil() {
	}

	public static WbxmlCodePageField createCodePageField(final int index, final String name, final int codepage) {
		return new WbxmlCodePageFieldWrapper(codepage, index, name, null);
	}
	
	public static WbxmlPage getWbxmlPage(final Object source) {
		return source.getClass().getAnnotation(WbxmlPage.class);
	}

	/**
	 * Get the fields from the source object which are annotated with the
	 * <code>WbxmlField</code> class.
	 * 
	 * Note, this search from the bottom up, which means fields in the base class
	 * are found before those in the derived class.
	 * 
	 * NOTE: We should do some name clashing checks here, duplicate fields in classes
	 *       will more than likely screw up the marshaling.
	 * 
	 * @param source the source object.
	 * @return the annotated fields.
	 */
	public static Field[] getFields(final Object source) {
		if (source == null) {
			throw new NullPointerException("source object cannot be null");
		}

		final ArrayList<Class<?>> heirachy = new ArrayList<Class<?>>();
		Class<? extends Object> clazz = (source instanceof Class<?>) ? (Class<?>) source : source.getClass();
		do {
			heirachy.add(clazz);
			clazz = clazz.getSuperclass();
		} while (!Object.class.equals(clazz));

		final List<Field> fields = new LinkedList<Field>();

		// from the bottom up scan.
		final ListIterator<Class<?>> liter = heirachy.listIterator(heirachy.size());
		while (liter.hasPrevious()) {
			clazz = liter.previous();
			for (Field field : clazz.getDeclaredFields()) {
				final WbxmlField wbxmlField = field.getAnnotation(WbxmlField.class);
				if (wbxmlField != null) {
					fields.add(field);
				}
			}
		}
		
		if (log.isDebugEnabled()) {
			log.debug("Object class :"+source+" has ("+fields.size()+") annotated fields");
		}

		return fields.toArray(new Field[0]);
	}

	public static Map<Integer, Field> getFieldsMappedByToken(final Object source) {
		final Map<Integer, Field> fieldMap = new HashMap<Integer, Field>();
		
		if (source == null) {
			throw new NullPointerException("source object cannot be null");
		}

		final ArrayList<Class<?>> heirachy = new ArrayList<Class<?>>();
		Class<? extends Object> clazz = (source instanceof Class<?>) ? (Class<?>) source : source.getClass();
		do {
			heirachy.add(clazz);
			clazz = clazz.getSuperclass();
		} while (!Object.class.equals(clazz));


		// from the bottom up scan.
		final ListIterator<Class<?>> liter = heirachy.listIterator(heirachy.size());
		while (liter.hasPrevious()) {
			clazz = liter.previous();
			for (Field field : clazz.getDeclaredFields()) {
				final WbxmlField wbxmlField = field.getAnnotation(WbxmlField.class);
				if (wbxmlField != null) {
					fieldMap.put(wbxmlField.index(), field);
				}
			}
		}
		
		if (log.isDebugEnabled()) {
			log.debug("Object class :"+source+" has ("+fieldMap.size()+") annotated fields");
		}
		
		return fieldMap;
	}
	
	/**
	 * Introspect the given class, extracting all the codepage information, populating the supplied collection.
	 * 
	 * @param clazz to introspect.
	 * @param pages to populate.
	 */
	public static void introspect(final Class<?> clazz, final Collection<WbxmlCodePage> pages) {
		final WbxmlPage page = clazz.getAnnotation(WbxmlPage.class);
		if (page == null) {
			// no page means we skip it.
			return;
		}

		if (page.index() == -1) {
			return;
		}

		WbxmlField root = clazz.getAnnotation(WbxmlField.class);
		WbxmlCodePageWrapper codePage = null;
		// TODO : For the introspect use a Map, then collate to collection
		for (WbxmlCodePage cp : pages) {
			if (cp.getIndex() == page.index()) {
				codePage = (WbxmlCodePageWrapper) cp;
				break;
			}
		}

		if (codePage == null) {
			codePage = new WbxmlCodePageWrapper(page);
			pages.add(codePage);
		}
		
		if (root != null) {
			codePage.addCodePageField(new WbxmlCodePageFieldWrapper(page, root, clazz));
		}

		// Parse from the root down
		for (Field field : WbxmlUtil.getFields(clazz)) {
			final WbxmlField wbxmlField = field.getAnnotation(WbxmlField.class);
			final Type type = field.getGenericType();
			if (type instanceof ParameterizedType) {
				ParameterizedType ptype = (ParameterizedType) type;
				Type rawType = ptype.getRawType();

				if (Collection.class.isAssignableFrom((Class<?>) rawType)) {
					Type[] args = ptype.getActualTypeArguments();
					if (args != null && args.length > 0) {
						for (Type arg : args) {
							if (arg instanceof Class<?>) {
								introspect((Class<?>) arg, pages);
							}
						}
					}
				}
			} else if (type instanceof Class<?>) {
				introspect((Class<?>) type, pages);
			}

			// if the class has a page, add it to the codepage.
			Class<?> modelClass = null;
			if (type instanceof Class<?>) {
				if (((Class<?>) type).getAnnotation(WbxmlPage.class) != null) {
					modelClass = (Class<?>) type;
				}
			}

			codePage.addCodePageField(new WbxmlCodePageFieldWrapper(page, wbxmlField, modelClass));
		}
	}
}
