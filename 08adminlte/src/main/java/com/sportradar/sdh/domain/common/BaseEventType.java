package com.sportradar.sdh.domain.common;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
public abstract class BaseEventType {

    protected Integer eventTypeId;

    protected String eventTypeName;

    protected String eventTypeIdXRefs;
}
