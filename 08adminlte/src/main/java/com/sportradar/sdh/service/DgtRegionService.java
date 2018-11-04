package com.sportradar.sdh.service;

import com.sportradar.sdh.domain.dgt.Region;

import java.util.List;

public interface DgtRegionService {
	Region findById(Integer regionNum);

	List<Region> findAll();

	void savePair(Region region);

	List<Region> findBySportId(Long sportId);
	List<Region> findAllForSport();
}
