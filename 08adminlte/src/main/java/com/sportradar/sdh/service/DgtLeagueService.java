package com.sportradar.sdh.service;

import com.sportradar.sdh.domain.dgt.League;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DgtLeagueService {
	League findById(Long leagueId);

	List<League> findAll();

	List<League> findBySportId(Long sportId);

	List<League> findByExample(League league);
}
