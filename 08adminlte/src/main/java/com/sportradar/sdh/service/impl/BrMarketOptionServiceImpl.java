package com.sportradar.sdh.service.impl;

import com.sportradar.sdh.dao.br.BrMarketOptionDao;
import com.sportradar.sdh.domain.br.Market;
import com.sportradar.sdh.domain.br.MarketOption;
import com.sportradar.sdh.service.BrMarketOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrMarketOptionServiceImpl implements BrMarketOptionService {
	@Autowired
	private BrMarketOptionDao brMarketOptionDao;
	@Override
	public MarketOption findById(Long marketId, Integer marketTypeId, Integer optionNum, String optionType) {
		return brMarketOptionDao.findById(marketId, marketTypeId, optionNum, optionType);
	}

	@Override
	public Integer countById(Long marketId, Integer marketTypeId, Integer optionNum, String optionType) {
		return this.brMarketOptionDao.countById(marketId, marketTypeId, optionNum, optionType);
	}

	@Override
	public Integer findNextOptionNum(Long marketId, Integer marketTypeId, Integer optionNum, String optionType) {
		return this.brMarketOptionDao.findNextOptionNum(marketId, marketTypeId, optionNum, optionType);
	}

	@Override
	public List<MarketOption> findAll() {
		return this.brMarketOptionDao.findAll();
	}

	@Override
	public List<MarketOption> findByMarket(Market market) {
		return this.brMarketOptionDao.findByMarket(market);
	}

	@Override
	public void updatePair(MarketOption marketOption) {
		String marketOptionIdXRef = marketOption.getMarketOptionRef().getCompositedId();
		this.brMarketOptionDao.updatePair(marketOption, marketOptionIdXRef);
	}
}
