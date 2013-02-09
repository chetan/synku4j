package org.synku4j.syncml.model;

import java.util.Collection;

import org.synku4j.syncml.wbxml.codepage.SyncMLCodePage;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;

@WbxmlPage(index = SyncMLCodePage.NAMESPACE_IDX, name = SyncMLCodePage.NAMESPACE_NAME)
@WbxmlField(index = 0x10, name = "Delete")
public class Delete extends SyncMLCmd {
	@WbxmlField(index = 0x1d, name = "NoResp")
	private String noResp;

	@WbxmlField(index = 0x07, name = "Archive")
	private Boolean archive;

	@WbxmlField(index = 0x26, name = "SftDel")
	private Boolean sftDel;

	@WbxmlField(index = 0x0e, name = "Cred")
	private Credential cred;

	@WbxmlField(index = 0x1a, name = "Meta")
	private MetInf meta;

	@WbxmlField(index = 0x14, name = "Item")
	private Collection<Item> items;

	public Delete() {
	}

	public String getNoResp() {
		return noResp;
	}

	public void setNoResp(String noResp) {
		this.noResp = noResp;
	}

	public Boolean getArchive() {
		return archive;
	}

	public void setArchive(Boolean archive) {
		this.archive = archive;
	}

	public Boolean getSftDel() {
		return sftDel;
	}

	public void setSftDel(Boolean sftDel) {
		this.sftDel = sftDel;
	}

	public Credential getCred() {
		return cred;
	}

	public void setCred(Credential cred) {
		this.cred = cred;
	}

	public MetInf getMeta() {
		return meta;
	}

	public void setMeta(MetInf meta) {
		this.meta = meta;
	}

	public Collection<Item> getItems() {
		return items;
	}

	public void setItems(Collection<Item> items) {
		this.items = items;
	}

	@Override
	public String getCmdName() {
		return getClass().getAnnotation(WbxmlField.class).name();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((archive == null) ? 0 : archive.hashCode());
		result = prime * result + ((cred == null) ? 0 : cred.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + ((meta == null) ? 0 : meta.hashCode());
		result = prime * result + ((noResp == null) ? 0 : noResp.hashCode());
		result = prime * result + ((sftDel == null) ? 0 : sftDel.hashCode());
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
		if (!(obj instanceof Delete)) {
			return false;
		}
		Delete other = (Delete) obj;
		if (archive == null) {
			if (other.archive != null) {
				return false;
			}
		} else if (!archive.equals(other.archive)) {
			return false;
		}
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
		if (meta == null) {
			if (other.meta != null) {
				return false;
			}
		} else if (!meta.equals(other.meta)) {
			return false;
		}
		if (noResp == null) {
			if (other.noResp != null) {
				return false;
			}
		} else if (!noResp.equals(other.noResp)) {
			return false;
		}
		if (sftDel == null) {
			if (other.sftDel != null) {
				return false;
			}
		} else if (!sftDel.equals(other.sftDel)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Delete [archive=" + archive + ", cred=" + cred + ", items=" + items + ", meta=" + meta + ", noResp=" + noResp + ", sftDel=" + sftDel + "]";
	}
}
