package com.sportradar.sdh.domain.common;

import lombok.Data;

import java.util.Date;

@Data
public abstract class BaseSport extends BaseEntity {

	protected Long sportId;
	protected String sportName;
	protected Integer priority;

	protected Date createdTime;
	protected Date updatedTime;

	@Override
	public String getCompositedId() {
		return String.valueOf(this.sportId);
	}

	@Override
	public void setCompositedId(String compositedId) {
		this.sportId = Long.parseLong(compositedId);
	}

}
