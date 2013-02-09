package com.zynku.core.model;

import java.util.Date;

public class Activity {

	private Long id;
	private ActivityType activityType;
	private User owner;
	private Boolean userDeleted;
	private Boolean userRead;
	private Date created;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// generic parms
	// recipients

}
