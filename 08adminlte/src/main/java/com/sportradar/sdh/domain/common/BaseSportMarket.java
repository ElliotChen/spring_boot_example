package com.sportradar.sdh.domain.common;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BaseSportMarket implements Serializable {

    private Long sportId;
    private Integer eventPartId;
    private Long marketId;

    private Integer marketTypeId;

    private String sportMarketName;

    private Integer priority;


}