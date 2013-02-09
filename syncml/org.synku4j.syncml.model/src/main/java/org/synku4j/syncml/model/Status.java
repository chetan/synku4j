package org.synku4j.syncml.model;

import java.util.ArrayList;
import java.util.Collection;

import org.synku4j.syncml.wbxml.codepage.SyncMLCodePage;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;

@WbxmlPage(index=SyncMLCodePage.NAMESPACE_IDX, name=SyncMLCodePage.NAMESPACE_NAME)
@WbxmlField(index=0x29, name="Status")
public class Status extends SyncMLCmd {
	@WbxmlField(index=0x1c, name="MsgRef", required=true)
	private String msgRef;
	
	@WbxmlField(index=0x0c, name="CmdRef", required=true)
	private String cmdRef;

	@WbxmlField(index=0x0a, name="Cmd", required=true)
	private String cmd;

	@WbxmlField(index=0x2f, name="TargetRef")
	private Collection<String> targetRefs;

	@WbxmlField(index=0x28, name="SourceRef")
	private Collection<String> sourceRefs;
	
	@WbxmlField(index=0x14, name="Item")
	private Collection<Item> items;
	
	@WbxmlField(index=0x0e, name="Cred")
	private Credential cred;
	
	@WbxmlField(index=0x09, name="Chal")
	private Challenge chal;

	@WbxmlField(index=0x0f, name="Data")
	private Object data;
	
	public Status() {
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

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public Collection<String> getTargetRefs() {
		return targetRefs;
	}

	public void setTargetRefs(Collection<String> targetRefs) {
		this.targetRefs = targetRefs;
	}
	
	public void addTargetRef(String targetRef) {
		if (targetRefs == null) {
			targetRefs = new ArrayList<String>();
		}
		targetRefs.add(targetRef);
		
	}

	public Collection<String> getSourceRefs() {
		return sourceRefs;
	}

	public void setSourceRefs(Collection<String> sourceRefs) {
		this.sourceRefs = sourceRefs;
	}
	
	public void addSourceRef(String sourceRef) {
		if (sourceRefs == null) {
			sourceRefs = new ArrayList<String>();
		}
		
		sourceRefs.add(sourceRef);
	}

	public Collection<Item> getItems() {
		return items;
	}

	public void setItems(Collection<Item> items) {
		this.items = items;
	}
	
	public void addItem(Item item) {
		if (items == null) {
			items = new ArrayList<Item>();
		}
	
		items.add(item);
	}

	public Credential getCred() {
		return cred;
	}

	public void setCred(Credential cred) {
		this.cred = cred;
	}

	public Challenge getChal() {
		return chal;
	}

	public void setChal(Challenge chal) {
		this.chal = chal;
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
		int result = super.hashCode();
		result = prime * result + ((chal == null) ? 0 : chal.hashCode());
		result = prime * result + ((cmd == null) ? 0 : cmd.hashCode());
		result = prime * result + ((cmdRef == null) ? 0 : cmdRef.hashCode());
		result = prime * result + ((cred == null) ? 0 : cred.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + ((msgRef == null) ? 0 : msgRef.hashCode());
		result = prime * result + ((sourceRefs == null) ? 0 : sourceRefs.hashCode());
		result = prime * result + ((targetRefs == null) ? 0 : targetRefs.hashCode());
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
		if (!(obj instanceof Status)) {
			return false;
		}
		Status other = (Status) obj;
		if (chal == null) {
			if (other.chal != null) {
				return false;
			}
		} else if (!chal.equals(other.chal)) {
			return false;
		}
		if (cmd == null) {
			if (other.cmd != null) {
				return false;
			}
		} else if (!cmd.equals(other.cmd)) {
			return false;
		}
		if (cmdRef == null) {
			if (other.cmdRef != null) {
				return false;
			}
		} else if (!cmdRef.equals(other.cmdRef)) {
			return false;
		}
		if (cred == null) {
			if (other.cred != null) {
				return false;
			}
		} else if (!cred.equals(other.cred)) {
			return false;
		}
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
		if (msgRef == null) {
			if (other.msgRef != null) {
				return false;
			}
		} else if (!msgRef.equals(other.msgRef)) {
			return false;
		}
		if (sourceRefs == null) {
			if (other.sourceRefs != null) {
				return false;
			}
		} else if (!sourceRefs.equals(other.sourceRefs)) {
			return false;
		}
		if (targetRefs == null) {
			if (other.targetRefs != null) {
				return false;
			}
		} else if (!targetRefs.equals(other.targetRefs)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Status [chal=" + chal + ", cmd=" + cmd + ", cmdRef=" + cmdRef + ", cred=" + cred + ", data=" + data + ", items=" + items + ", msgRef=" + msgRef
				+ ", sourceRefs=" + sourceRefs + ", targetRefs=" + targetRefs + ", getCmdId()=" + getCmdId() + "]";
	}
	
	@Override
	public String getCmdName() {
		return getClass().getAnnotation(WbxmlField.class).name();
	}
}
