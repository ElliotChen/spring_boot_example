package com.sportradar.sdp.domain.sdh;

import com.sportradar.sdp.domain.common.BaseEventType;
import com.sportradar.sdp.domain.dgt.League;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "EventType")
@Data
@Slf4j
public class EventType extends BaseEventType {
	@Transient
	private String brEventTypeIdXRefs;

	@Transient
	private String dgtEventTypeIdXRefs;

	@Transient
	private List<com.sportradar.sdp.domain.dgt.EventType> dgtEventTypeXRefs;

	@Transient
	private List<com.sportradar.sdp.domain.br.EventType> brEventTypeXRefs;

	@OneToMany(fetch= FetchType.LAZY, mappedBy="eventTypeId", cascade = {CascadeType.PERSIST})
	private List<EventTypeLanguage> languages;
}
