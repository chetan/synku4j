package com.zynku.core.model;

import java.util.Map;

public class ExternalIdentity {
	/** Keys used to store information in the credentials map */
	public static final String USER_NAME_KEY     = "username";
	public static final String USER_PASSWORD_KEY = "password";
	
	private Long id;
	private User user;
	private ExternalNetwork network;
	private Map<String, String> credentials;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ExternalNetwork getNetwork() {
		return network;
	}

	public void setNetwork(ExternalNetwork network) {
		this.network = network;
	}

	public Map<String, String> getCredentials() {
		return credentials;
	}

	public void setCredentials(Map<String, String> credentials) {
		this.credentials = credentials;
	}

}
