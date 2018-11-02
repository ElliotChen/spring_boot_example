package com.sportradar.sdh.service;

import com.sportradar.sdh.domain.sdp.League;
import com.sportradar.sdh.dto.dts.DataTablesInput;
import com.sportradar.sdh.dto.dts.DataTablesOutput;
import com.sportradar.sdh.dto.sdp.LeagueDto;

import java.util.List;

public interface LeagueService {
	List<LeagueDto> findAll();

	LeagueDto findById(Long leagueId);

	List<com.sportradar.sdh.domain.dgt.League> findAllDgtLeagues();

	List<com.sportradar.sdh.domain.br.League> findAllBrLeagues();

	DataTablesOutput<LeagueDto> findByPage(DataTablesInput input);

	List<LeagueDto> findByIdWithAllLanguage(Long leagueId);

	void saveI18N(LeagueDto league);

	void saveData(LeagueDto league);

	void savePair(LeagueDto league);
}
