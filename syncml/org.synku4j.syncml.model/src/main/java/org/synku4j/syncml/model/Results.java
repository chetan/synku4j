package org.synku4j.syncml.model;

import java.util.Collection;

import org.synku4j.syncml.wbxml.codepage.SyncMLCodePage;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;

@WbxmlPage(index=SyncMLCodePage.NAMESPACE_IDX, name=SyncMLCodePage.NAMESPACE_NAME)
@WbxmlField(index=0x22, name="Results")
public class Results extends SyncMLCmd {
	@WbxmlField(index=0x1c, name="MsgRef")
	private String msgRef;
	
	@WbxmlField(index=0x0c, name="CmdRef")
	private String cmdRef;

	@WbxmlField(index=0x1a, name="Meta")
	private MetInf MetInf;
	
	@WbxmlField(index=0x2f, name="TargetRef")
	private String targetRef;
	
	@WbxmlField(index=0x28, name="SourceRef")
	private String sourceRef;
	
	@WbxmlField(index=0x14, name="Item")
	private Collection<Item> items;
	
	public Results() {
	}

	public String getMsgRef() {
		return msgRef;
	}

	public void setMsgRef(String msgRef) {
		this.msgRef = msgRef;
	}

	public String getCmdRef() {
		return cmdRef;
	}

	public void setCmdRef(String cmdRef) {
		this.cmdRef = cmdRef;
	}

	public MetInf getMeta() {
		return MetInf;
	}

	public void setMeta(MetInf MetInf) {
		this.MetInf = MetInf;
	}

	public String getTargetRef() {
		return targetRef;
	}

	public void setTargetRef(String targetRef) {
		this.targetRef = targetRef;
	}

	public String getSourceRef() {
		return sourceRef;
	}

	public void setSourceRef(String sourceRef) {
		this.sourceRef = sourceRef;
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
		result = prime * result + ((cmdRef == null) ? 0 : cmdRef.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + ((MetInf == null) ? 0 : MetInf.hashCode());
		result = prime * result + ((msgRef == null) ? 0 : msgRef.hashCode());
		result = prime * result + ((sourceRef == null) ? 0 : sourceRef.hashCode());
		result = prime * result + ((targetRef == null) ? 0 : targetRef.hashCode());
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
		if (!(obj instanceof Results)) {
			return false;
		}
		Results other = (Results) obj;
		if (cmdRef == null) {
			if (other.cmdRef != null) {
				return false;
			}
		} else if (!cmdRef.equals(other.cmdRef)) {
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
		if (msgRef == null) {
			if (other.msgRef != null) {
				return false;
			}
		} else if (!msgRef.equals(other.msgRef)) {
			return false;
		}
		if (sourceRef == null) {
			if (other.sourceRef != null) {
				return false;
			}
		} else if (!sourceRef.equals(other.sourceRef)) {
			return false;
		}
		if (targetRef == null) {
			if (other.targetRef != null) {
				return false;
			}
		} else if (!targetRef.equals(other.targetRef)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Results [cmdRef=" + cmdRef + ", items=" + items + ", MetInf=" + MetInf + ", msgRef=" + msgRef + ", sourceRef=" + sourceRef + ", targetRef="
				+ targetRef + ", getCmdId()=" + getCmdId() + "]";
	}
	
	@Override
	public String getCmdName() {
		return getClass().getAnnotation(WbxmlField.class).name();
	}
}
