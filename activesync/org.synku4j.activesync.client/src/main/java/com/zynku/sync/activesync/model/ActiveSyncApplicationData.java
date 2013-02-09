package com.zynku.sync.activesync.model;

import java.util.HashMap;
import java.util.Map;

public class ActiveSyncApplicationData {

	public String serverId;
	public Map<String, String> applicationData = new HashMap();
	
	public ActiveSyncApplicationData() {
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public void set(String key, String value) {
		applicationData.put(key, value);
	}
	
	public String get(String key) {
		return applicationData.get(key);
	}
	
	public Map<String, String> getApplicationData() {
		return applicationData;
	}
}
