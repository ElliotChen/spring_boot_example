package com.sportradar.sdh.service;

import com.sportradar.sdh.domain.dgt.MarketOption;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DgtMarketOptionService {
	MarketOption findById(Long marketId, Integer optionNum);

	Integer countById(Long marketId, Integer optionNum);

	Integer findNextOptionNum(Long marketId);

	List<MarketOption> findAll();

	List<MarketOption> findByMarketId(Long marketId);

	void updatePair(MarketOption marketOption);
}
