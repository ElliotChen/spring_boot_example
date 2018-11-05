package com.sportradar.sdh.domain.common;

import lombok.Data;

@Data
public abstract class BaseCompetitor extends BaseEntity {
	protected Long competitorId;

	protected String competitorFullName;

	protected String competitorShortName;

	protected String alias;

	protected Integer regionNum;

	protected Long sportId;

	protected String competitorIdXRef;
}
