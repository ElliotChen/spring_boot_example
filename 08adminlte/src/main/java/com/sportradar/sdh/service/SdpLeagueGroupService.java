package com.sportradar.sdh.service;

import com.sportradar.sdh.dto.sdp.LeagueGroupDto;

import java.util.List;

public interface SdpLeagueGroupService {
	List<LeagueGroupDto> findAll();

	LeagueGroupDto findById(Long leagueGroupId);
}
