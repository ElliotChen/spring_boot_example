package com.sportradar.sdh.service;

import com.sportradar.sdh.domain.dgt.Market;

import java.util.List;

public interface DgtMarketService {
	Market findById(Long marketId);

	Integer countById(Long marketId);

	Long findNextId();

	List<Market> findAll();
}
