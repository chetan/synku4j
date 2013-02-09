package org.synku4j.wbxml.decoder;
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



import java.util.HashMap;
import java.util.Map;

import org.synku4j.wbxml.core.WbxmlCodePageField;
import org.synku4j.wbxml.core.WbxmlCodePageFinder;

public abstract class AbstractCodePageFinder implements WbxmlCodePageFinder {
	
	private Map<Integer, Map<Integer, WbxmlCodePageField>> codepageById = new HashMap<Integer, Map<Integer, WbxmlCodePageField>>();
	private Map<Integer, Map<String, WbxmlCodePageField>> codepageByName = new HashMap<Integer, Map<String, WbxmlCodePageField>>();

	protected AbstractCodePageFinder() {
	}

	@Override
	public WbxmlCodePageField find(int codePageIdx, int fieldId) {
		final Map<Integer, WbxmlCodePageField> m = codepageById.get(codePageIdx);
		if (m == null) {
			return null;
		}

		return m.get(fieldId);
	}
	
	@Override
	public WbxmlCodePageField find(int codePageIdx, String fieldName) {
		Map<String, WbxmlCodePageField> m = codepageByName.get(codePageIdx);
		if (m == null) {
			return null;
		}

		return m.get(fieldName);
	}
	
	protected void populate(int codePageIdx, WbxmlCodePageField[] fields) {
		final Map<Integer, WbxmlCodePageField> fieldMapId = new HashMap<Integer, WbxmlCodePageField>();
		final Map<String, WbxmlCodePageField> fieldMapName = new HashMap<String, WbxmlCodePageField>();

		for (WbxmlCodePageField field : fields) {
			fieldMapId.put(field.getToken(), field);
			fieldMapName.put(field.getFieldName(), field);
		}

		codepageById.put(codePageIdx, fieldMapId);
		codepageByName.put(codePageIdx, fieldMapName);
	}
}
