package com.sportradar.sdh.service.impl;

import com.sportradar.sdh.dao.br.BrMarketDao;
import com.sportradar.sdh.domain.br.Market;
import com.sportradar.sdh.service.BrMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrMarketServiceImpl implements BrMarketService {
	@Autowired
	private BrMarketDao brMarketDao;
	@Override
	public Market findById(Long marketId, Integer marketTypeId) {
		return this.brMarketDao.findById(marketId, marketTypeId);
	}

	@Override
	public Integer countById(Long marketId, Integer marketTypeId) {
		return this.brMarketDao.countById(marketId, marketTypeId);
	}

	@Override
	public Long findNextId() {
		return this.brMarketDao.findNextId();
	}

	@Override
	public List<Market> findAll() {
		return this.brMarketDao.findAll();
	}

	@Override
	public List<Market> findByMarketTypeId(Integer marketTypeId) {
		return this.brMarketDao.findByMarketTypeId(marketTypeId);
	}
}
