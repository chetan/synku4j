package org.synku4j.activesync.model.folderhierarchy;

import static org.synku4j.activesync.model.Constants.REQUEST;
import static org.synku4j.activesync.model.Constants.RESPONSE;

import org.synku4j.activesync.wbxml.codepage.FolderHierarchyCodePage;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;

/**
 * MS-ASCMD 2.2.1.4
 * 
 * FolderSync is used to sync the Folder Hierarachy, not the contents of the
 * folders.
 * 
 * This command works similarly to the Sync command. An initial FolderSync
 * command with a synchronization key of 0 (value of 0 in <SyncKey> element) is
 * required in order to obtain the list of folders and the synchronization key
 * associated with that list. The synchronization key MUST be returned in the
 * <SyncKey> element of the response. This synchronization key MUST be used in
 * subsequent FolderSync commands to obtain folder hierarchy changes. Unlike a
 * Sync request, there is no <GetChanges> element submitted in the FolderSync
 * request to get changes from the server. All folders MUST be returned to the
 * client when initial folder synchronization is done with a synchronization key
 * of 0.
 * 
 * 
 * @author Jools Enticknap
 */
@WbxmlPage(index = FolderHierarchyCodePage.NAMESPACE_IDX, name = FolderHierarchyCodePage.NAMESPACE_NAME)
//@WbxmlField(name="FolderSync", index=0x16)
public class FolderSync {

	/**
	 * MS-ASCMD - 2.2.1.4.1.2
	 * 
	 * After successful folder synchronization, the server MUST send a
	 * synchronization key to the client. The client MUST store this key and
	 * send the key back to the server the next time the folder hierarchy is
	 * synchronized or updated. The server MUST check the value of the key to
	 * make sure the value of the <SyncKey> provided in the request matches a
	 * <SyncKey> value on the server. The server MUST provide an error if the
	 * <SyncKey> values do not match.
	 * */
	@WbxmlField(name = "SyncKey", limit = 64, index = 0x12, filters = { REQUEST, RESPONSE })
	private String syncKey;

	/**
	 * MS-ASCMD - 2.2.1.4.2.2
	 * 
	 * If the command fails, the <Status> element contains a code that indicates
	 * the type of failure. The <Status> element is global for all collections.
	 * If one collection fails, a failure status MUST be returned for all
	 * collections.
	 */
	@WbxmlField(name = "Status", limit = 1, index = 0x0c, filters = { RESPONSE })
	private FolderSyncStatus status;

	/**
	 * MS-ASCMD - 2.2.1.4.2.4 
	 * 
	 * The <Changes> element is a container for changes to
	 * the folder hierarchy. It is used in the FolderSync command response to
	 * update the client with folder additions, deletions, and updates on the
	 * server. The server SHOULD maintain the same set of folder data being
	 * returned across synchronization key 0, in terms of <ServerId> and
	 * <DisplayName> mapping. However, if an error occurs, the server can return
	 * a totally different set.
	 */
	@WbxmlField(name = "Changes", index = 0x0e, filters = { RESPONSE })
	private FolderSyncChanges changes;

	public FolderSync() {
	}
	
	
	public FolderSync(final String syncKey) {
		this.syncKey = syncKey;
	}

	public String getSyncKey() {
		return syncKey;
	}

	public void setSyncKey(String syncKey) {
		this.syncKey = syncKey;
	}

	public FolderSyncStatus getStatus() {
		return status;
	}

	public void setStatus(FolderSyncStatus status) {
		this.status = status;
	}

	public FolderSyncChanges getChanges() {
		return changes;
	}

	public void setChanges(FolderSyncChanges changes) {
		this.changes = changes;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((changes == null) ? 0 : changes.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((syncKey == null) ? 0 : syncKey.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof FolderSync)) {
			return false;
		}
		FolderSync other = (FolderSync) obj;
		if (changes == null) {
			if (other.changes != null) {
				return false;
			}
		} else if (!changes.equals(other.changes)) {
			return false;
		}
		if (status == null) {
			if (other.status != null) {
				return false;
			}
		} else if (!status.equals(other.status)) {
			return false;
		}
		if (syncKey == null) {
			if (other.syncKey != null) {
				return false;
			}
		} else if (!syncKey.equals(other.syncKey)) {
			return false;
		}
		return true;
	}


	@Override
	public String toString() {
		return "FolderSync [changes=" + changes + ", status=" + status + ", syncKey=" + syncKey + "]";
	}
}
