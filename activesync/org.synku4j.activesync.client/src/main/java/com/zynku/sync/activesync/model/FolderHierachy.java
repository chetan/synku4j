package com.zynku.sync.activesync.model;

import java.util.ArrayList;
import java.util.List;

public class FolderHierachy {
	private String status;
	private String syncKey;
	private final List<Folder> added = new ArrayList<Folder>();
	private final List<Folder> deleted = new ArrayList<Folder>();
	private final List<Folder> updated = new ArrayList<Folder>();

	public FolderHierachy() {
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSyncKey() {
		return syncKey;
	}

	public void setSyncKey(String syncKey) {
		this.syncKey = syncKey;
	}

	public List<Folder> getAdded() {
		return added;
	}

	public List<Folder> getDeleted() {
		return deleted;
	}

	public List<Folder> getUpdated() {
		return updated;
	}

	public boolean hasFolders() {
		return !updated.isEmpty() || !deleted.isEmpty() || !added.isEmpty();
	}

	public int getFolderCount() {
		return updated.size() + deleted.size() + added.size();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((added == null) ? 0 : added.hashCode());
		result = prime * result + ((deleted == null) ? 0 : deleted.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((syncKey == null) ? 0 : syncKey.hashCode());
		result = prime * result + ((updated == null) ? 0 : updated.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FolderHierachy other = (FolderHierachy) obj;
		if (added == null) {
			if (other.added != null)
				return false;
		} else if (!added.equals(other.added))
			return false;
		if (deleted == null) {
			if (other.deleted != null)
				return false;
		} else if (!deleted.equals(other.deleted))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (syncKey == null) {
			if (other.syncKey != null)
				return false;
		} else if (!syncKey.equals(other.syncKey))
			return false;
		if (updated == null) {
			if (other.updated != null)
				return false;
		} else if (!updated.equals(other.updated))
			return false;
		return true;
	}

}
