package com.sportradar.sdh.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sportradar.sdh.dao.br.BrMarketDao;
import com.sportradar.sdh.dao.dgt.DgtSportMarketDao;
import com.sportradar.sdh.dao.sdp.SdpMarketDao;
import com.sportradar.sdh.domain.common.BaseEntity;
import com.sportradar.sdh.domain.common.SourceTypeEnum;
import com.sportradar.sdh.domain.sdp.Market;
import com.sportradar.sdh.dto.dts.DataTablesInput;
import com.sportradar.sdh.dto.dts.DataTablesOutput;
import com.sportradar.sdh.dto.dts.Order;
import com.sportradar.sdh.dto.sdp.MarketDto;
import com.sportradar.sdh.dto.sdp.Translation;
import com.sportradar.sdh.service.SdpMarketService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SdpMarketServiceImpl implements SdpMarketService {

	@Autowired
	private SdpMarketDao sdpMarketDao;

	@Autowired
	private DgtSportMarketDao dgtSportMarketDao;

	@Autowired
	private BrMarketDao brMarketDao;

	@Override
	public List<MarketDto> findAll() {
		return this.convertDto(this.sdpMarketDao.findAll());
	}

	@Override
	public MarketDto findById(Long marketId) {
		return this.convertDto(this.sdpMarketDao.findById(marketId));
	}

	@Override
	public DataTablesOutput<MarketDto> findByPage(DataTablesInput input) {
		PageHelper.startPage((input.getStart() / input.getLength()) +1 , input.getLength());
		if (!input.getOrder().isEmpty()) {
			Order order = input.getOrder().get(0);
			Integer columnIndex = order.getColumn();
			String dir = order.getDir();
			String name = input.getColumns().get(columnIndex.intValue()).getData();
			String sort = name + " " +dir;
			PageHelper.orderBy(sort);
		}
		Page<Market> page = this.sdpMarketDao.findByPage();
		DataTablesOutput<MarketDto> ds = new DataTablesOutput<MarketDto>();
		ds.setData(convertDto(page.getResult()));
		ds.setDraw(input.getDraw());
		ds.setRecordsFiltered(page.getTotal());
		ds.setRecordsTotal(page.getTotal());

		return ds;
	}

	@Override
	public List<MarketDto> findByIdWithAllLanguage(Long marketId) {
		return this.convertDto(this.sdpMarketDao.findByIdWithAllLanguage(marketId));
	}

	@Override
	public void saveI18N(MarketDto market) {
		market.setUpdatedTime(new Date());
		Market mk = this.sdpMarketDao.findByIdAndLanguageCodeWithLanguage(market.getMarketId(), market.getLanguage().getLanguageCode());
		if (mk == null) {
			this.sdpMarketDao.insertI18N(market);
		} else {
			this.sdpMarketDao.updateI18N(market);
		}
	}

	@Override
	public void saveData(MarketDto market) {
		Integer count = this.sdpMarketDao.countById(market.getMarketId());
		Date now = new Date();

		if (count == 0) {
			market.setMarketId(this.sdpMarketDao.findNextId());
			market.setCreatedTime(now);
			market.setUpdatedTime(now);
			this.sdpMarketDao.insertData(market);
		} else {
			market.setUpdatedTime(now);
			this.sdpMarketDao.updateData(market);
		}
	}

	@Override
	public void savePair(MarketDto market) {
		String marketIdXRefs = market.getDgtSportMarket().getCompositedId()+"|"+market.getBrMarket().getCompositedId();
		this.sdpMarketDao.updatePair(market, marketIdXRefs);

		String compositedId = market.getCompositedId();
		this.dgtSportMarketDao.updatePair(market.getDgtSportMarket(), compositedId);
		this.brMarketDao.updatePair(market.getBrMarket(), compositedId);
	}


	private List<MarketDto> convertDto(List<Market> markets) {
		List<MarketDto> result = new ArrayList<>();
		for (Market market : markets) {
			result.add(this.convertDto(market));
		}
		return result;
	}

	private MarketDto convertDto(Market market) {
		if (null == market) {
			return null;
		}

		MarketDto sd = new MarketDto();

		BeanUtils.copyProperties(market, sd);

		for (Market translatedRegion :this.sdpMarketDao.findByIdWithLanguage(sd.getMarketId())) {
			Translation translation = new Translation();

			translation.setLanguageCode(translatedRegion.getLanguage().getLanguageCode());
			translation.setLanguageName(translatedRegion.getLanguage().getLanguageName());
			translation.setTranslationValue(translatedRegion.getMarketName());

			sd.getTranslations().add(translation);
		}

		for (BaseEntity baseEntity: market.getMarketXRefs()) {
			if (SourceTypeEnum.DGT == baseEntity.getSourceType()) {
				sd.setDgtSportMarket((com.sportradar.sdh.domain.dgt.SportMarket) baseEntity);
			} else if (SourceTypeEnum.BR == baseEntity.getSourceType()) {
				sd.setBrMarket((com.sportradar.sdh.domain.br.Market) baseEntity);
			}
		}
		return sd;
	}
}
