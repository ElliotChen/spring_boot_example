package com.sportradar.sdh.domain.common;

import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
public abstract class BaseMarketOption extends BaseReferenceModel {

	private Long marketId;

	private Integer optionNum;

	private String shortName;

	private String fullName;

	private Date updatedTime;
}
