package com.sportradar.sdh.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sportradar.sdh.dao.br.BrLeagueDao;
import com.sportradar.sdh.dao.dgt.DgtLeagueDao;
import com.sportradar.sdh.dao.sdp.SdpLeagueDao;
import com.sportradar.sdh.dao.sdp.SdpLeagueGroupDao;
import com.sportradar.sdh.dao.sdp.SdpRegionDao;
import com.sportradar.sdh.dao.sdp.SdpSportDao;
import com.sportradar.sdh.domain.common.BaseLeague;
import com.sportradar.sdh.domain.common.SourceTypeEnum;
import com.sportradar.sdh.domain.sdp.League;
import com.sportradar.sdh.dto.dts.DataTablesInput;
import com.sportradar.sdh.dto.dts.DataTablesOutput;
import com.sportradar.sdh.dto.sdp.LeagueDto;
import com.sportradar.sdh.dto.sdp.Translation;
import com.sportradar.sdh.service.SdpLeagueService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SdpLeagueServiceImpl implements SdpLeagueService {
	@Autowired
	private SdpLeagueDao sdpLeagueDao;

	@Autowired
	private DgtLeagueDao dgtLeagueDao;

	@Autowired
	private BrLeagueDao brLeagueDao;

	@Autowired
	private SdpLeagueGroupDao sdpLeagueGroupDao;

	@Autowired
	private SdpRegionDao sdpRegionDao;

	@Autowired
	private SdpSportDao sdpSportDao;

	@Override
	public List<LeagueDto> findAll() {
		return this.convertDto(this.sdpLeagueDao.findAll());
	}

	@Override
	public LeagueDto findById(Long leagueId) {
		return this.convertDto(this.sdpLeagueDao.findById(leagueId));
	}

	@Override
	public List<com.sportradar.sdh.domain.dgt.League> findAllDgtLeagues() {
		return this.dgtLeagueDao.findAll();
	}

	@Override
	public List<com.sportradar.sdh.domain.br.League> findAllBrLeagues() {
		return this.brLeagueDao.findAll();
	}

	@Override
	public DataTablesOutput<LeagueDto> findByPage(DataTablesInput input) {
		PageHelper.startPage((input.getStart() / input.getLength()) +1 , input.getLength());

		if (!input.getOrder().isEmpty()) {
			Integer columnIndex = input.getOrder().get(0).getColumn();
			String name = input.getColumns().get(columnIndex.intValue()).getName();
			PageHelper.orderBy(name);
		}


		Page<League> page = this.sdpLeagueDao.findByPage();
		DataTablesOutput<LeagueDto> ds = new DataTablesOutput<LeagueDto>();
		ds.setData(convertDto(page.getResult()));
		ds.setDraw(input.getDraw());
		ds.setRecordsFiltered(page.getTotal());
		ds.setRecordsTotal(page.getTotal());

		return  ds;
	}

	@Override
	public List<LeagueDto> findByIdWithAllLanguage(Long leagueId) {
		return this.convertDto(this.sdpLeagueDao.findByIdWithAllLanguage(leagueId));
	}

	@Override
	public void saveI18N(LeagueDto league) {
		league.setUpdatedTime(new Date());
		League language = this.sdpLeagueDao.findByIdAndLanguageCodeWithLanguage(league.getLeagueId(), league.getLanguage().getLanguageCode());
		if (language == null) {
			this.sdpLeagueDao.insertI18N(league);
		} else {
			this.sdpLeagueDao.updateI18N(league);
		}
	}

	@Override
	public void saveData(LeagueDto league) {
		Integer count = this.sdpLeagueDao.countById(league.getLeagueId());
		Date now = new Date();

		if (count == 0) {
			league.setLeagueId(this.sdpLeagueDao.findNextId());
			league.setCreatedTime(now);
			league.setUpdatedTime(now);
			this.sdpLeagueDao.insertData(league);
		} else {
			league.setUpdatedTime(now);
			this.sdpLeagueDao.updateData(league);
		}
	}

	@Override
	public void savePair(LeagueDto league) {
		String leagueIdXRefs = league.getDgtLeague().getCompositedId()+"|"+league.getBrLeague().getCompositedId();
		this.sdpLeagueDao.updatePair(league.getLeagueId(), leagueIdXRefs);

		String compositedId = league.getCompositedId();
		this.dgtLeagueDao.updatePair(league.getDgtLeague(), compositedId);
		this.brLeagueDao.updatePair(league.getBrLeague(), compositedId);
	}

	private List<LeagueDto> convertDto(List<League> leagues) {
		List<LeagueDto> result = new ArrayList<>();
		for (League league : leagues) {
			result.add(convertDto(league));
		}
		return result;
	}

	private LeagueDto convertDto(League league) {
		if (null == league) {
			return null;
		}

		LeagueDto sd = new LeagueDto();

		BeanUtils.copyProperties(league, sd);

		for (League translatedRegion :this.sdpLeagueDao.findByIdWithLanguage(sd.getLeagueId())) {
			Translation translation = new Translation();

			translation.setLanguageCode(translatedRegion.getLanguage().getLanguageCode());
			translation.setLanguageName(translatedRegion.getLanguage().getLanguageName());
			translation.setTranslationValue(translatedRegion.getLeagueName());

			sd.getTranslations().add(translation);
		}

		for (BaseLeague baseLeague: league.getLeagueXRefs()) {
			if (SourceTypeEnum.DGT == baseLeague.getSourceType()) {
				sd.setDgtLeague((com.sportradar.sdh.domain.dgt.League) baseLeague);
			} else if (SourceTypeEnum.BR == baseLeague.getSourceType()) {
				sd.setBrLeague((com.sportradar.sdh.domain.br.League) baseLeague);
			}
		}
		return sd;
	}
}
