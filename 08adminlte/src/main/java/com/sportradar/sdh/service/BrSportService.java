package com.sportradar.sdh.service;

import com.sportradar.sdh.domain.br.Sport;

import java.util.List;

public interface BrSportService {
	List<Sport> findAll();

	List<Sport> findByRegionNum(Integer regionNum);
	List<Sport> findAllForRegion();
}
