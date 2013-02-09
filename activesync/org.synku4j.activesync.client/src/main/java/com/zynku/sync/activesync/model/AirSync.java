package com.zynku.sync.activesync.model;

import java.util.ArrayList;
import java.util.List;

public final class AirSync {

	private final List<AirSyncCollection> collections = new ArrayList<AirSyncCollection>();
	
	public AirSync() {
	}

	public List<AirSyncCollection> getCollections() {
		return collections;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((collections == null) ? 0 : collections.hashCode());
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
		AirSync other = (AirSync) obj;
		if (collections == null) {
			if (other.collections != null)
				return false;
		} else if (!collections.equals(other.collections))
			return false;
		return true;
	}
	
}
