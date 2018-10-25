package com.sportradar.sdp.domain.common;

import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Data
@IdClass(MarketOptionPK.class)
public abstract class BaseMarketOption extends BaseReferenceModel {

	@Id
	private Long marketId;

	@Id
	private Integer optionNum;

	private String shortName;

	private String fullName;

	@LastModifiedDate
	private Date updatedTime;
}
