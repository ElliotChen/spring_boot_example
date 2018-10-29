package com.sportradar.sdh.domain.common;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
public abstract class BaseEventType {

    @Id
    private Integer eventTypeId;

    private String eventTypeName;

    private String eventTypeIdXRefs;
}
