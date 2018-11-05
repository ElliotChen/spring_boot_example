package com.sportradar.sdh.service.impl;

import com.sportradar.sdh.dao.dgt.DgtMarketOptionDao;
import com.sportradar.sdh.domain.dgt.MarketOption;
import com.sportradar.sdh.service.DgtMarketOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DgtMarketOptionServiceImpl implements DgtMarketOptionService {
	@Autowired
	private DgtMarketOptionDao dgtMarketOptionDao;

	@Override
	public MarketOption findById(Long marketId, Integer optionNum) {
		return dgtMarketOptionDao.findById(marketId, optionNum);
	}

	@Override
	public Integer countById(Long marketId, Integer optionNum) {
		return dgtMarketOptionDao.countById(marketId, optionNum);
	}

	@Override
	public Integer findNextOptionNum(Long marketId) {
		return dgtMarketOptionDao.findNextOptionNum(marketId);
	}

	@Override
	public List<MarketOption> findAll() {
		return dgtMarketOptionDao.findAll();
	}

	@Override
	public List<MarketOption> findByMarketId(Long marketId) {
		return dgtMarketOptionDao.findByMarketId(marketId);
	}

	@Override
	public void updatePair(MarketOption marketOption) {
		String marketOptionRef = marketOption.getMarketOptionRef().getCompositedId();
		this.dgtMarketOptionDao.updatePair(marketOption, marketOptionRef);
	}
}
