package com.sportradar.sdh.service.impl;

import com.sportradar.sdh.dao.br.BrRegionDao;
import com.sportradar.sdh.domain.br.Region;
import com.sportradar.sdh.service.BrRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrRegionServiceImpl implements BrRegionService {
	@Autowired
	private BrRegionDao brRegionDao;

	@Override
	public Region findById(Integer regionNum) {
		return this.brRegionDao.findById(regionNum);
	}

	@Override
	public List<Region> findAll() {
		return this.brRegionDao.findAll();
	}

	@Override
	public void savePair(Region region) {
		String compositedId = region.getRegionRef().getCompositedId();

		this.brRegionDao.savePair(region, compositedId);
	}

	@Override
	public List<Region> findBySportId(Long sportId) {
		return this.brRegionDao.findBySportId(sportId);
	}

	@Override
	public List<Region> findAllForSport() {
		return this.brRegionDao.findAllForSport();
	}
}
