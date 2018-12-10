package com.sportradar.sdh.domain.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(of = {"competitorId"}, callSuper = false)
@Data
public abstract class BaseCompetitor extends BaseEntity {
	protected Long competitorId;

	protected String competitorFullName;

	protected String competitorShortName;

	protected String alias;

	protected Integer tshirt;

	protected Integer regionNum;

	protected Long sportId;

    protected Date updatedTime;

	public String getCompositedId() {
		return String.valueOf(competitorId);
	}
	public void setCompositedId(String compositedId) {
		this.competitorId = Long.parseLong(compositedId);
	};
}
