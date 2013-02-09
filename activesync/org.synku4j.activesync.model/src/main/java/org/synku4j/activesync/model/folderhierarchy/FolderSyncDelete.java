package org.synku4j.activesync.model.folderhierarchy;

import static org.synku4j.activesync.model.Constants.RESPONSE;

import org.synku4j.activesync.wbxml.codepage.FolderHierarchyCodePage;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;

/**
 * 
 * @author Jools Enticknap
 */
@WbxmlPage(index = FolderHierarchyCodePage.NAMESPACE_IDX, name = FolderHierarchyCodePage.NAMESPACE_NAME)
public class FolderSyncDelete {
	/**
	 * MS-ASCMD - 2.2.1.4.2.8
	 * 
	 * The <ServerId> element specifies the server-unique identifier for a
	 * folder on the server.
	 */
	@WbxmlField(name = "ServerId", required = true, limit = 64, index = 0x08, filters = { RESPONSE })
	private String serverId;

	FolderSyncDelete() {
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
}
