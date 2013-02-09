package org.synku4j.syncml.model.devinf;

import org.synku4j.syncml.wbxml.codepage.DevInfCodePage;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;

@WbxmlPage(index=DevInfCodePage.NAMESPACE_IDX, name=DevInfCodePage.NAMESPACE_NAME)
@WbxmlField(name="Tx-Pref", index=0x22)
public class TxPref {
	@WbxmlField(index=0x06, name="CTType")
	private String ctType;
	
	@WbxmlField(index=0x24, name="VerCT")
	private String verCt;
	
	public TxPref() {
	}

	public String getCtType() {
		return ctType;
	}

	public void setCtType(String ctType) {
		this.ctType = ctType;
	}

	public String getVerCt() {
		return verCt;
	}

	public void setVerCt(String verCt) {
		this.verCt = verCt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ctType == null) ? 0 : ctType.hashCode());
		result = prime * result + ((verCt == null) ? 0 : verCt.hashCode());
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
		if (!(obj instanceof TxPref)) {
			return false;
		}
		TxPref other = (TxPref) obj;
		if (ctType == null) {
			if (other.ctType != null) {
				return false;
			}
		} else if (!ctType.equals(other.ctType)) {
			return false;
		}
		if (verCt == null) {
			if (other.verCt != null) {
				return false;
			}
		} else if (!verCt.equals(other.verCt)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TxPref [ctType=" + ctType + ", verCt=" + verCt + "]";
	}
}
