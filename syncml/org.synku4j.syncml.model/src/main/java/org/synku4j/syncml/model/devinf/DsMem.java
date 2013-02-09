package org.synku4j.syncml.model.devinf;

import org.synku4j.syncml.wbxml.codepage.DevInfCodePage;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;

@WbxmlPage(index=DevInfCodePage.NAMESPACE_IDX, name=DevInfCodePage.NAMESPACE_NAME)
@WbxmlField(name="DsMem", index=0x0D)
public class DsMem {
	@WbxmlField(index=0x1B, name="SharedMem")
	private Boolean sharedMem;
	
	@WbxmlField(index=0x14, name="MaxMem")
	private String maxMem;
	
	@WbxmlField(index=0x13, name="MaxID")
	private String maxID;

	public DsMem() {
	}

	public Boolean getSharedMem() {
		return sharedMem;
	}

	public void setSharedMem(Boolean sharedMem) {
		this.sharedMem = sharedMem;
	}

	public String getMaxMem() {
		return maxMem;
	}

	public void setMaxMem(String maxMem) {
		this.maxMem = maxMem;
	}

	public String getMaxID() {
		return maxID;
	}

	public void setMaxID(String maxID) {
		this.maxID = maxID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maxID == null) ? 0 : maxID.hashCode());
		result = prime * result + ((maxMem == null) ? 0 : maxMem.hashCode());
		result = prime * result + ((sharedMem == null) ? 0 : sharedMem.hashCode());
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
		if (!(obj instanceof DsMem)) {
			return false;
		}
		DsMem other = (DsMem) obj;
		if (maxID == null) {
			if (other.maxID != null) {
				return false;
			}
		} else if (!maxID.equals(other.maxID)) {
			return false;
		}
		if (maxMem == null) {
			if (other.maxMem != null) {
				return false;
			}
		} else if (!maxMem.equals(other.maxMem)) {
			return false;
		}
		if (sharedMem == null) {
			if (other.sharedMem != null) {
				return false;
			}
		} else if (!sharedMem.equals(other.sharedMem)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "DsMem [maxID=" + maxID + ", maxMem=" + maxMem + ", sharedMem=" + sharedMem + "]";
	}
}
