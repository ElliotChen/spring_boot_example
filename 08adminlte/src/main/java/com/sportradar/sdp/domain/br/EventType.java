package com.sportradar.sdp.domain.br;

import com.sportradar.sdp.domain.common.BaseEventType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "EventType")
@Data
@Slf4j
public class EventType extends BaseEventType {
}
