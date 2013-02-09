package com.zynku.sync.activesync.wbxml.codepage;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zynku.sync.wbxml.CodePageField;
import com.zynku.sync.wbxml.CodePageLookup;

public final class ActiveSyncCodePageLookup implements CodePageLookup {
	
	private static final Log log = LogFactory.getLog(ActiveSyncCodePageLookup.class);

	private static final Map<Integer, Map<Integer, CodePageField>> FIELDBYID = new HashMap<Integer, Map<Integer, CodePageField>>();
	private static final Map<Integer, Map<String, CodePageField>> FIELDBYNAME = new HashMap<Integer, Map<String, CodePageField>>();

	private static final ActiveSyncCodePageLookup INSTANCE = new ActiveSyncCodePageLookup();
	
	private ActiveSyncCodePageLookup() {
	}
	
	public CodePageField lookup(final int codePage, final int fieldId) {
		final Map<Integer, CodePageField> byFieldId = FIELDBYID.get(codePage);
		if (byFieldId == null) {
			throw new IllegalStateException("Unknown codepage (0x"+Integer.toString(codePage, 16)+"), field (0x"+Integer.toString(fieldId)+")");
		}
		
		return FIELDBYID.get(codePage).get(fieldId);
	}

	public CodePageField lookup(final int codePage, final String fieldName) {
		return FIELDBYNAME.get(codePage).get(fieldName);
	}


	protected static void add(final CodePageField fld) {
		final int namespaceidx = fld.getNameSpaceIndex();
		
		Map<Integer, CodePageField> byId = FIELDBYID.get(namespaceidx);
		if (byId == null) {
			byId = new HashMap<Integer, CodePageField>();
			FIELDBYID.put(namespaceidx, byId);
		}
		
		byId.put(fld.getCodePageIndex(), fld);
		
		
		Map<String, CodePageField> byName = FIELDBYNAME.get(namespaceidx);
		if (byName == null) {
			byName = new HashMap<String, CodePageField>();
			FIELDBYNAME.put(namespaceidx, byName);
		}
		
		byName.put(fld.getFieldName(), fld);
	}
	
	public static CodePageLookup getInstance() {
		return INSTANCE;
	}
}
