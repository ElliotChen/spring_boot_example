package com.sportradar.sdh.service;

import com.sportradar.sdh.domain.dgt.Sport;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DgtSportService {
	List<Sport> findAll();

	List<Sport> findByRegionNum(Integer regionNum);
	List<Sport> findAllForRegion();

	List<Sport> findAllForSportMarket();
}
