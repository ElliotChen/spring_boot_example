package com.sportradar.sdh.service.impl;

import com.sportradar.sdh.dao.sdp.SdpLeagueGroupDao;
import com.sportradar.sdh.domain.sdp.LeagueGroup;
import com.sportradar.sdh.dto.sdp.LeagueGroupDto;
import com.sportradar.sdh.service.LeagueGroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeagueGroupServiceImpl implements LeagueGroupService {
	@Autowired
	private SdpLeagueGroupDao sdpLeagueGroupDao;

	@Override
	public List<LeagueGroupDto> findAll() {
		return this.convertDto(this.sdpLeagueGroupDao.findAll());
	}

	@Override
	public LeagueGroupDto findById(Long leagueGroupId) {
		return this.convertDto(this.sdpLeagueGroupDao.findById(leagueGroupId));
	}

	private List<LeagueGroupDto> convertDto(List<LeagueGroup> leagueGroups) {
		List<LeagueGroupDto> result = new ArrayList<>();
		for (LeagueGroup leagueGroup : leagueGroups) {
			result.add(this.convertDto(leagueGroup));
		}
		return result;
	}

	private LeagueGroupDto convertDto(LeagueGroup leagueGroup) {
		LeagueGroupDto sd = new LeagueGroupDto();

		BeanUtils.copyProperties(leagueGroup, sd);

		return sd;
	}
}
