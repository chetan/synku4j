package com.zynku.sync.activesync.wbxml.codepage;

import com.zynku.sync.wbxml.CodePageField;

public enum Contacts2CodePageField implements CodePageField {
	CustomerId(0x05), GovernmentId(0x06), IMAddress(0x07),
    IMAddress2(0x08), IMAddress3(0x09), ManagerName(0x0a),
    CompanyMainPhone(0x0b), AccountName(0x0c), NickName(0x0d),
    MMS(0x0e);

	private final int idx;
	private static final String NAMESPACE_NAME = "Contacts2";
	private static final int NAMESPACE_IDX = 0x0c;
	
	Contacts2CodePageField(final int idx) {
		this.idx = idx;
		ActiveSyncCodePageLookup.add(this);
	}
	
	public int getCodePageIndex() {
		return idx;
	}

	public String getFieldName() {
		return name();
	}

	public String getNameSpace() {
		return NAMESPACE_NAME;
	}

	public int getNameSpaceIndex() {
		return NAMESPACE_IDX;
	}
}
