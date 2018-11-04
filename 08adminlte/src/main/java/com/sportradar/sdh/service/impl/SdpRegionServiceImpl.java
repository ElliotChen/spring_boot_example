package com.sportradar.sdh.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sportradar.sdh.dao.br.BrRegionDao;
import com.sportradar.sdh.dao.dgt.DgtRegionDao;
import com.sportradar.sdh.dao.sdp.SdpRegionDao;
import com.sportradar.sdh.domain.common.BaseRegionSport;
import com.sportradar.sdh.domain.common.BaseSport;
import com.sportradar.sdh.domain.common.SourceTypeEnum;
import com.sportradar.sdh.domain.sdp.Region;
import com.sportradar.sdh.dto.dts.DataTablesInput;
import com.sportradar.sdh.dto.dts.DataTablesOutput;
import com.sportradar.sdh.dto.sdp.RegionDto;
import com.sportradar.sdh.dto.sdp.Translation;
import com.sportradar.sdh.service.SdpRegionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SdpRegionServiceImpl implements SdpRegionService {

	@Autowired
	private SdpRegionDao sdpRegionDao;

	@Autowired
	private DgtRegionDao dgtRegionDao;

	@Autowired
	private BrRegionDao brRegionDao;

	@Override
	public List<RegionDto> findAll() {
		return this.convertDto(this.sdpRegionDao.findAll());
	}

	@Override
	public RegionDto findById(Integer regionNum) {
		return this.convertDto(this.sdpRegionDao.findById(regionNum));
	}

	/*
	@Override
	public List<com.sportradar.sdh.domain.dgt.Region> findAllDgtRegions() {
		return this.dgtRegionDao.findAll();
	}
	*/

	@Override
	public List<com.sportradar.sdh.domain.br.Region> findAllBrRegions() {
		return this.brRegionDao.findAll();
	}

	@Override
	public DataTablesOutput<RegionDto> findByPage(DataTablesInput input) {
		PageHelper.startPage((input.getStart() / input.getLength()) +1 , input.getLength());

		Page<Region> page = this.sdpRegionDao.findByPage();
		DataTablesOutput<RegionDto> ds = new DataTablesOutput<RegionDto>();
		ds.setData(convertDto(page.getResult()));
		ds.setDraw(input.getDraw());
		ds.setRecordsFiltered(page.getTotal());
		ds.setRecordsTotal(page.getTotal());

		return  ds;
	}

	@Override
	public List<RegionDto> findByIdWithAllLanguage(Integer regionNum) {
		return this.convertDto(this.sdpRegionDao.findByIdWithAllLanguage(regionNum));
	}

	@Override
	public void saveI18N(RegionDto region) {
		region.setUpdatedTime(new Date());
		Region language = this.sdpRegionDao.findByIdAndLanguageCodeWithLanguage(region.getRegionNum(), region.getLanguage().getLanguageCode());
		if (language == null) {
			this.sdpRegionDao.insertI18N(region);
		} else {
			this.sdpRegionDao.updateI18N(region);
		}
	}

	@Override
	public void saveData(RegionDto region) {
		Integer count = this.sdpRegionDao.countById(region.getRegionNum());
		Date now = new Date();
		if (count == 0) {
			region.setRegionNum(this.sdpRegionDao.findNextId());
			region.setCreatedTime(now);
			region.setUpdatedTime(now);
			this.sdpRegionDao.insertData(region);
		} else {
			region.setUpdatedTime(now);
			this.sdpRegionDao.updateData(region);
		}
	}

	@Override
	public void savePair(RegionDto region) {
		String regionNumXRefs = region.getDgtRegionSport().getCompositedId()+"|"+region.getBrRegionSport().getCompositedId();
		this.sdpRegionDao.updatePair(region.getRegionNum(), regionNumXRefs);
	}

	private List<RegionDto> convertDto(List<Region> regions) {
		List<RegionDto> result = new ArrayList<>();
		for (Region region : regions) {

			result.add(this.convertDto(region));
		}
		return result;
	}

	private RegionDto convertDto(Region region) {
		RegionDto sd = new RegionDto();

		BeanUtils.copyProperties(region, sd);

		for (com.sportradar.sdh.domain.sdp.Region translatedRegion :this.sdpRegionDao.findByIdWithLanguage(sd.getRegionNum())) {
			Translation translation = new Translation();

			translation.setLanguageCode(translatedRegion.getLanguage().getLanguageCode());
			translation.setLanguageName(translatedRegion.getLanguage().getLanguageName());
			translation.setTranslationValue(translatedRegion.getRegionFullName());

			sd.getTranslations().add(translation);
		}

		for (BaseRegionSport baseRegionSport: region.getRegionSportXRefs()) {
			if (SourceTypeEnum.DGT == baseRegionSport.getSourceType()) {
				sd.setDgtRegionSport((com.sportradar.sdh.domain.dgt.RegionSport) baseRegionSport);
			} else if (SourceTypeEnum.BR == baseRegionSport.getSourceType()) {
				sd.setBrRegionSport((com.sportradar.sdh.domain.br.RegionSport) baseRegionSport);
			}
		}

		return sd;
	}

}
