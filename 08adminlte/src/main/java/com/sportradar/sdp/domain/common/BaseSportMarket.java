package com.sportradar.sdp.domain.common;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import java.io.Serializable;

@MappedSuperclass
@Data
public abstract class BaseSportMarket implements Serializable {

    @Id
    private Long sportId;
    @Id
    private Integer eventPartId;
    @Id
    private Long marketId;

    private Integer marketTypeId;

    private String sportMarketName;

    private Integer priority;


}