package com.sportradar.sdh.service.impl;

import com.sportradar.sdh.dao.dgt.DgtRegionDao;
import com.sportradar.sdh.domain.dgt.Region;
import com.sportradar.sdh.service.DgtRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DgtRegionServiceImpl implements DgtRegionService {
	@Autowired
	private DgtRegionDao dgtRegionDao;

	@Override
	public Region findById(Integer regionNum) {
		return this.dgtRegionDao.findById(regionNum);
	}

	@Override
	public List<Region> findAll() {
		return this.dgtRegionDao.findAll();
	}

	@Override
	public void savePair(Region region) {
		String compositedId = region.getRegionRef().getCompositedId();

		this.dgtRegionDao.savePair(region, compositedId);
	}
}
