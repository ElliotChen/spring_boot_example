package com.sportradar.sdh.service;

import com.sportradar.sdh.dto.dts.DataTablesInput;
import com.sportradar.sdh.dto.dts.DataTablesOutput;
import com.sportradar.sdh.dto.sdp.MarketOptionDto;

import java.util.List;

public interface SdpMarketOptionService {

	List<MarketOptionDto> findAll();

	MarketOptionDto findById(Long marketId, Integer optionNum);

	DataTablesOutput<MarketOptionDto> findByPage(DataTablesInput input);

	List<MarketOptionDto> findByIdWithAllLanguage(Long marketId, Integer optionNum);

	void saveI18N(MarketOptionDto market);

	void saveData(MarketOptionDto market);

	void savePair(MarketOptionDto market);
}
