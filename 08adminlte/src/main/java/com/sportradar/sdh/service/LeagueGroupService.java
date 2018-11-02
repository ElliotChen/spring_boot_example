package com.sportradar.sdh.service;

import com.sportradar.sdh.dto.sdp.LeagueGroupDto;

import java.util.List;

public interface LeagueGroupService {
	List<LeagueGroupDto> findAll();

	LeagueGroupDto findById(Long leagueGroupId);
}
