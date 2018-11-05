package com.sportradar.sdh.domain.common;

import lombok.Data;

@Data
public class BasePeriod extends BaseEntity {
	protected Long sportId;
	protected Integer eventPartId;
	protected Integer periodNum;
	protected String periodName;

	public String getCompositedId() {
		return "";
	}
	public void setCompositedId(String compositedId) {};
}
