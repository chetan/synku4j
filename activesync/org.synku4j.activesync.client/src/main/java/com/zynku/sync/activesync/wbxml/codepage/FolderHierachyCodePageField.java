package com.zynku.sync.activesync.wbxml.codepage;

import com.zynku.sync.wbxml.CodePageField;

public enum FolderHierachyCodePageField implements CodePageField {
	Folders(0x05), Folder(0x06), DisplayName(0x07),
	ServerId(0x08),	ParentId(0x09),	Type(0x0a),
	Response(0x0b),	Status(0x0c),	ContentClass(0x0d),
	Changes(0x0e),	Add(0x0f),	Delete(0x10),
	Update(0x11),	SyncKey(0x12),	FolderCreate(0x13),
	FolderDelete(0x14),	FolderUpdate(0x15),	FolderSync(0x16),
	Count(0x17), Version(0x18);

	
	private final int idx;
	private static final String NAMESPACE_NAME = "FolderHierarchy";
	private static final int NAMESPACE_IDX = 0x07;
	
	FolderHierachyCodePageField(final int idx) {
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
