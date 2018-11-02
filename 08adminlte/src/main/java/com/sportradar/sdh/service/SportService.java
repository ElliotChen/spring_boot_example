package com.sportradar.sdh.service;

import com.sportradar.sdh.dto.dts.DataTablesInput;
import com.sportradar.sdh.dto.dts.DataTablesOutput;
import com.sportradar.sdh.dto.sdp.SportDto;

import java.util.List;

public interface SportService {
	List<SportDto> findAll();

	SportDto findById(Long sportId);

	List<com.sportradar.sdh.domain.dgt.Sport> findAllDgtSports();

	List<com.sportradar.sdh.domain.br.Sport> findAllBrSports();

	DataTablesOutput<SportDto> findByPage(DataTablesInput input);

	List<SportDto> findByIdWithAllLanguage(Long sportId);

	void saveI18N(SportDto sport);

	void saveData(SportDto sport);

	void savePair(SportDto sport);
}
