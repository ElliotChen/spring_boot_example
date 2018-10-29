package com.sportradar.sdh.dao.sdp;

import com.github.pagehelper.Page;
import com.sportradar.sdh.domain.sdp.Region;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SdpRegionDao {
	Region findById(@Param("regionNum") Integer regionNum);

	List<Region> findAll();

	Page<Region> findByPage();
}
