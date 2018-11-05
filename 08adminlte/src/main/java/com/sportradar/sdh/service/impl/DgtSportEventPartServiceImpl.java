package com.sportradar.sdh.service.impl;

import com.sportradar.sdh.dao.dgt.DgtSportEventPartDao;
import com.sportradar.sdh.domain.dgt.SportEventPart;
import com.sportradar.sdh.service.DgtSportEventPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DgtSportEventPartServiceImpl implements DgtSportEventPartService {

	@Autowired
	private DgtSportEventPartDao dgtSportEventPartDao;

	@Override
	public SportEventPart findById(Long sportId, Integer eventPartId) {
		return this.dgtSportEventPartDao.findById(sportId, eventPartId);
	}

	@Override
	public List<SportEventPart> findAll() {
		return this.dgtSportEventPartDao.findAll();
	}

	@Override
	public List<SportEventPart> findAllForSportMarket() {
		return this.dgtSportEventPartDao.findAllForSportMarket();
	}

	@Override
	public List<SportEventPart> findAllForSportMarket(Long sportId) {
		return this.dgtSportEventPartDao.findAllForSportMarket(sportId);
	}
}
