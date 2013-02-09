package org.synku4j.syncml.model;

import java.util.ArrayList;
import java.util.Collection;

import org.synku4j.syncml.wbxml.codepage.SyncMLCodePage;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;

@WbxmlPage(index=SyncMLCodePage.NAMESPACE_IDX, name=SyncMLCodePage.NAMESPACE_NAME)
@WbxmlField(index=0x06, name="Alert")
public class Alert extends SyncMLCmd {
	@WbxmlField(index=0x1d, name="NoResp")
	private String noResp;
	
	@WbxmlField(index=0x15, name="Lang")
	private String lang;
	
	@WbxmlField(index=0x0f, name="Data")
	private Object data; 
	
	@WbxmlField(index=0x14, name="Item", required=true)
	private Collection<Item> items;
	
	public Alert() {
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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Collection<Item> getItems() {
		return items;
	}

	public void setItems(Collection<Item> items) {
		this.items = items;
	}

	public void addItem(final Item item) {
		if (items == null) {
			items = new ArrayList<Item>();
		}
		items.add(item);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + ((lang == null) ? 0 : lang.hashCode());
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
		if (!(obj instanceof Alert)) {
			return false;
		}
		Alert other = (Alert) obj;
		if (data == null) {
			if (other.data != null) {
				return false;
			}
		} else if (!data.equals(other.data)) {
			return false;
		}
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
		return "Alert [data=" + data + ", items=" + items + ", lang=" + lang + ", noResp=" + noResp + "]";
	}
	
	@Override
	public String getCmdName() {
		return getClass().getAnnotation(WbxmlField.class).name();
	}
}
