package com.sportradar.sdh.service.impl;

import com.sportradar.sdh.dao.dgt.DgtLeagueDao;
import com.sportradar.sdh.domain.dgt.League;
import com.sportradar.sdh.service.DgtLeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DgtLeagueServiceImpl implements DgtLeagueService {
	@Autowired
	private DgtLeagueDao dgtLeagueDao;

	@Override
	public League findById(Long leagueId) {
		return this.dgtLeagueDao.findById(leagueId);
	}

	@Override
	public List<League> findAll() {
		return this.dgtLeagueDao.findAll();
	}

	@Override
	public List<League> findBySportId(Long sportId) {
		return this.dgtLeagueDao.findBySportId(sportId);
	}

	public List<League> findByExample(League league) {
		return this.dgtLeagueDao.findByExample(league);
	}
}
