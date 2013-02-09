package com.zynku.sync.activesync.wbxml.codepage;

import com.zynku.sync.wbxml.CodePageField;

public enum ValidateCertCodePageField implements CodePageField  {

	ValidateCert(0x05),
	Certificates(0x06),
    Certificate(0x07),
    CertificateChain(0x08),
    CheckCRL(0x09),
    Status(0x0a);
	
	private final int idx;
	
	ValidateCertCodePageField(final int idx) {
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
		return "ValidateCert";
	}

	public int getNameSpaceIndex() {
		return 0x0b;
	}
}