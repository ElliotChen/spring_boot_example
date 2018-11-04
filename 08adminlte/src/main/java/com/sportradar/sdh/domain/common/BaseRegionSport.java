package com.sportradar.sdh.domain.common;

import lombok.Data;

import java.util.Date;

@Data
public abstract class BaseRegionSport extends BaseEntity {
	protected Integer regionNum;

	protected Long sportId;

	protected Date updatedTime;


	@Override
	public String getCompositedId() {
		return String.valueOf(this.regionNum)+" "+String.valueOf(this.sportId);
	}

	@Override
	public void setCompositedId(String compositedId) {
		String[] ids = compositedId.split(" ");

		this.regionNum = Integer.parseInt(ids[0]);
		this.sportId = Long.parseLong(ids[1]);
	}
}
