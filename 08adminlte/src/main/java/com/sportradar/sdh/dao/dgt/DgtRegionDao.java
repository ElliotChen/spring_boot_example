package com.sportradar.sdh.dao.dgt;

import com.sportradar.sdh.domain.dgt.Region;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DgtRegionDao {
	Region findById(@Param("regionNum") Integer regionNum);

	List<Region> findAll();
}
