package com.sportradar.sdh.domain.sdp;

import com.sportradar.sdh.domain.common.BaseEventType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;

@Data
@Slf4j
public class EventType extends BaseEventType {
	private String brEventTypeIdXRefs;

	private String dgtEventTypeIdXRefs;

	private List<com.sportradar.sdh.domain.dgt.EventType> dgtEventTypeXRefs;

	private List<com.sportradar.sdh.domain.br.EventType> brEventTypeXRefs;

}
