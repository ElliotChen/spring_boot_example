package com.sportradar.sdh.domain.common;

import lombok.Data;

import java.util.Date;

@Data
public abstract class BaseMarket extends BaseEntity {

    protected Long marketId;
    protected String marketName;


    protected Integer priority;

    protected Date createdTime;
    protected Date updatedTime;

    protected String marketIdXRefs;
}
