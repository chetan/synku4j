package org.synku4j.syncml.model.devinf;

import java.util.Collection;

import org.synku4j.syncml.wbxml.codepage.DevInfCodePage;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;

@WbxmlPage(index=DevInfCodePage.NAMESPACE_IDX, name=DevInfCodePage.NAMESPACE_NAME)
@WbxmlField(name="DataStore", index=0x07)
public class DataStore {
	@WbxmlField(index=0x1D, name="SourceRef", required=true)
	private String sourceRef;
	
	@WbxmlField(index=0x0C, name="DisplayName")
	private String displayName;
	
	@WbxmlField(index=0x12, name="MaxGUIDSize")
	private String maxGuidSize;
	
	@WbxmlField(index=0x1A, name="RxPref", required=true)
	private RxPref rxPref;

	@WbxmlField(index=0x19, name="Rx")
	private Collection<Rx> rx;
	
	@WbxmlField(index=0x22, name="TxPref")
	private TxPref txPref;

	@WbxmlField(index=0x21, name="Tx")
	private Collection<Tx> tx;
	
	@WbxmlField(index=0x0D, name="DsMem")
	private DsMem dsMem;
	
	@WbxmlField(index=0x1F, name="SyncCap")
	private SyncCap syncCap;
	
	public DataStore() {
	}

	public String getSourceRef() {
		return sourceRef;
	}

	public void setSourceRef(String sourceRef) {
		this.sourceRef = sourceRef;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getMaxGuidSize() {
		return maxGuidSize;
	}

	public void setMaxGuidSize(String maxGuidSize) {
		this.maxGuidSize = maxGuidSize;
	}

	public RxPref getRxPref() {
		return rxPref;
	}

	public void setRxPref(RxPref rxPref) {
		this.rxPref = rxPref;
	}

	public Collection<Rx> getRx() {
		return rx;
	}

	public void setRx(Collection<Rx> rx) {
		this.rx = rx;
	}

	public TxPref getTxPref() {
		return txPref;
	}

	public void setTxPref(TxPref txPref) {
		this.txPref = txPref;
	}

	public Collection<Tx> getTx() {
		return tx;
	}

	public void setTx(Collection<Tx> tx) {
		this.tx = tx;
	}

	public DsMem getDsMem() {
		return dsMem;
	}

	public void setDsMem(DsMem dsMem) {
		this.dsMem = dsMem;
	}

	public SyncCap getSyncCap() {
		return syncCap;
	}

	public void setSyncCap(SyncCap syncCap) {
		this.syncCap = syncCap;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result + ((dsMem == null) ? 0 : dsMem.hashCode());
		result = prime * result + ((maxGuidSize == null) ? 0 : maxGuidSize.hashCode());
		result = prime * result + ((rx == null) ? 0 : rx.hashCode());
		result = prime * result + ((rxPref == null) ? 0 : rxPref.hashCode());
		result = prime * result + ((sourceRef == null) ? 0 : sourceRef.hashCode());
		result = prime * result + ((syncCap == null) ? 0 : syncCap.hashCode());
		result = prime * result + ((tx == null) ? 0 : tx.hashCode());
		result = prime * result + ((txPref == null) ? 0 : txPref.hashCode());
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
		if (!(obj instanceof DataStore)) {
			return false;
		}
		DataStore other = (DataStore) obj;
		if (displayName == null) {
			if (other.displayName != null) {
				return false;
			}
		} else if (!displayName.equals(other.displayName)) {
			return false;
		}
		if (dsMem == null) {
			if (other.dsMem != null) {
				return false;
			}
		} else if (!dsMem.equals(other.dsMem)) {
			return false;
		}
		if (maxGuidSize == null) {
			if (other.maxGuidSize != null) {
				return false;
			}
		} else if (!maxGuidSize.equals(other.maxGuidSize)) {
			return false;
		}
		if (rx == null) {
			if (other.rx != null) {
				return false;
			}
		} else if (!rx.equals(other.rx)) {
			return false;
		}
		if (rxPref == null) {
			if (other.rxPref != null) {
				return false;
			}
		} else if (!rxPref.equals(other.rxPref)) {
			return false;
		}
		if (sourceRef == null) {
			if (other.sourceRef != null) {
				return false;
			}
		} else if (!sourceRef.equals(other.sourceRef)) {
			return false;
		}
		if (syncCap == null) {
			if (other.syncCap != null) {
				return false;
			}
		} else if (!syncCap.equals(other.syncCap)) {
			return false;
		}
		if (tx == null) {
			if (other.tx != null) {
				return false;
			}
		} else if (!tx.equals(other.tx)) {
			return false;
		}
		if (txPref == null) {
			if (other.txPref != null) {
				return false;
			}
		} else if (!txPref.equals(other.txPref)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "DataStore [displayName=" + displayName + ", dsMem=" + dsMem + ", maxGuidSize=" + maxGuidSize + ", rx=" + rx + ", rxPref="
				+ rxPref + ", sourceRef=" + sourceRef + ", syncCap=" + syncCap + ", tx=" + tx + ", txPref=" + txPref + "]";
	}
}
