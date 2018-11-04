package com.sportradar.sdh.service;

import com.sportradar.sdh.dto.dts.DataTablesInput;
import com.sportradar.sdh.dto.dts.DataTablesOutput;
import com.sportradar.sdh.dto.sdp.RegionDto;
import com.sportradar.sdh.dto.sdp.SportDto;

import java.util.List;

public interface SdpRegionService {

	List<RegionDto> findAll();

	RegionDto findById(Integer regionNum);

	List<com.sportradar.sdh.domain.br.Region> findAllBrRegions();

	DataTablesOutput<RegionDto> findByPage(DataTablesInput input);

	List<RegionDto> findByIdWithAllLanguage(Integer regionNum);

	void saveI18N(RegionDto region);

	void saveData(RegionDto region);

	void savePair(RegionDto region);

	List<RegionDto> findBySportId(Long sportId);
}
