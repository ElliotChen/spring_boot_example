package com.sportradar.sdh.domain.common;

import lombok.Data;

@Data
public class BaseSportEventPart extends BaseEntity {
	protected Long sportId;

	protected Integer eventPartId;

	protected String eventPartName;

	public String getCompositedId() {
		return String.valueOf(sportId + " " + eventPartId);
	}
	public void setCompositedId(String compositedId) {
		String[] ids = compositedId.split(" ");
		this.sportId = Long.parseLong(ids[0]);
		this.eventPartId = Integer.parseInt(ids[1]);
	};
}
