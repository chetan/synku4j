package org.synku4j.syncml.model;

import java.util.Collection;

import org.synku4j.syncml.wbxml.codepage.SyncMLCodePage;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;

@WbxmlPage(index=SyncMLCodePage.NAMESPACE_IDX, name=SyncMLCodePage.NAMESPACE_NAME)
@WbxmlField(index=0x08, name="Atomic")
public class Atomic  extends SyncMLCmd {
	@WbxmlField(index=0x1d, name="NoResp")
	private String noResp;
	
	// Only the classes specified in the list may be part of the collection
	@WbxmlField(required=true, classes={ Add.class, Replace.class, Delete.class, Copy.class, Map.class, Sequence.class, Sync.class })
	private Collection<SyncMLCmd> cmds;

	@WbxmlField(index=0x1a, name="Meta")
	private MetInf meta;
	
	public Atomic() {
	}

	public String getNoResp() {
		return noResp;
	}

	public void setNoResp(String noResp) {
		this.noResp = noResp;
	}

	public MetInf getMeta() {
		return meta;
	}

	public void setMeta(MetInf meta) {
		this.meta = meta;
	}

	@Override
	public String getCmdName() {
		return getClass().getAnnotation(WbxmlField.class).name();
	}

	public Collection<SyncMLCmd> getCmds() {
		return cmds;
	}

	public void setCmds(Collection<SyncMLCmd> cmds) {
		this.cmds = cmds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cmds == null) ? 0 : cmds.hashCode());
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
		if (!(obj instanceof Atomic)) {
			return false;
		}
		Atomic other = (Atomic) obj;
		if (cmds == null) {
			if (other.cmds != null) {
				return false;
			}
		} else if (!cmds.equals(other.cmds)) {
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
		return "Atomic [cmds=" + cmds + ", meta=" + meta + ", noResp=" + noResp + "]";
	}
}
