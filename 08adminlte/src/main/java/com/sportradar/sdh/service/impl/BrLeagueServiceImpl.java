package com.sportradar.sdh.service.impl;

import com.sportradar.sdh.dao.br.BrLeagueDao;
import com.sportradar.sdh.domain.br.League;
import com.sportradar.sdh.service.BrLeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrLeagueServiceImpl implements BrLeagueService {
	@Autowired
	private BrLeagueDao brLeagueDao;

	@Override
	public League findById(Long leagueId) {
		return this.brLeagueDao.findById(leagueId);
	}

	@Override
	public List<League> findAll() {
		return this.brLeagueDao.findAll();
	}

	@Override
	public List<League> findBySportId(Long sportId) {
		return this.brLeagueDao.findBySportId(sportId);
	}

	public List<League> findByExample(League league) {
		return this.brLeagueDao.findByExample(league);
	}
}
