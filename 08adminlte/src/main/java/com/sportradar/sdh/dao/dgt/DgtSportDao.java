package com.sportradar.sdh.dao.dgt;

import com.sportradar.sdh.domain.dgt.Sport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DgtSportDao {
	Sport findById(@Param("sportId") Long sportId);

	List<Sport> findAll();

	List<Sport> findByRegionNum(@Param("regionNum") Integer regionNum);
	List<Sport> findAllForRegion();

	List<Sport> findAllForSportMarket();
}
