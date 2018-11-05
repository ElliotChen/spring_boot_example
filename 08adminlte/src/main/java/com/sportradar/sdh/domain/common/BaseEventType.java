package com.sportradar.sdh.domain.common;

import lombok.Data;

@Data
public abstract class BaseEventType extends BaseEntity {

    protected Integer eventTypeId;

    protected String eventTypeName;

    @Override
    public String getCompositedId() {
        return String.valueOf(this.getEventTypeId());
    }

    @Override
    public void setCompositedId(String compositedId) {
        this.eventTypeId = Integer.parseInt(compositedId);
    };
}
