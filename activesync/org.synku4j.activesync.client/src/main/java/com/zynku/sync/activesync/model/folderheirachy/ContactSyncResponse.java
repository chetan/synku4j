package com.zynku.sync.activesync.model.folderheirachy;

import java.util.ArrayList;
import java.util.List;

import com.zynku.sync.activesync.model.ActiveSyncApplicationData;

public final class ContactSyncResponse {
	
	private String status;
	
	private List<ActiveSyncApplicationData> applicationData = new ArrayList<ActiveSyncApplicationData>();

	public ContactSyncResponse() {
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void add(final ActiveSyncApplicationData data) {
		applicationData.add(data);
	}
	
	public List<ActiveSyncApplicationData> getApplicationData() {
		return applicationData;
	}
	
}
