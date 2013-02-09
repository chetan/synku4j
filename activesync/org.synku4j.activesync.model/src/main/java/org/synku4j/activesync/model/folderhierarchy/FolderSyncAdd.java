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
public class FolderSyncAdd {
	/**
	 * MS-ASCMD - 2.2.1.4.2.8
	 * 
	 * The <ServerId> element specifies the server-unique identifier for a
	 * folder on the server.
	 */
	@WbxmlField(name = "ServerId", required = true, limit = 64, index = 0x08, filters = { RESPONSE })
	private String serverId;

	/**
	 * MS-ASCMD 2.2.1.4.2.9
	 * 
	 * The <ParentId> element specifies the server ID of the parent of the
	 * folder on the server that has been added or updated.
	 */
	@WbxmlField(name = "ParentId", required = true, limit = 64, index = 0x09, filters = { RESPONSE })
	private String parentId;

	/**
	 * MS-ASCMD 2.2.1.4.2.11
	 * 
	 * The <Type> element specifies the type of the folder that was added or
	 * updated (renamed or moved) on the server.
	 */
	@WbxmlField(name = "Type", required = true, limit = 1, index = 0x0a, filters = { RESPONSE })
	private FolderType type;

	/**
	 * MS-ASCMD 2.2.1.4.2.10 
	 * 
	 * The <DisplayName> element specifies the name of
	 * the folder that is shown to the user.
	 */
	@WbxmlField(name = "DisplayName", required = true, index = 0x07, filters = { RESPONSE })
	private String displayName;

	public FolderSyncAdd() {
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public FolderType getType() {
		return type;
	}

	public void setType(FolderType type) {
		this.type = type;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
}
