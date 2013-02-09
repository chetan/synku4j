package com.zynku.sync.activesync.model.folderheirachy;

import java.util.ArrayList;
import java.util.List;

import com.zynku.sync.activesync.model.Folder;

public class FolderSyncResponse {
	
	private String syncKey;
	private String status;
	private final List<Folder> added = new ArrayList<Folder>();
	private final List<Folder> changed = new ArrayList<Folder>();
	private final List<Folder> removed = new ArrayList<Folder>();
	
	public FolderSyncResponse() {
	}

	public String getSyncKey() {
		return syncKey;
	}

	public void setSyncKey(String syncKey) {
		this.syncKey = syncKey;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Folder> getAdded() {
		return added;
	}

	public List<Folder> getChanged() {
		return changed;
	}

	public List<Folder> getRemoved() {
		return removed;
	}
}
