package com.sportradar.sdh.service;

import com.sportradar.sdh.domain.br.Market;

import java.util.List;

public interface BrMarketService {
	Market findById(Long marketId, Integer marketTypeId);

	Integer countById(Long marketId, Integer marketTypeId);

	Long findNextId();

	List<Market> findAll();

	List<Market> findByMarketTypeId(Integer marketTypeId);
}
