package com.sportradar.sdh.dao.br;

import com.sportradar.sdh.domain.br.Region;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BrRegionDao {
	Region findById(@Param("regionNum") Integer regionNum);

	List<Region> findAll();
}
