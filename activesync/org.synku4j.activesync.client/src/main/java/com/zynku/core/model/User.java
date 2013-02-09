package com.zynku.core.model;

import java.util.Collection;
import java.util.Date;

public class User {

	private Long id;
	private Name name;
	private String username;
	private String password;
	private Date birthDate;
	private String email;
	private String msisdn;
	private Device device;
	private Locale locale;
	private String profilePicture;
	private Boolean authenticated;
	private Collection<ExternalIdentity> externalIdentities;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public Boolean getAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(Boolean authenticated) {
		this.authenticated = authenticated;
	}

	public Collection<ExternalIdentity> getExternalIdentities() {
		return externalIdentities;
	}

	public void setExternalIdentities(Collection<ExternalIdentity> externalIdentities) {
		this.externalIdentities = externalIdentities;
	}

}
