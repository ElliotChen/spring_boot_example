package com.sportradar.sdh.domain.common;

import lombok.Data;

import java.util.Date;

@Data
public abstract class BaseRegionSport {
	private Integer regionNum;

	private Long sportId;

	private Integer regionSportXRef;

	private Date updatedTime;
}
