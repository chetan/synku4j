package org.synku4j.syncml.model;

import java.util.Collection;

import org.synku4j.syncml.wbxml.codepage.SyncMLCodePage;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;

@WbxmlPage(index=SyncMLCodePage.NAMESPACE_IDX, name=SyncMLCodePage.NAMESPACE_NAME)
@WbxmlField(index=0x20, name="Replace")
public class Replace extends SyncMLCmd {
	@WbxmlField(index=0x1d, name="NoResp")
	private String noResp;

	@WbxmlField(index=0x0e, name="Cred")
	private Credential cred;
	
	@WbxmlField(index=0x1a, name="Meta")
	private MetInf MetInf;

	@WbxmlField(index=0x14, name="Item")
	private Collection<Item> items;
	
	public Replace() {
	}

	public String getNoResp() {
		return noResp;
	}

	public void setNoResp(String noResp) {
		this.noResp = noResp;
	}

	public Credential getCred() {
		return cred;
	}

	public void setCred(Credential cred) {
		this.cred = cred;
	}

	public MetInf getMeta() {
		return MetInf;
	}

	public void setMeta(MetInf MetInf) {
		this.MetInf = MetInf;
	}

	public Collection<Item> getItems() {
		return items;
	}

	public void setItems(Collection<Item> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cred == null) ? 0 : cred.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + ((MetInf == null) ? 0 : MetInf.hashCode());
		result = prime * result + ((noResp == null) ? 0 : noResp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Replace)) {
			return false;
		}
		Replace other = (Replace) obj;
		if (cred == null) {
			if (other.cred != null) {
				return false;
			}
		} else if (!cred.equals(other.cred)) {
			return false;
		}
		if (items == null) {
			if (other.items != null) {
				return false;
			}
		} else if (!items.equals(other.items)) {
			return false;
		}
		if (MetInf == null) {
			if (other.MetInf != null) {
				return false;
			}
		} else if (!MetInf.equals(other.MetInf)) {
			return false;
		}
		if (noResp == null) {
			if (other.noResp != null) {
				return false;
			}
		} else if (!noResp.equals(other.noResp)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Replace [cred=" + cred + ", items=" + items + ", MetInf=" + MetInf + ", noResp=" + noResp + ", getCmdId()=" + getCmdId() + "]";
	}
	
	@Override
	public String getCmdName() {
		return getClass().getAnnotation(WbxmlField.class).name();
	}
}
