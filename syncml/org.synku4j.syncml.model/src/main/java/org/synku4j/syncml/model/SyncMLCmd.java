package org.synku4j.syncml.model;

import org.synku4j.wbxml.annotations.WbxmlField;

public abstract class SyncMLCmd implements Comparable<SyncMLCmd> {
	@WbxmlField(index=0x0b, name="CmdID", required=true)
	private String cmdId;
	
	protected SyncMLCmd() {
	}

	public String getCmdId() {
		return cmdId;
	}

	public void setCmdId(String cmdId) {
		this.cmdId = cmdId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cmdId == null) ? 0 : cmdId.hashCode());
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
		if (!(obj instanceof SyncMLCmd)) {
			return false;
		}
		SyncMLCmd other = (SyncMLCmd) obj;
		if (cmdId == null) {
			if (other.cmdId != null) {
				return false;
			}
		} else if (!cmdId.equals(other.cmdId)) {
			return false;
		}
		return true;
	}

	public abstract String getCmdName();
	
	@Override
	public String toString() {
		return "AbstractCmd [cmdId=" + cmdId + "]";
	}

	@Override
	public int compareTo(SyncMLCmd o) {
		return o.getCmdId().compareTo(cmdId);
	}
}
