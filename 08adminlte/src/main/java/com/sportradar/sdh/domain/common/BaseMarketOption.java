package com.sportradar.sdh.domain.common;

import lombok.Data;
import java.util.Date;

@Data
public abstract class BaseMarketOption extends BaseEntity {

	protected Long marketId;

	protected Integer optionNum;

	protected String shortName;

	protected String fullName;

	protected Date updatedTime;
}
