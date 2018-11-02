package com.sportradar.sdh.service.impl;

import com.sportradar.sdh.domain.dgt.Region;
import com.sportradar.sdh.dto.dts.DataTablesInput;
import com.sportradar.sdh.dto.dts.DataTablesOutput;
import com.sportradar.sdh.dto.sdp.RegionDto;
import com.sportradar.sdh.service.RegionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {
	@Override
	public List<RegionDto> findAll() {
		return null;
	}

	@Override
	public RegionDto findById(Integer regionNum) {
		return null;
	}

	@Override
	public List<Region> findAllDgtRegions() {
		return null;
	}

	@Override
	public List<com.sportradar.sdh.domain.br.Region> findAllBrRegions() {
		return null;
	}

	@Override
	public DataTablesOutput<RegionDto> findByPage(DataTablesInput input) {
		return null;
	}

	@Override
	public List<RegionDto> findByIdWithAllLanguage(Integer regionNum) {
		return null;
	}

	@Override
	public void saveI18N(RegionDto region) {

	}

	@Override
	public void saveData(RegionDto region) {

	}

	@Override
	public void savePair(RegionDto region) {

	}
}
