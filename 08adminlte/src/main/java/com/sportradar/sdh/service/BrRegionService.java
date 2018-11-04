package com.sportradar.sdh.service;

import com.sportradar.sdh.domain.br.Region;

import java.util.List;

public interface BrRegionService {
	Region findById(Integer regionNum);

	List<Region> findAll();

	void savePair(Region region);
}
