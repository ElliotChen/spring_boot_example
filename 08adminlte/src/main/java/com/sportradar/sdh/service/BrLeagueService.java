package com.sportradar.sdh.service;

import com.sportradar.sdh.domain.br.League;

import java.util.List;

public interface BrLeagueService {
	League findById(Long leagueId);

	List<League> findAll();

	List<League> findBySportId(Long sportId);

	List<League> findByExample(League league);
}
