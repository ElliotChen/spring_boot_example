package com.sportradar.sdp.domain.common;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class BaseMarket {

    @Id
    private Long marketId;
    private String marketName;
    private Integer marketTypeId;

    private Integer priority;

    private Date createdTime;
    private Date updatedTime;

    private String eventTypeId;

    private String marketIdXRefs;
}
