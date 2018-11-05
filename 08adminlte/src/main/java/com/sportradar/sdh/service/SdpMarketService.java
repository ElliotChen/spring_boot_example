package com.sportradar.sdh.service;

import com.sportradar.sdh.domain.br.Region;
import com.sportradar.sdh.dto.dts.DataTablesInput;
import com.sportradar.sdh.dto.dts.DataTablesOutput;
import com.sportradar.sdh.dto.sdp.MarketDto;

import java.util.List;

public interface SdpMarketService {

	List<MarketDto> findAll();

	MarketDto findById(Long marketId);

	DataTablesOutput<MarketDto> findByPage(DataTablesInput input);

	List<MarketDto> findByIdWithAllLanguage(Long marketId);

	void saveI18N(MarketDto market);

	void saveData(MarketDto market);

	void savePair(MarketDto market);
}
