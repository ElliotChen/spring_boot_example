package com.sportradar.sdh.domain.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false, of = {"marketId", "optionNum"})
public abstract class BaseMarketOption extends BaseEntity {

	protected Long marketId;

	protected Integer optionNum = -1;

	protected String shortName;

	protected String fullName;

	protected Date updatedTime;
}
