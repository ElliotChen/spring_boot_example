package com.sportradar.sdh.dao.br;

import com.sportradar.sdh.domain.br.Sport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BrSportDao {
	Sport findById(@Param("sportId") Long sportId);

	List<Sport> findAll();

	List<Sport> findByRegionNum(@Param("regionNum") Integer regionNum);
	List<Sport> findAllForRegion();
}
