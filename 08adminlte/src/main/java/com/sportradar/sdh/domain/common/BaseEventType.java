package com.sportradar.sdh.domain.common;

import lombok.Data;

@Data
public abstract class BaseEventType extends BaseEntity {

    protected Integer eventTypeId;

    protected String eventTypeName;

    protected String eventTypeIdXRefs;
}
