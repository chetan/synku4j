package org.synku4j.syncml.model;

import java.util.ArrayList;
import java.util.Collection;

import org.synku4j.syncml.wbxml.codepage.SyncMLCodePage;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;

@WbxmlPage(index = SyncMLCodePage.NAMESPACE_IDX, name = SyncMLCodePage.NAMESPACE_NAME)
@WbxmlField(index = 0x2B, name = "SyncBody")
public class SyncBody {

	@WbxmlField(required = true, classes = { Add.class, Alert.class, Atomic.class, Copy.class, Exec.class, 
											 Get.class, Map.class, Put.class, Results.class, 
											 Search.class,	Sequence.class, Status.class, Sync.class })
	private Collection<SyncMLCmd> cmds;

	@WbxmlField(index = 0x12, name = "Final")
	private Boolean finalEntry;

	public SyncBody() {
	}

	public Boolean getFinalEntry() {
		return finalEntry;
	}

	public void setFinalEntry(Boolean finalEntry) {
		this.finalEntry = finalEntry;
	}

	public Collection<SyncMLCmd> getCmds() {
		return cmds;
	}

	public void setCmds(Collection<SyncMLCmd> cmds) {
		this.cmds = cmds;
	}
	
	public void addCmd(SyncMLCmd cmd) {
		if (cmds == null) {
			cmds = new ArrayList<SyncMLCmd>();
		}
		
		cmd.setCmdId(String.valueOf(cmds.size()+1));
		cmds.add(cmd);
	}
 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cmds == null) ? 0 : cmds.hashCode());
		result = prime * result + ((finalEntry == null) ? 0 : finalEntry.hashCode());
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
		if (!(obj instanceof SyncBody)) {
			return false;
		}
		SyncBody other = (SyncBody) obj;
		if (cmds == null) {
			if (other.cmds != null) {
				return false;
			}
		} else if (!cmds.equals(other.cmds)) {
			return false;
		}
		if (finalEntry == null) {
			if (other.finalEntry != null) {
				return false;
			}
		} else if (!finalEntry.equals(other.finalEntry)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "SyncBody [cmds=" + cmds + ", finalEntry=" + finalEntry + "]";
	}
}
