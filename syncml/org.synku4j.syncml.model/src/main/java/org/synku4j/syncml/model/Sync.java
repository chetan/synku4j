package org.synku4j.syncml.model;

import java.util.Collection;

import org.synku4j.syncml.wbxml.codepage.SyncMLCodePage;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;

/**
 *
 * @author Jools Enticknap
 */
@WbxmlPage(index=SyncMLCodePage.NAMESPACE_IDX, name=SyncMLCodePage.NAMESPACE_NAME)
@WbxmlField(index=0x2a, name="Sync")
public class Sync extends SyncMLCmd {
	@WbxmlField(index=0x1d, name="NoResp")
	private String noResp;
	
	@WbxmlField(index=0x0e, name="Cred")
	private Credential cred;
	
	@WbxmlField(index=0x2e, name="Target")
	private Location target;
	
	@WbxmlField(index=0x27, name="Source")
	private Location source;

	@WbxmlField(index=0x1a, name="Meta")
	private MetInf meta;
	
	@WbxmlField(classes = { Add.class, Atomic.class, Copy.class, Delete.class, Replace.class, Sequence.class } )
	private Collection<SyncMLCmd> cmds;
	
	@WbxmlField(index=0x33, name="NumberOfChanged")
	private String numberOfChanged;
	
	public Sync() {
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

	public Location getTarget() {
		return target;
	}

	public void setTarget(Location target) {
		this.target = target;
	}

	public Location getSource() {
		return source;
	}

	public void setSource(Location source) {
		this.source = source;
	}

	public MetInf getMeta() {
		return meta;
	}

	public void setMeta(MetInf MetInf) {
		this.meta = MetInf;
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
		result = prime * result + ((cred == null) ? 0 : cred.hashCode());
		result = prime * result + ((meta == null) ? 0 : meta.hashCode());
		result = prime * result + ((noResp == null) ? 0 : noResp.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
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
		if (!(obj instanceof Sync)) {
			return false;
		}
		Sync other = (Sync) obj;
		if (cmds == null) {
			if (other.cmds != null) {
				return false;
			}
		} else if (!cmds.equals(other.cmds)) {
			return false;
		}
		if (cred == null) {
			if (other.cred != null) {
				return false;
			}
		} else if (!cred.equals(other.cred)) {
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
		if (source == null) {
			if (other.source != null) {
				return false;
			}
		} else if (!source.equals(other.source)) {
			return false;
		}
		if (target == null) {
			if (other.target != null) {
				return false;
			}
		} else if (!target.equals(other.target)) {
			return false;
		}
		return true;
	}
	
	public String getNumberOfChanged() {
		return numberOfChanged;
	}

	public void setNumberOfChanged(String numberOfChanged) {
		this.numberOfChanged = numberOfChanged;
	}

	@Override
	public String toString() {
		return "Sync [cmds=" + cmds + ", cred=" + cred + ", MetInf=" + meta + ", noResp=" + noResp + ", source=" + source + ", target=" + target
				+ ", getCmdId()=" + getCmdId() + "]";
	}
	
	@Override
	public String getCmdName() {
		return getClass().getAnnotation(WbxmlField.class).name();
	}
}
