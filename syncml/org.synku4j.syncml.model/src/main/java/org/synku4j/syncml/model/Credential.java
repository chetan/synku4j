package org.synku4j.syncml.model;

import org.synku4j.syncml.wbxml.codepage.SyncMLCodePage;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;

@WbxmlPage(index = SyncMLCodePage.NAMESPACE_IDX, name = SyncMLCodePage.NAMESPACE_NAME)
@WbxmlField(index = 0x0e, name = "Cred")
public class Credential {

	@WbxmlField(index = 0x1a, name = "Meta")
	private MetInf meta;

	@WbxmlField(index = 0x0f, name = "Data")
	private Object data;

	public Credential() {
	}

	public MetInf getMeta() {
		return meta;
	}

	public void setMeta(MetInf meta) {
		this.meta = meta;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((meta == null) ? 0 : meta.hashCode());
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
		if (!(obj instanceof Credential)) {
			return false;
		}
		Credential other = (Credential) obj;
		if (data == null) {
			if (other.data != null) {
				return false;
			}
		} else if (!data.equals(other.data)) {
			return false;
		}
		if (meta == null) {
			if (other.meta != null) {
				return false;
			}
		} else if (!meta.equals(other.meta)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Credential [data=" + data + ", MetInf=" + meta + "]";
	}
}
