package org.synku4j.activesync.wbxml.codepage;

import org.synku4j.wbxml.core.WbxmlCodePageField;

public enum FolderHierarchyCodePage implements WbxmlCodePageField {
    Folders(0x05), Folder(0x06), DisplayName(0x07),
    ServerId(0x08), ParentId(0x09), Type(0x0a),
    Response(0x0b), Status(0x0c),   ContentClass(0x0d),
    Changes(0x0e),  Add(0x0f),      Delete(0x10),
    Update(0x11),   SyncKey(0x12),  FolderCreate(0x13),
    FolderDelete(0x14),     FolderUpdate(0x15),     FolderSync(0x16),
    Count(0x17), Version(0x18);

    private final int idx;
    public static final String NAMESPACE_NAME = "FolderHierarchy";
    public static final int NAMESPACE_IDX = 0x07;
    public static final String INITIAL_SYNC_KEY = "0";

	FolderHierarchyCodePage(final int idx) {
		this.idx = idx;
	}

	public int getToken() {
		return idx;
	}

	public String getFieldName() {
		return name();
	}

	public String getNameSpace() {
		return NAMESPACE_NAME;
	}

	public int getPage() {
		return NAMESPACE_IDX;
	}

	@Override
	public Class<?> getModelClass() {
		return null;
	}

}
