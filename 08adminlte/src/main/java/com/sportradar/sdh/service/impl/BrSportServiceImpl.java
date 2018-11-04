package com.sportradar.sdh.service.impl;

import com.sportradar.sdh.dao.br.BrSportDao;
import com.sportradar.sdh.domain.br.Sport;
import com.sportradar.sdh.service.BrSportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrSportServiceImpl implements BrSportService {
	@Autowired
	private BrSportDao brSportDao;
	@Override
	public List<Sport> findAll() {
		return this.brSportDao.findAll();
	}

	@Override
	public List<Sport> findByRegionNum(Integer regionNum) {
		return this.brSportDao.findByRegionNum(regionNum);
	}

	@Override
	public List<Sport> findAllForRegion() {
		return this.brSportDao.findAllForRegion();
	}
}
