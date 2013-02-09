package org.synku4j.activesync.model.folderhierarchy;

import static org.synku4j.activesync.model.Constants.RESPONSE;

import java.util.List;

import org.synku4j.activesync.wbxml.codepage.FolderHierarchyCodePage;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;

/**
 * MS-ASCMD - 2.2.1.4.2.4
 * 
 * The <Changes> element is a container for changes to the folder hierarchy. It
 * is used in the FolderSync command response to update the client with folder
 * additions, deletions, and updates on the server. The server SHOULD maintain
 * the same set of folder data being returned across synchronization key 0, in
 * terms of <ServerId> and <DisplayName> mapping. However, if an error occurs,
 * the server can return a totally different set.
 * 
 * @author Jools Enticknap
 */
@WbxmlPage(index = FolderHierarchyCodePage.NAMESPACE_IDX, name = FolderHierarchyCodePage.NAMESPACE_NAME)
public class FolderSyncChanges {
	/**
	 * MS-ASCMD 2.2.1.4.2.5
	 * 
	 * The <Count> element is used in the FolderSync command response to list
	 * the number of added, deleted, and updated folders on the server since the
	 * last folder synchronization. These changes are listed in the <Changes>
	 * element. If there are no changes since the last folder synchronization, a
	 * <Count> of 0 is returned.
	 */
	@WbxmlField(name = "Count", required = true, index = 0x17, filters = { RESPONSE })
	private int count;

	/**
	 * MS-ASCMD 2.2.1.4. 2.6 
	 * 
	 * The <Delete> element is used in the FolderSync
	 * command response to specify that a folder on the 1 (required) server was
	 * deleted since the last folder synchronization.
	 */
	@WbxmlField(name = "Update", index = 0x11, filters = { RESPONSE })
	private List<FolderSyncUpdate> updates;

	/**
	 * 
	 */
	@WbxmlField(name = "Delete", index = 0x11, filters = { RESPONSE })
	private List<FolderSyncDelete> deletes;

	/**
	 * 
	 */
	@WbxmlField(name = "Add", index = 0x11, filters = { RESPONSE })
	private List<FolderSyncAdd> adds;

	public FolderSyncChanges() {
	}

}
