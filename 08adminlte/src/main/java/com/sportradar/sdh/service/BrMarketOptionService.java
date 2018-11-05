package com.sportradar.sdh.service;

import com.sportradar.sdh.domain.br.Market;
import com.sportradar.sdh.domain.br.MarketOption;

import java.util.List;

public interface BrMarketOptionService {
	MarketOption findById(Long marketId, Integer marketTypeId, Integer optionNum, String optionType);

	Integer countById(Long marketId, Integer marketTypeId, Integer optionNum, String optionType);

	Integer findNextOptionNum(Long marketId, Integer marketTypeId, Integer optionNum, String optionType);

	List<MarketOption> findAll();

	List<MarketOption> findByMarket(Market market);

	void updatePair(MarketOption marketOption);

}
