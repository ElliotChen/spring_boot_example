package com.sportradar.sdh.dao.sdp;

import com.github.pagehelper.Page;
import com.sportradar.sdh.domain.sdp.Sport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SdpSportDao {
	Sport findById(@Param("sportId") Long sportId);

	List<Sport> findAll();

	Page<Sport> findByPage();
}
