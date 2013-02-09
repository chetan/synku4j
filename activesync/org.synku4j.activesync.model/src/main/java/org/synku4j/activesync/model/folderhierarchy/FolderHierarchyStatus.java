package org.synku4j.activesync.model.folderhierarchy;

public enum FolderHierarchyStatus {
	SUCCESS(1),
	SERVER_ERROR(6),
	TIMEOUT(8),
	SYNCKEY_MISMATCH(9),
	BAD_REQUEST(10),
	UNKNOWN_ERROR(11);
	
	private final int code;
	
	FolderHierarchyStatus(final int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
}
