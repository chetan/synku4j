package com.zynku.sync.activesync.context;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zynku.sync.activesync.model.Folder;
import com.zynku.sync.activesync.model.FolderType;


public final class ActiveSyncContext {
	
	private String userName;
	private String password;
	private String synckKey;
	private String deviceId;
	private String deviceType;
	private URL serverURL;
	private final List<Folder> folders = new ArrayList<Folder>();
	private final Map<String, String> syncKeys = new HashMap<String, String>();
	
	public ActiveSyncContext() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSynckKey() {
		return synckKey;
	}

	public void setSynckKey(String synckKey) {
		this.synckKey = synckKey;
	}

	public URL getServerURL() {
		return serverURL;
	}

	public void setServerURL(URL serverURL) {
		this.serverURL = serverURL;
	}

	public List<Folder> getFolders() {
		return folders;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public Map<String, String> getSyncKeys() {
		return syncKeys;
	}

	public List<Folder> getFolders(FolderType type) {
		final ArrayList<Folder> filteredFolders = new ArrayList<Folder>();
		for (Folder f : folders) {
			if (type.equals(f.getType())) {
				filteredFolders.add(f);
			}
		}
		
		return filteredFolders;
	}
	public boolean hasFolders() {
		return !folders.isEmpty();
	}
	
}
