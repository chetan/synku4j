package org.synku4j.syncml.model;

import org.synku4j.syncml.wbxml.codepage.SyncMLCodePage;
import org.synku4j.wbxml.annotations.WbxmlField;
import org.synku4j.wbxml.annotations.WbxmlPage;


@WbxmlPage(index=SyncMLCodePage.NAMESPACE_IDX, name=SyncMLCodePage.NAMESPACE_NAME)
public class Location {
	@WbxmlField(index=0x17, name="LocURI", required=true)
	private String uri;
	
	@WbxmlField(index=0x16, name="LocName", required=false)
	private String name;
	
	public Location() {
	}

	public Location(final String locURI) {
		this.uri = locURI;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
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
		if (!(obj instanceof Location)) {
			return false;
		}
		Location other = (Location) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (uri == null) {
			if (other.uri != null) {
				return false;
			}
		} else if (!uri.equals(other.uri)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Location [name=" + name + ", uri=" + uri + "]";
	}
}
