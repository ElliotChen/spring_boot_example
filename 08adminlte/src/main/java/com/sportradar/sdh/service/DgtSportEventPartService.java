package com.sportradar.sdh.service;

import com.sportradar.sdh.domain.dgt.SportEventPart;

import java.util.List;

public interface DgtSportEventPartService {
	SportEventPart findById(Long sportId, Integer eventPartId);

	List<SportEventPart> findAll();

	List<SportEventPart> findAllForSportMarket();
	List<SportEventPart> findAllForSportMarket(Long sportId);
}
