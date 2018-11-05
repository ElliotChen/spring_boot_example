package com.sportradar.sdh.service.impl;

import com.sportradar.sdh.dao.dgt.DgtPeriodDao;
import com.sportradar.sdh.domain.dgt.Period;
import com.sportradar.sdh.service.DgtPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DgtPeriodServiceImpl implements DgtPeriodService {
	@Autowired
	private DgtPeriodDao dgtPeriodDao;
	@Override
	public Period findById(Long sportId, Integer eventPartId, Integer periodNum) {
		return this.dgtPeriodDao.findById(sportId, eventPartId, periodNum);
	}

	@Override
	public List<Period> findAll() {
		return this.dgtPeriodDao.findAll();
	}

	@Override
	public List<Period> findAllForSportMarket() {
		return this.dgtPeriodDao.findAllForSportMarket(null, null, null);
	}

	@Override
	public List<Period> findAllForSportMarket(Long sportId, Integer eventPartId) {
		return this.dgtPeriodDao.findAllForSportMarket(sportId, eventPartId, null);
	}
}
