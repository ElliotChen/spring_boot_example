package com.sportradar.sdh.dao.sdp;

import com.sportradar.sdh.domain.sdp.LeagueGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SdpLeagueGroupDao {
	LeagueGroup findById(@Param("leagueGroupId") Long leagueGroupId);

	List<LeagueGroup> findAll();
}
