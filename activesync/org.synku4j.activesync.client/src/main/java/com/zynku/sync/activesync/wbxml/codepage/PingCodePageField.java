package com.zynku.sync.activesync.wbxml.codepage;

import com.zynku.sync.wbxml.CodePageField;

public enum PingCodePageField implements CodePageField {
	Ping(0x05), AutdState(0x06), /* Not used by protocol */
	Status(0x07), HeartbeatInterval(0x08), Folders(0x09), 
	Folder(0x0a), Id(0x0b), Class(0x0c), MaxFolders(0x0d);

	private final int idx;
	private static final String NAMESPACE_NAME = "Ping";
	private static final int NAMESPACE_IDX = 0x0d;
	
	PingCodePageField(final int idx) {
		this.idx = idx;
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
