package com.sportradar.sdh.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sportradar.sdh.dao.br.BrSportDao;
import com.sportradar.sdh.dao.dgt.DgtSportDao;
import com.sportradar.sdh.dao.sdp.SdpSportDao;
import com.sportradar.sdh.domain.common.BaseSport;
import com.sportradar.sdh.domain.common.SourceTypeEnum;
import com.sportradar.sdh.domain.sdp.Sport;
import com.sportradar.sdh.dto.dts.DataTablesInput;
import com.sportradar.sdh.dto.dts.DataTablesOutput;
import com.sportradar.sdh.dto.sdp.SportDto;
import com.sportradar.sdh.dto.sdp.Translation;
import com.sportradar.sdh.service.SdpSportService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SdpSportServiceImpl implements SdpSportService {

	@Autowired
	private SdpSportDao sdpSportDao;


	@Autowired
	private DgtSportDao dgtSportDao;

	@Autowired
	private BrSportDao brSportDao;

	@Override
	public List<SportDto> findAll() {
		return this.convertDto(this.sdpSportDao.findAll());
	}

	@Override
	public List<SportDto> findByIdWithAllLanguage(Long sportId) {
		return this.convertDto(this.sdpSportDao.findByIdWithAllLanguage(sportId));
	}

	@Override
	public SportDto findById(Long sportId) {
		return this.convertDto(this.sdpSportDao.findById(sportId));
	}
	@Override
	public DataTablesOutput<SportDto> findByPage(DataTablesInput input) {

		PageHelper.startPage((input.getStart() / input.getLength()) +1 , input.getLength());

		Page<Sport> page = this.sdpSportDao.findByPage();
		DataTablesOutput<SportDto> ds = new DataTablesOutput<SportDto>();

		ds.setData(convertDto(page.getResult()));
		ds.setDraw(input.getDraw());
		ds.setRecordsFiltered(page.getTotal());
		ds.setRecordsTotal(page.getTotal());

		return  ds;
	}


	@Override
	public void saveI18N(SportDto sport) {
		sport.setUpdatedTime(new Date());
		Sport language = this.sdpSportDao.findByIdAndLanguageCodeWithLanguage(sport.getSportId(), sport.getLanguage().getLanguageCode());
		if (language == null) {
			this.sdpSportDao.insertI18N(sport);
		} else {
			this.sdpSportDao.updateI18N(sport);
		}
	}

	@Override
	public void saveData(SportDto sport) {
		Integer count = this.sdpSportDao.countById(sport.getSportId());

		Date now = new Date();

		if (count == 0) {
			sport.setSportId(this.sdpSportDao.findNextId());
			sport.setCreatedTime(now);
			sport.setUpdatedTime(now);
			this.sdpSportDao.insertData(sport);
		} else {
			sport.setUpdatedTime(now);
			this.sdpSportDao.updateData(sport);
		}
	}

	@Override
	public void savePair(SportDto sport) {
		String sportIdXRefs = sport.getDgtSport().getCompositedId()+"|"+sport.getBrSport().getCompositedId();
		this.sdpSportDao.updatePair(sport, sportIdXRefs);

		String compositedId = sport.getCompositedId();
		this.dgtSportDao.updatePair(sport.getDgtSport(), compositedId);
		this.brSportDao.updatePair(sport.getBrSport(), compositedId);
	}


	private List<SportDto> convertDto(List<Sport> sports) {
		List<SportDto> result = new ArrayList<>();
		for (Sport sport : sports) {
			result.add(this.convertDto(sport));
		}
		return result;
	}

	private SportDto convertDto(Sport sport) {
		SportDto sd = new SportDto();

		BeanUtils.copyProperties(sport, sd);

		for (Sport translatedSport :this.sdpSportDao.findByIdWithLanguage(sd.getSportId())) {
			Translation translation = new Translation();

			translation.setLanguageCode(translatedSport.getLanguage().getLanguageCode());
			translation.setLanguageName(translatedSport.getLanguage().getLanguageName());
			translation.setTranslationValue(translatedSport.getSportName());

			sd.getTranslations().add(translation);
		}

		for (BaseSport baseSport: sport.getSportXRefs()) {
			if (SourceTypeEnum.DGT == baseSport.getSourceType()) {
				sd.setDgtSport((com.sportradar.sdh.domain.dgt.Sport) baseSport);
			} else if (SourceTypeEnum.BR == baseSport.getSourceType()) {
				sd.setBrSport((com.sportradar.sdh.domain.br.Sport) baseSport);
			}
		}

		return sd;
	}
}
