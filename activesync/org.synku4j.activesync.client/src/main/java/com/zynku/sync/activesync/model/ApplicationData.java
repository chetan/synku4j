package com.zynku.sync.activesync.model;

import java.util.HashMap;
import java.util.Map;

import com.zynku.sync.wbxml.CodePage;

public class ApplicationData {

	private Map<String, String> applicationData = new HashMap();
	private CodePage codePage;
	
	
	// TODO: do we need the codepage here ?
	
	public ApplicationData() {
		
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

	public CodePage getCodePage() {
		return codePage;
	}

	public void setCodePage(CodePage codePage) {
		this.codePage = codePage;
	}	
}
