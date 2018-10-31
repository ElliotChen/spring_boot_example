package com.sportradar.sdh.ctrl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sportradar.sdh.dao.sdp.SdpEventTypeDao;
import com.sportradar.sdh.dao.sdp.SdpLanguageDao;
import com.sportradar.sdh.dao.sdp.SdpMarketDao;
import com.sportradar.sdh.domain.sdp.Market;
import com.sportradar.sdh.dto.dts.DataTablesInput;
import com.sportradar.sdh.dto.dts.DataTablesOutput;
import com.sportradar.sdh.dto.sdp.MarketDto;
import com.sportradar.sdh.dto.sdp.Translation;
import com.sportradar.sdh.dto.system.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/market/*")
public class MarketCtrl {
	private static final String prefix = "market";
	@Autowired
	private SdpMarketDao sdpMarketDao;

	@Autowired
	private SdpEventTypeDao sdpEventTypeDao;

	private SdpLanguageDao sdhLanguageDao;

	@GetMapping("/pair")
	public String index() {
		return prefix+"/pairIndex";
	}

	@GetMapping("/i18n")
	public String i18nIndex() {
		return prefix+"/i18nIndex";
	}

	@GetMapping("/data")
	public String dataIndex() {
		return prefix+"/dataIndex";
	}

	@GetMapping("/findAll")
	@ResponseBody
	public DataTablesOutput<MarketDto> findAll(@Valid DataTablesInput input) {
		//DataTablesOutput<MarketOption> ds = this.sdhMarketOptionDao.findAll(input);

		PageHelper.startPage((input.getStart() / input.getLength()) +1 , input.getLength());

		Page<Market> page = this.sdpMarketDao.findByPage();
		DataTablesOutput<MarketDto> ds = new DataTablesOutput<MarketDto>();
		ds.setData(coverDto(page.getResult()));
		ds.setDraw(input.getDraw());
		ds.setRecordsFiltered(page.getTotal());
		ds.setRecordsTotal(page.getTotal());

		return  ds;
	}

	@GetMapping("/pair/{id}")
	public String pair(@PathVariable Long id, Model model) {
		Market sport = this.sdpMarketDao.findById(id);
		model.addAttribute("market", sport);
		return prefix+"/pair";
	}

	@GetMapping("/i18n/{id}")
	public String i18n(@PathVariable Long id, Model model) {

		Market sport = this.sdpMarketDao.findById(id);
		List<Market> sports = this.sdpMarketDao.findByIdWithAllLanguage(id);

		model.addAttribute("market", sport);
		model.addAttribute("markets", sports);
		return prefix+"/i18n";
	}

	@PostMapping("/i18n/save")
	@ResponseBody
	public ApiResult saveI18n(MarketDto league, Model model) {
		log.error("Find Market [{}] - [{}]",league.getMarketId(), league.getLanguage().getLanguageCode());
		this.saveDbI18N(league);
		model.addAttribute("successFlash", "Success!");

		ApiResult apiResult = new ApiResult();
		apiResult.setStatus(HttpStatus.OK);
		apiResult.setMessage("Save Success!");
		return apiResult;
	}

	@GetMapping("/data/{id}")
	public String data(@PathVariable Long id, Model model) {
		Market market = this.sdpMarketDao.findById(id);

		if (null == market) {
			market = new Market();
		}

		model.addAttribute("market", market);

		model.addAttribute("eventTypes", this.sdpEventTypeDao.findAll());
		return prefix+"/data";

	}

	@PostMapping("/data/save")
	public String saveData(MarketDto market, Model model) {

		this.saveDbData(market);

		model.addAttribute("successFlash", "Success!");
		return prefix+"/dataIndex";
	}

	private List<MarketDto> coverDto(List<Market> markets) {
		List<MarketDto> result = new ArrayList<>();
		for (Market market : markets) {
			MarketDto sd = new MarketDto();

			BeanUtils.copyProperties(market, sd);

			for (Market translatedRegion :this.sdpMarketDao.findByIdWithLanguage(sd.getMarketId())) {
				Translation translation = new Translation();

				translation.setLanguageCode(translatedRegion.getLanguage().getLanguageCode());
				translation.setLanguageName(translatedRegion.getLanguage().getLanguageName());
				translation.setTranslationValue(translatedRegion.getMarketName());

				sd.getTranslations().add(translation);
			}
			result.add(sd);
		}
		return result;
	}

	private void saveDbI18N(MarketDto market) {
		market.setUpdatedTime(new Date());
		Market mk = this.sdpMarketDao.findByIdAndLanguageCodeWithLanguage(market.getMarketId(), market.getLanguage().getLanguageCode());
		if (mk == null) {
			this.sdpMarketDao.insertI18N(market);
		} else {
			this.sdpMarketDao.updateI18N(market);
		}
	}

	private void saveDbData(MarketDto market) {
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
	/*
	@GetMapping("/findAll")
	@ResponseBody
	public DataTablesOutput<Market> findAll(@Valid DataTablesInput input) {
		DataTablesOutput<Market> ds = this.sdpMarketDao.findAll(input);
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

		Market market = this.findById(id, model);

		this.addLanguages(model);

		this.addUsedLanguageCodes(market, model);

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


	private Market findById(Long id, Model model) {
		Optional<Market> optionalRegion = this.sdpMarketDao.findById(id);
		Market market = new Market();
		if (optionalRegion.isPresent()) {
			market = optionalRegion.get();
		}

		model.addAttribute("market", market);

		return market;
	}

	private void addLanguages(Model model) {
		List<Language> languageCodes = new ArrayList<>();
		languageCodes.add(new Language(1,"English"));
		languageCodes.add(new Language(512,"中文"));

		model.addAttribute("languageCodes", languageCodes);
	}

	private void addUsedLanguageCodes(Market market, Model model) {
		List<Integer> usedCodes = new ArrayList<>();
		if (null != market.getLanguages()) {
			for (MarketLanguage rl : market.getLanguages()) {
				usedCodes.add(rl.getLanguageCode());
			}
		}

		model.addAttribute("usedCodes", usedCodes);
	}

*/

}
