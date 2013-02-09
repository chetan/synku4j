package org.synku4j.activesync.model.folderhierarchy;

import org.synku4j.activesync.wbxml.codepage.FolderHierarchyCodePage;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;
import static org.synku4j.activesync.model.Constants.REQUEST;

@WbxmlPage(index=FolderHierarchyCodePage.NAMESPACE_IDX, name=FolderHierarchyCodePage.NAMESPACE_NAME)
public class FolderHeirarchyFolder {
	
	@WbxmlField(name="ServerId", index=0x08, filters={ REQUEST } )
	private String serverId;
	
	@WbxmlField(name="ParentId", index=0x09, filters={ REQUEST } )
	private String parentId;
	
	@WbxmlField(name="DisplayName", index=0x07, filters={ REQUEST } )
	private String displayName;
	
	
	public FolderHeirarchyFolder() {
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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result + ((serverId == null) ? 0 : serverId.hashCode());
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
		if (!(obj instanceof FolderHeirarchyFolder)) {
			return false;
		}
		FolderHeirarchyFolder other = (FolderHeirarchyFolder) obj;
		if (displayName == null) {
			if (other.displayName != null) {
				return false;
			}
		} else if (!displayName.equals(other.displayName)) {
			return false;
		}
		if (parentId == null) {
			if (other.parentId != null) {
				return false;
			}
		} else if (!parentId.equals(other.parentId)) {
			return false;
		}
		if (serverId == null) {
			if (other.serverId != null) {
				return false;
			}
		} else if (!serverId.equals(other.serverId)) {
			return false;
		}
		return true;
	}
	
}
