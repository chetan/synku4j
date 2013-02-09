package com.zynku.core.sync;

public final class SyncStatus {

	private final SyncResult syncResult;
	private final SyncDomain syncDomain;
	
	private int imported;
	private int exported;
	private int ignored;
	private int conflicts;
	private long startTime;
	private long endTime;
	
	public SyncStatus(final SyncDomain syncDomain, final SyncResult syncResult) {
		this.syncResult = syncResult;
		this.syncDomain = syncDomain;
	}

	public int getImported() {
		return imported;
	}

	public void setImported(int imported) {
		this.imported = imported;
	}

	public int getExported() {
		return exported;
	}

	public void setExported(int exported) {
		this.exported = exported;
	}

	public int getIgnored() {
		return ignored;
	}

	public void setIgnored(int ignored) {
		this.ignored = ignored;
	}

	public int getConflicts() {
		return conflicts;
	}

	public void setConflicts(int conflicts) {
		this.conflicts = conflicts;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public SyncResult getSyncResult() {
		return syncResult;
	}

	public SyncDomain getSyncDomain() {
		return syncDomain;
	}
}
