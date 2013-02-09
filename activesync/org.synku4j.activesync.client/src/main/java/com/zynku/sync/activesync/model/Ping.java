package com.zynku.sync.activesync.model;

import java.util.ArrayList;
import java.util.List;

public final class Ping {

	private String status;
	private Integer maxFolders;
	private Integer heartbeatInterval;
	private final List<Folder> folders = new ArrayList<Folder>();
	
	public Ping() {
	}

	public List<Folder> getFolders() {
		return folders;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getMaxFolders() {
		return maxFolders;
	}

	public void setMaxFolders(Integer maxFolders) {
		this.maxFolders = maxFolders;
	}

	public Integer getHeartbeatInterval() {
		return heartbeatInterval;
	}

	public void setHeartbeatInterval(Integer heartbeatInterval) {
		this.heartbeatInterval = heartbeatInterval;
	}
}
