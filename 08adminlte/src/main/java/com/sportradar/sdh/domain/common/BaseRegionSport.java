package com.sportradar.sdh.domain.common;

import lombok.Data;

import java.util.Date;

@Data
public abstract class BaseRegionSport extends BaseEntity {
	protected Integer regionNum;

	protected Long sportId;

	protected Integer regionSportXRef;

	protected Date updatedTime;
}
