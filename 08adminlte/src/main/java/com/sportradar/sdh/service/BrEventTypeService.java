package com.sportradar.sdh.service;

import com.sportradar.sdh.domain.br.EventType;

import java.util.List;

public interface BrEventTypeService {
	List<EventType> findAll();
	EventType findById(Integer eventTypeId);
	List<EventType> findAllForMarket();
	List<EventType> findAllForMarket(Long marketId);
}
