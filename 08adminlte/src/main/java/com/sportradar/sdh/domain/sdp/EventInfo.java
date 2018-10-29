package com.sportradar.sdh.domain.sdp;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

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
