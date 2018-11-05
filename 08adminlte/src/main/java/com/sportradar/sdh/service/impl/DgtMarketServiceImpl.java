package com.sportradar.sdh.service.impl;

import com.sportradar.sdh.dao.dgt.DgtMarketDao;
import com.sportradar.sdh.domain.dgt.Market;
import com.sportradar.sdh.service.DgtMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DgtMarketServiceImpl implements DgtMarketService {
	@Autowired
	private DgtMarketDao dgtMarketDao;

	@Override
	public Market findById(Long marketId) {
		return this.dgtMarketDao.findById(marketId);
	}

	@Override
	public Integer countById(Long marketId) {
		return this.dgtMarketDao.countById(marketId);
	}

	@Override
	public Long findNextId() {
		return this.dgtMarketDao.findNextId();
	}

	@Override
	public List<Market> findAll() {
		return this.dgtMarketDao.findAll();
	}
}
