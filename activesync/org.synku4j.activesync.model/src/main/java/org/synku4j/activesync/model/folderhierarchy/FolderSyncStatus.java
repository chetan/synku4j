package org.synku4j.activesync.model.folderhierarchy;

import org.synku4j.activesync.wbxml.codepage.FolderHierarchyCodePage;
import org.synku4j.wbxml.annotations.WbxmlPage;

/**
 * MS-ASCMD - 2.2.1.4. 2.2
 * 
 * If the command fails, the <Status> element contains a code that indicates the
 * type of failure. The <Status> element is global for all collections. If one
 * collection fails, a failure status MUST be returned for all collections.
 * 
 * 
 * @author Jools Enticknap
 */
@WbxmlPage(index = FolderHierarchyCodePage.NAMESPACE_IDX, name = FolderHierarchyCodePage.NAMESPACE_NAME)
public enum FolderSyncStatus {
	SUCCESS(1), SERVER_ERROR(6), TIMEOUT(8), SYNC_KEY_MISSMATCH(9), BAD_REQUEST(10), UNKNOWN(11);

	private final int code;

	FolderSyncStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
