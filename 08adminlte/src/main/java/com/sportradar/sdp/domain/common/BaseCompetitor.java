package com.sportradar.sdp.domain.common;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public abstract class BaseCompetitor {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long competitorId;

	private String competitorFullName;

	private String competitorShortName;

	private String alias;

	private Integer regionNum;

	private Long sportId;

	private String competitorIdXRef;
}
