package com.sportradar.sdh.service;

import com.sportradar.sdh.domain.dgt.Period;

import java.util.List;

public interface DgtPeriodService {
	Period findById(Long sportId, Integer eventPartId, Integer periodNum);

	List<Period> findAll();

	List<Period> findAllForSportMarket();
	List<Period> findAllForSportMarket(Long sportId, Integer eventPartId);
}
