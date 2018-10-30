package com.sportradar.sdh.domain.common;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BaseSportMarket extends BaseEntity {

    protected Long sportId;
    protected Integer eventPartId;
    protected Long marketId;

    protected Integer marketTypeId;

    protected String sportMarketName;

    protected Integer priority;


}