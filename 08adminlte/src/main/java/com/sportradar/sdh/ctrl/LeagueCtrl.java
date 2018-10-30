package com.sportradar.sdh.ctrl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sportradar.sdh.dao.sdp.SdpLanguageDao;
import com.sportradar.sdh.dao.sdp.SdpLeagueDao;
import com.sportradar.sdh.domain.sdp.League;
import com.sportradar.sdh.dto.dts.DataTablesInput;
import com.sportradar.sdh.dto.dts.DataTablesOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/league/*")
public class LeagueCtrl {
	private static final String prefix = "league";
	@Autowired
	private SdpLeagueDao sdpLeagueDao;

	private SdpLanguageDao sdpLanguageDao;

	@GetMapping("/")
	public String index() {
		return prefix+"/pairIndex";
	}

	@GetMapping("/i18nIndex")
	public String i18nIndex() {
		return prefix+"/i18nIndex";
	}

	@GetMapping("/dataIndex")
	public String dataIndex() {
		return prefix+"/dataIndex";
	}

	@GetMapping("/findAll")
	@ResponseBody
	public DataTablesOutput<League> findAll(@Valid DataTablesInput input) {
		//DataTablesOutput<MarketOption> ds = this.sdhMarketOptionDao.findAll(input);

		PageHelper.startPage((input.getStart() / input.getLength()) +1 , input.getLength());

		Page<League> page = this.sdpLeagueDao.findByPage();
		DataTablesOutput<League> ds = new DataTablesOutput<League>();
		ds.setData(page.getResult());
		ds.setDraw(input.getDraw());
		ds.setRecordsFiltered(page.getTotal());
		ds.setRecordsTotal(page.getTotal());

		return  ds;
	}
	/*
	@GetMapping("/findAll")
	@ResponseBody
	public DataTablesOutput<League> findAll(@Valid DataTablesInput input) {
		DataTablesOutput<League> ds = this.sdpLeagueDao.findAll(input);
		return  ds;
	}

	@GetMapping("/")
	public String index() {
		return prefix+"/pairIndex";
	}

	@GetMapping("/pair/{id}")
	public String pair(@PathVariable Long id, Model model) {
		this.findById(id, model);

		return prefix+"/pair";
	}

	@PostMapping("/savePair")
	public String savePair(Model model) {
		model.addAttribute("successFlash", "Success!");
		return prefix+"/pairIndex";
	}

	@PostMapping("/saveI18n")
	public String saveI18n(Model model) {
		model.addAttribute("successFlash", "Success!");
		return prefix+"/i18nIndex";
	}

	@PostMapping("/saveData")
	public String saveData(Model model) {
		model.addAttribute("successFlash", "Success!");
		return prefix+"/dataIndex";
	}

	@GetMapping("/i18nIndex")
	public String i18nIndex() {
		return prefix+"/i18nIndex";
	}

	@GetMapping("/i18n/{id}")
	public String i18n(@PathVariable Long id, Model model) {

		League league = this.findById(id, model);

		this.addLanguages(model);

		this.addUsedLanguageCodes(league, model);

		return prefix+"/i18n";
	}

	@GetMapping("/dataIndex")
	public String dataIndex() {
		return prefix+"/dataIndex";
	}

	@GetMapping("/data/{id}")
	public String data(@PathVariable Long id, Model model) {
		this.findById(id, model);

		return prefix+"/data";
	}


	private League findById(Long id, Model model) {
		Optional<League> optionalRegion = this.sdpLeagueDao.findById(id);
		League league = new League();
		if (optionalRegion.isPresent()) {
			league = optionalRegion.get();
		}

		model.addAttribute("league", league);

		return league;
	}

	private void addLanguages(Model model) {
		List<Language> languageCodes = new ArrayList<>();
		languageCodes.add(new Language(1,"English"));
		languageCodes.add(new Language(512,"中文"));

		model.addAttribute("languageCodes", languageCodes);
	}

	private void addUsedLanguageCodes(League league, Model model) {
		List<Integer> usedCodes = new ArrayList<>();
		if (null != league.getLanguages()) {
			for (LeagueLanguage rl : league.getLanguages()) {
				usedCodes.add(rl.getLanguageCode());
			}
		}

		model.addAttribute("usedCodes", usedCodes);
	}


*/
}
