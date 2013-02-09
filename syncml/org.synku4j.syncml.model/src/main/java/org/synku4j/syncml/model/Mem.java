package org.synku4j.syncml.model;

import org.synku4j.syncml.wbxml.codepage.MetInfCodePage;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;

@WbxmlPage(index=MetInfCodePage.NAMESPACE_IDX, name=MetInfCodePage.NAMESPACE_NAME)
@WbxmlField(index=0x0d, name="Mem")
public class Mem {
	@WbxmlField(index=0x11, name="SharedMem")
	private Boolean sharedMem;
	
	@WbxmlField(index=0x09, name="FreeMem", required=true)
	private String freeMem;
	
	@WbxmlField(index=0x08, name="FreeID", required=true)
	private String freeId;
	
	public Mem() {
	}

	public Boolean getSharedMem() {
		return sharedMem;
	}

	public void setSharedMem(Boolean sharedMem) {
		this.sharedMem = sharedMem;
	}

	public String getFreeMem() {
		return freeMem;
	}

	public void setFreeMem(String freeMem) {
		this.freeMem = freeMem;
	}

	public String getFreeId() {
		return freeId;
	}

	public void setFreeId(String freeId) {
		this.freeId = freeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((freeId == null) ? 0 : freeId.hashCode());
		result = prime * result + ((freeMem == null) ? 0 : freeMem.hashCode());
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
		if (!(obj instanceof Mem)) {
			return false;
		}
		Mem other = (Mem) obj;
		if (freeId == null) {
			if (other.freeId != null) {
				return false;
			}
		} else if (!freeId.equals(other.freeId)) {
			return false;
		}
		if (freeMem == null) {
			if (other.freeMem != null) {
				return false;
			}
		} else if (!freeMem.equals(other.freeMem)) {
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
		return "Mem [freeId=" + freeId + ", freeMem=" + freeMem + ", sharedMem=" + sharedMem + "]";
	}
}
