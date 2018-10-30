package com.sportradar.sdh.ctrl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sportradar.sdh.dao.sdp.SdpLanguageDao;
import com.sportradar.sdh.dao.sdp.SdpMarketDao;
import com.sportradar.sdh.dao.sdp.SdpMarketOptionDao;
import com.sportradar.sdh.domain.sdp.MarketOption;
import com.sportradar.sdh.domain.sdp.Sport;
import com.sportradar.sdh.dto.dts.DataTablesInput;
import com.sportradar.sdh.dto.dts.DataTablesOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/marketOption/*")
public class MarketOptionCtrl {
	private static final String prefix = "marketOption";
	@Autowired
	private SdpMarketOptionDao sdhMarketOptionDao;

	@Autowired
	private SdpLanguageDao sdhLanguageDao;

	@Autowired
	private SdpMarketDao sdhMarketDao;

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
	public DataTablesOutput<MarketOption> findAll(@Valid DataTablesInput input) {
		//DataTablesOutput<MarketOption> ds = this.sdhMarketOptionDao.findAll(input);

		PageHelper.startPage((input.getStart() / input.getLength()) +1 , input.getLength());

		Page<MarketOption> page = this.sdhMarketOptionDao.findByPage();
		DataTablesOutput<MarketOption> ds = new DataTablesOutput<MarketOption>();
		ds.setData(page.getResult());
		ds.setDraw(input.getDraw());
		ds.setRecordsFiltered(page.getTotal());
		ds.setRecordsTotal(page.getTotal());

		return  ds;
	}

	/*
	@GetMapping("/findAll")
	@ResponseBody
	public DataTablesOutput<MarketOption> findAll(@Valid DataTablesInput input) {
		DataTablesOutput<MarketOption> ds = this.sdhMarketOptionDao.findAll(input);
		return  ds;
	}

	@GetMapping("/")
	public String index() {
		return prefix+"/pairIndex";
	}

	@GetMapping("/pair/{expressId}")
	public String pair(@PathVariable String expressId, Model model) {
		MarketOptionPK id = new MarketOptionPK(expressId);

		this.findById(id, model);

		model.addAttribute("markets", Lists.newArrayList(this.sdhMarketDao.findAll()));

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

	@GetMapping("/i18n/{expressId}")
	public String i18n(@PathVariable String expressId, Model model) {
		MarketOptionPK id = new MarketOptionPK(expressId);

		MarketOption market = this.findById(id, model);

		this.addLanguages(model);

		this.addUsedLanguageCodes(market, model);

		return prefix+"/i18n";
	}

	@GetMapping("/dataIndex")
	public String dataIndex() {
		return prefix+"/dataIndex";
	}

	@GetMapping("/data/{expressId}")
	public String data(@PathVariable String expressId, Model model) {
		MarketOptionPK id = new MarketOptionPK(expressId);
		this.findById(id, model);

		model.addAttribute("markets", Lists.newArrayList(this.sdhMarketDao.findAll()));
		return prefix+"/data";
	}

	private MarketOption findById(MarketOptionPK id, Model model) {
		Optional<MarketOption> optionalRegion = this.sdhMarketOptionDao.findById(id);
		MarketOption marketOption = new MarketOption();
		if (optionalRegion.isPresent()) {
			marketOption = optionalRegion.get();
		}

		model.addAttribute("marketOption", marketOption);

		return marketOption;
	}

	private void addLanguages(Model model) {
		List<Language> languageCodes = new ArrayList<>();
		languageCodes.add(new Language(1,"English"));
		languageCodes.add(new Language(512,"中文"));

		model.addAttribute("languageCodes", languageCodes);
	}

	private void addUsedLanguageCodes(MarketOption marketOption, Model model) {
		List<Integer> usedCodes = new ArrayList<>();
		if (null != marketOption.getLanguages()) {
			for (MarketOptionLanguage rl : marketOption.getLanguages()) {
				usedCodes.add(rl.getLanguageCode());
			}
		}

		model.addAttribute("usedCodes", usedCodes);
	}

*/

}
