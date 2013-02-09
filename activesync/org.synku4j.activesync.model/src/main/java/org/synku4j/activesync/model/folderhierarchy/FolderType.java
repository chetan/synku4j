package org.synku4j.activesync.model.folderhierarchy;

import org.synku4j.activesync.wbxml.codepage.FolderHierarchyCodePage;
import org.synku4j.wbxml.annotations.WbxmlPage;

@WbxmlPage(index = FolderHierarchyCodePage.NAMESPACE_IDX, name = FolderHierarchyCodePage.NAMESPACE_NAME)
public enum FolderType {
	USER_FOLDER(1),
	DEFAULT_INBOX(2),
	DEFAULT_DRAFTS(3),
	DEFAULT_DELETED_ITEMS(4),
	DEFAULT_SENT_ITEMS(5),
	DEFAULT_OUTBOX(6),
	DEFAULT_TASKS(7),
	DEFAULT_CALENDAR(8),
	DEFAULT_CONTACTS(9),
	DEFAULT_NOTES(10),
	DEFAULT_JOURNAL(11),
	USER_MAIL(12),
	USER_CALENDAR(13),
	USER_CONTACTS(14),
	USER_TASKS(15),
	USER_JOURNAL(16),
	USER_NOTES(17),
	UNKNOWN(18),
	RECIPIANT_INFORMATION_CACHE(19)
	;
	
	private final int code;
	
	FolderType(final int code) {
		this.code = code; 
	}
	
	public int getCode() {
		return code;
	}
}
