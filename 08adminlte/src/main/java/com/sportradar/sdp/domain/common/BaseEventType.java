package com.sportradar.sdp.domain.common;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class BaseEventType {

    @Id
    private Integer eventTypeId;

    private String eventTypeName;

    private String eventTypeIdXRefs;
}
