package org.synku4j.syncml.model;

import java.util.Collection;

import org.synku4j.syncml.wbxml.codepage.SyncMLCodePage;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;

@WbxmlPage(index = SyncMLCodePage.NAMESPACE_IDX, name = SyncMLCodePage.NAMESPACE_NAME)
@WbxmlField(index = 0x0d, name = "Copy")
public class Copy extends SyncMLCmd {
	@WbxmlField(index = 0x1d, name = "NoResp")
	private String noResp;

	@WbxmlField(index = 0x15, name = "Lang")
	private String lang;

	@WbxmlField(index = 0x1a, name = "Meta")
	private MetInf meta;

	@WbxmlField(index = 0x14, name = "Item", required = true)
	private Collection<Item> items;

	public Copy() {
	}

	public String getNoResp() {
		return noResp;
	}

	public void setNoResp(String noResp) {
		this.noResp = noResp;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public MetInf getMeta() {
		return meta;
	}

	public void setMeta(MetInf metInf) {
		this.meta = metInf;
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
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + ((lang == null) ? 0 : lang.hashCode());
		result = prime * result + ((meta == null) ? 0 : meta.hashCode());
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
		if (!(obj instanceof Copy)) {
			return false;
		}
		Copy other = (Copy) obj;
		if (items == null) {
			if (other.items != null) {
				return false;
			}
		} else if (!items.equals(other.items)) {
			return false;
		}
		if (lang == null) {
			if (other.lang != null) {
				return false;
			}
		} else if (!lang.equals(other.lang)) {
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
		return true;
	}

	@Override
	public String toString() {
		return "Copy [items=" + items + ", lang=" + lang + ", meta=" + meta + ", noResp=" + noResp + "]";
	}
}
