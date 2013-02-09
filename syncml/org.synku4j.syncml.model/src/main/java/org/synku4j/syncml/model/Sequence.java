package org.synku4j.syncml.model;

import java.util.Collection;

import org.synku4j.syncml.wbxml.codepage.SyncMLCodePage;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;

@WbxmlPage(index=SyncMLCodePage.NAMESPACE_IDX, name=SyncMLCodePage.NAMESPACE_NAME)
@WbxmlField(index=0x24, name="Sequence")
public class Sequence extends SyncMLCmd {

	@WbxmlField(index=0x1d, name="NoResp")
	private String noResp;
	
	@WbxmlField(index=0x1a, name="Meta")
	private MetInf MetInf;
	
	private Collection<SyncMLCmd> cmds;
	
	public Sequence() {
	}

	public String getNoResp() {
		return noResp;
	}

	public void setNoResp(String noResp) {
		this.noResp = noResp;
	}

	public MetInf getMeta() {
		return MetInf;
	}

	public void setMeta(MetInf MetInf) {
		this.MetInf = MetInf;
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
		if (!(obj instanceof Sequence)) {
			return false;
		}
		Sequence other = (Sequence) obj;
		if (cmds == null) {
			if (other.cmds != null) {
				return false;
			}
		} else if (!cmds.equals(other.cmds)) {
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
		return "Sequence [cmds=" + cmds + ", MetInf=" + MetInf + ", noResp=" + noResp + ", getCmdId()=" + getCmdId() + "]";
	}
	
	@Override
	public String getCmdName() {
		return getClass().getAnnotation(WbxmlField.class).name();
	}
}
