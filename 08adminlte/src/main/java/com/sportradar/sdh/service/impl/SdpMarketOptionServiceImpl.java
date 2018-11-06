package com.sportradar.sdh.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sportradar.sdh.dao.br.BrMarketOptionDao;
import com.sportradar.sdh.dao.dgt.DgtMarketOptionDao;
import com.sportradar.sdh.dao.sdp.SdpMarketOptionDao;
import com.sportradar.sdh.domain.common.BaseMarketOption;
import com.sportradar.sdh.domain.common.SourceTypeEnum;
import com.sportradar.sdh.domain.sdp.MarketOption;
import com.sportradar.sdh.dto.dts.DataTablesInput;
import com.sportradar.sdh.dto.dts.DataTablesOutput;
import com.sportradar.sdh.dto.sdp.MarketOptionDto;
import com.sportradar.sdh.dto.sdp.Translation;
import com.sportradar.sdh.service.SdpMarketOptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class SdpMarketOptionServiceImpl implements SdpMarketOptionService {
	@Autowired
	private SdpMarketOptionDao sdpMarketOptionDao;

	@Autowired
	private DgtMarketOptionDao dgtMarketOptionDao;

	@Autowired
	private BrMarketOptionDao brMarketOptionDao;

	@Override
	public List<MarketOptionDto> findAll() {
		return convertDto(sdpMarketOptionDao.findAll());
	}

	@Override
	public MarketOptionDto findById(Long marketId, Integer optionNum) {
		return convertDto(sdpMarketOptionDao.findById(marketId, optionNum));
	}

	@Override
	public DataTablesOutput<MarketOptionDto> findByPage(DataTablesInput input) {
		PageHelper.startPage((input.getStart() / input.getLength()) +1 , input.getLength());

		Page<MarketOption> page = this.sdpMarketOptionDao.findByPage();
		DataTablesOutput<MarketOptionDto> ds = new DataTablesOutput<MarketOptionDto>();
		ds.setData(convertDto(page.getResult()));
		ds.setDraw(input.getDraw());
		ds.setRecordsFiltered(page.getTotal());
		ds.setRecordsTotal(page.getTotal());
		return ds;
	}

	@Override
	public List<MarketOptionDto> findByIdWithAllLanguage(Long marketId, Integer optionNum) {
		return convertDto(this.sdpMarketOptionDao.findByIdWithAllLanguage(marketId,optionNum));
	}

	@Override
	public void saveI18N(MarketOptionDto marketOption) {
		marketOption.setUpdatedTime(new Date());
		MarketOption mk = this.sdpMarketOptionDao.findByIdAndLanguageCodeWithLanguage(marketOption.getMarketId(), marketOption.getOptionNum(), marketOption.getLanguage().getLanguageCode());
		if (mk == null) {
			this.sdpMarketOptionDao.insertI18N(marketOption);
		} else {
			this.sdpMarketOptionDao.updateI18N(marketOption);
		}
	}

	@Override
	public void saveData(MarketOptionDto marketOption) {
		Integer count = this.sdpMarketOptionDao.countById(marketOption.getMarketId(), marketOption.getOptionNum());
		Date now = new Date();
		log.debug("Save MarketOption [{}] - [{}]", marketOption.getMarketId(), marketOption.getOptionNum());
		if (count == 0) {

			Integer optionNum = this.sdpMarketOptionDao.findNextOptionNum(marketOption.getMarketId());
			if (null == optionNum) {
				optionNum = 1;
			}

			marketOption.setOptionNum(optionNum);
			marketOption.setUpdatedTime(now);
			this.sdpMarketOptionDao.insertData(marketOption);
		} else {
			marketOption.setUpdatedTime(now);
			this.sdpMarketOptionDao.updateData(marketOption);
		}
	}

	@Override
	public void savePair(MarketOptionDto marketOption) {
		String marketOptionIdXRefs = marketOption.getDgtMarketOption().getCompositedId()+"|"+marketOption.getBrMarketOption().getCompositedId();
		this.sdpMarketOptionDao.updatePair(marketOption, marketOptionIdXRefs);

		String compositedId = marketOption.getCompositedId();
		this.dgtMarketOptionDao.updatePair(marketOption.getDgtMarketOption(), compositedId);
		this.brMarketOptionDao.updatePair(marketOption.getBrMarketOption(), compositedId);
	}


	private List<MarketOptionDto> convertDto(List<MarketOption> markets) {
		List<MarketOptionDto> result = new ArrayList<>();
		for (MarketOption market : markets) {
			result.add(this.convertDto(market));
		}
		return result;
	}

	private MarketOptionDto convertDto(MarketOption marketOption) {
		if (null == marketOption) {
			return null;
		}

		MarketOptionDto sd = new MarketOptionDto();

		BeanUtils.copyProperties(marketOption, sd);

		for (MarketOption translatedRegion :this.sdpMarketOptionDao.findByIdWithLanguage(sd.getMarketId(), sd.getOptionNum())) {
			Translation translation = new Translation();

			translation.setLanguageCode(translatedRegion.getLanguage().getLanguageCode());
			translation.setLanguageName(translatedRegion.getLanguage().getLanguageName());
			translation.setTranslationValue(translatedRegion.getFullName());

			sd.getTranslations().add(translation);
		}

		for (BaseMarketOption baseEntity: marketOption.getMarketOptionXRefs()) {
			if (SourceTypeEnum.DGT == baseEntity.getSourceType()) {
				sd.setDgtMarketOption((com.sportradar.sdh.domain.dgt.MarketOption) baseEntity);
			} else if (SourceTypeEnum.BR == baseEntity.getSourceType()) {
				sd.setBrMarketOption((com.sportradar.sdh.domain.br.MarketOption) baseEntity);
			}
		}

		return sd;
	}
}
