package com.sportradar.sdh.service.impl;

import com.sportradar.sdh.dao.br.BrEventTypeDao;
import com.sportradar.sdh.domain.br.EventType;
import com.sportradar.sdh.service.BrEventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrEventTypeServiceImpl implements BrEventTypeService {
	@Autowired
	private BrEventTypeDao brEventTypeDao;
	@Override
	public List<EventType> findAll() {
		return this.brEventTypeDao.findAll();
	}

	@Override
	public EventType findById(Integer eventTypeId) {
		return this.brEventTypeDao.findById(eventTypeId);
	}

	@Override
	public List<EventType> findAllForMarket() {
		return this.findAllForMarket(null);
	}

	@Override
	public List<EventType> findAllForMarket(Long marketId) {
		return this.brEventTypeDao.findAllForMarket(marketId);
	}
}
