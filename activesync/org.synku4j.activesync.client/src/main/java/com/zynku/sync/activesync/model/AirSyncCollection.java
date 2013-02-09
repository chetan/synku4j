package com.zynku.sync.activesync.model;

import java.util.ArrayList;
import java.util.List;

public final class AirSyncCollection {
	
	private String syncKey;
	private String collectionId;
	private String status;
	private Boolean changes;
	private Boolean moreAvailable;
	private Boolean deleteAsMoves;
	private Integer windowSize;
	private final List<AirSyncCommand> commands = new ArrayList<AirSyncCommand>();
	private AirSyncBase options;
	
	
	public AirSyncCollection() {
	}
	
	public Integer getWindowSize() {
		return windowSize;
	}

	public void setWindowSize(Integer windowSize) {
		this.windowSize = windowSize;
	}

	public AirSyncBase getOptions() {
		return options;
	}

	public void setOptions(AirSyncBase options) {
		this.options = options;
	}

	public String getSyncKey() {
		return syncKey;
	}

	public void setSyncKey(String syncKey) {
		this.syncKey = syncKey;
	}

	public String getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}
	
	public List<AirSyncCommand> getCommands() {
		return commands;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getChanges() {
		return changes;
	}

	public void setChanges(Boolean changes) {
		this.changes = changes;
	}

	public Boolean getDeleteAsMoves() {
		return deleteAsMoves;
	}

	public void setDeleteAsMoves(Boolean deleteAsMoves) {
		this.deleteAsMoves = deleteAsMoves;
	}
	
	public Boolean getMoreAvailable() {
		return moreAvailable;
	}

	public void setMoreAvailable(Boolean moreAvailable) {
		this.moreAvailable = moreAvailable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((changes == null) ? 0 : changes.hashCode());
		result = prime * result + ((collectionId == null) ? 0 : collectionId.hashCode());
		result = prime * result + ((commands == null) ? 0 : commands.hashCode());
		result = prime * result + ((deleteAsMoves == null) ? 0 : deleteAsMoves.hashCode());
		result = prime * result + ((moreAvailable == null) ? 0 : moreAvailable.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((syncKey == null) ? 0 : syncKey.hashCode());
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
		AirSyncCollection other = (AirSyncCollection) obj;
		if (changes == null) {
			if (other.changes != null)
				return false;
		} else if (!changes.equals(other.changes))
			return false;
		if (collectionId == null) {
			if (other.collectionId != null)
				return false;
		} else if (!collectionId.equals(other.collectionId))
			return false;
		if (commands == null) {
			if (other.commands != null)
				return false;
		} else if (!commands.equals(other.commands))
			return false;
		if (deleteAsMoves == null) {
			if (other.deleteAsMoves != null)
				return false;
		} else if (!deleteAsMoves.equals(other.deleteAsMoves))
			return false;
		if (moreAvailable == null) {
			if (other.moreAvailable != null)
				return false;
		} else if (!moreAvailable.equals(other.moreAvailable))
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
		return true;
	}
}
