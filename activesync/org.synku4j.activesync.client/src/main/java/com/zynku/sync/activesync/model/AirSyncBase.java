package com.zynku.sync.activesync.model;

/**
 * The <code>AirSyncBase</code> class
 * 
 * see MS-ASAIRS 
 * 
 * @author Jools Enticknap
 */
public final class AirSyncBase {
	
	private AirSyncBaseBodyPreference bodyPreference;
	
	public AirSyncBase() {
	}

	public AirSyncBaseBodyPreference getBodyPreference() {
		return bodyPreference;
	}

	public void setBodyPreference(AirSyncBaseBodyPreference bodyPreference) {
		this.bodyPreference = bodyPreference;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bodyPreference == null) ? 0 : bodyPreference.hashCode());
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
		if (!(obj instanceof AirSyncBase)) {
			return false;
		}
		AirSyncBase other = (AirSyncBase) obj;
		if (bodyPreference == null) {
			if (other.bodyPreference != null) {
				return false;
			}
		} else if (!bodyPreference.equals(other.bodyPreference)) {
			return false;
		}
		return true;
	}
}
