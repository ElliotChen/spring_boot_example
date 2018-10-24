package com.sportradar.sdp.domain.sdh;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "EventInfo")
@Data
public class EventInfo {

    @Id
    private Long eventId;
    private Integer eventTypeId;
    private String title;
    private Date happenedTime;
    private String content;
    private Integer severity;
    private byte[] impact;
    private String comments;

    @LastModifiedDate
    private Date updateTime;
}
