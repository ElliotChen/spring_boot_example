package com.sportradar.sdh.service.impl;

import com.sportradar.sdh.dao.dgt.DgtSportDao;
import com.sportradar.sdh.domain.dgt.Sport;
import com.sportradar.sdh.service.DgtSportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DgtSportServiceImpl implements DgtSportService {
	@Autowired
	private DgtSportDao dgtSportDao;
	@Override
	public List<Sport> findAll() {
		return this.dgtSportDao.findAll();
	}

	@Override
	public List<Sport> findByRegionNum(Integer regionNum) {
		return this.dgtSportDao.findByRegionNum(regionNum);
	}

	@Override
	public List<Sport> findAllForRegion() {
		return this.dgtSportDao.findAllForRegion();
	}

	@Override
	public List<Sport> findAllForSportMarket() {
		return this.dgtSportDao.findAllForSportMarket();
	}


}
