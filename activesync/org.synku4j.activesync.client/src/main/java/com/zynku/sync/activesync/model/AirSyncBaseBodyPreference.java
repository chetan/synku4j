package com.zynku.sync.activesync.model;

public class AirSyncBaseBodyPreference {
	//	1 plain text, 2 HTML, 3 RTF, 4 MIME  
	private String type;
	private Integer truncationSize;
	private Boolean allOrNone;
	private String restriction;
	// Range 0..255
	private Integer preview;

	public AirSyncBaseBodyPreference() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getTruncationSize() {
		return truncationSize;
	}

	public void setTruncationSize(Integer truncationSize) {
		this.truncationSize = truncationSize;
	}

	public Boolean getAllOrNone() {
		return allOrNone;
	}

	public void setAllOrNone(Boolean allOrNone) {
		this.allOrNone = allOrNone;
	}

	public String getRestriction() {
		return restriction;
	}

	public void setRestriction(String restriction) {
		this.restriction = restriction;
	}

	public Integer getPreview() {
		return preview;
	}

	public void setPreview(Integer preview) {
		this.preview = preview;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((allOrNone == null) ? 0 : allOrNone.hashCode());
		result = prime * result + ((preview == null) ? 0 : preview.hashCode());
		result = prime * result + ((restriction == null) ? 0 : restriction.hashCode());
		result = prime * result + ((truncationSize == null) ? 0 : truncationSize.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (!(obj instanceof AirSyncBaseBodyPreference)) {
			return false;
		}
		AirSyncBaseBodyPreference other = (AirSyncBaseBodyPreference) obj;
		if (allOrNone == null) {
			if (other.allOrNone != null) {
				return false;
			}
		} else if (!allOrNone.equals(other.allOrNone)) {
			return false;
		}
		if (preview == null) {
			if (other.preview != null) {
				return false;
			}
		} else if (!preview.equals(other.preview)) {
			return false;
		}
		if (restriction == null) {
			if (other.restriction != null) {
				return false;
			}
		} else if (!restriction.equals(other.restriction)) {
			return false;
		}
		if (truncationSize == null) {
			if (other.truncationSize != null) {
				return false;
			}
		} else if (!truncationSize.equals(other.truncationSize)) {
			return false;
		}
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
			return false;
		}
		return true;
	}
}
