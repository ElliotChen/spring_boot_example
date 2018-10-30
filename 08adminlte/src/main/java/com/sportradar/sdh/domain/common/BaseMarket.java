package com.sportradar.sdh.domain.common;

import java.util.Date;

import lombok.Data;

@Data
public abstract class BaseMarket extends BaseEntity {

    protected Long marketId;
    protected String marketName;


    protected Integer priority;

    protected Date createdTime;
    protected Date updatedTime;

    protected String eventTypeId;

    protected String marketIdXRefs;
}
