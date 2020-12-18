package com.sportradar.sdh.domain.sdp;

import lombok.Data;

import java.util.Date;

@Data
public class EventInfo {

    private Long eventId;
    private Integer eventTypeId;
    private String title;
    private Date happenedTime;
    private String content;
    private Integer severity;
    private byte[] impact;
    private String comments;

    private Date updateTime;
}
