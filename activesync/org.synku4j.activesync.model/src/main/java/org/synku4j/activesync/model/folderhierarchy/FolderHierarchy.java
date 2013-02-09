package org.synku4j.activesync.model.folderhierarchy;

import org.synku4j.activesync.wbxml.codepage.FolderHierarchyCodePage;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;


@WbxmlPage(index=FolderHierarchyCodePage.NAMESPACE_IDX, name=FolderHierarchyCodePage.NAMESPACE_NAME)
public final class FolderHierarchy {

	@WbxmlField(name="SyncKey", index=0x0c, limit=64, filters={ "response", "request" } )
	private String syncKey;
	
	@WbxmlField(name="Status", index=0x12, filters={ "response" } )
	private FolderHierarchyStatus status;
	
	
	public FolderHierarchy() {
	}

	public FolderHierarchyStatus getStatus() {
		return status;
	}

	public void setStatus(FolderHierarchyStatus status) {
		this.status = status;
	}

	public String getSyncKey() {
		return syncKey;
	}

	public void setSyncKey(String syncKey) {
		this.syncKey = syncKey;
	}
}
