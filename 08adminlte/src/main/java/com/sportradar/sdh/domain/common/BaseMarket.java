package com.sportradar.sdh.domain.common;

import java.util.Date;

import lombok.Data;

@Data
public abstract class BaseMarket extends BaseEntity {

    private Long marketId;
    private String marketName;


    private Integer priority;

    private Date createdTime;
    private Date updatedTime;

    private String eventTypeId;

    private String marketIdXRefs;
}
