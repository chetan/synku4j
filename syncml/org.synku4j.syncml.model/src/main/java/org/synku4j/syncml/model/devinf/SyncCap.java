package org.synku4j.syncml.model.devinf;

import java.util.Collection;

import org.synku4j.syncml.wbxml.codepage.DevInfCodePage;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;

@WbxmlPage(index=DevInfCodePage.NAMESPACE_IDX, name=DevInfCodePage.NAMESPACE_NAME)
@WbxmlField(name="SyncCap", index=0x1F)
public class SyncCap {
	@WbxmlField(name="SyncType", index=0x20)	
	private Collection<String>  syncType;
	
	public SyncCap() {
	}

	public Collection<String> getSyncType() {
		return syncType;
	}

	public void setSyncType(Collection<String> syncType) {
		this.syncType = syncType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((syncType == null) ? 0 : syncType.hashCode());
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
		if (!(obj instanceof SyncCap)) {
			return false;
		}
		SyncCap other = (SyncCap) obj;
		if (syncType == null) {
			if (other.syncType != null) {
				return false;
			}
		} else if (!syncType.equals(other.syncType)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "SyncCap [syncType=" + syncType + "]";
	}
}
