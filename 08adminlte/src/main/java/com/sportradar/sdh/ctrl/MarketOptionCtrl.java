package com.sportradar.sdh.ctrl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sportradar.sdh.dao.sdp.SdpLanguageDao;
import com.sportradar.sdh.dao.sdp.SdpMarketDao;
import com.sportradar.sdh.dao.sdp.SdpMarketOptionDao;
import com.sportradar.sdh.domain.sdp.Market;
import com.sportradar.sdh.domain.sdp.MarketOption;
import com.sportradar.sdh.domain.sdp.Sport;
import com.sportradar.sdh.dto.dts.DataTablesInput;
import com.sportradar.sdh.dto.dts.DataTablesOutput;
import com.sportradar.sdh.dto.sdp.MarketDto;
import com.sportradar.sdh.dto.sdp.MarketOptionDto;
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
@RequestMapping("/marketOption/*")
public class MarketOptionCtrl {
	private static final String prefix = "marketOption";
	@Autowired
	private SdpMarketOptionDao sdhMarketOptionDao;

	@Autowired
	private SdpLanguageDao sdhLanguageDao;

	@Autowired
	private SdpMarketDao sdhMarketDao;

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
	public DataTablesOutput<MarketOptionDto> findAll(@Valid DataTablesInput input) {
		//DataTablesOutput<MarketOption> ds = this.sdhMarketOptionDao.findAll(input);

		PageHelper.startPage((input.getStart() / input.getLength()) +1 , input.getLength());

		Page<MarketOption> page = this.sdhMarketOptionDao.findByPage();
		DataTablesOutput<MarketOptionDto> ds = new DataTablesOutput<MarketOptionDto>();
		ds.setData(coverDto(page.getResult()));
		ds.setDraw(input.getDraw());
		ds.setRecordsFiltered(page.getTotal());
		ds.setRecordsTotal(page.getTotal());

		return  ds;
	}

	@GetMapping("/pair/{marketId}/{optionNum}")
	public String pair(@PathVariable Long marketId, @PathVariable Integer optionNum, Model model) {
		MarketOption sport = this.sdhMarketOptionDao.findById(marketId, optionNum);
		model.addAttribute("market", sport);
		return prefix+"/pair";
	}

	@GetMapping("/i18n/{marketId}/{optionNum}")
	public String i18n(@PathVariable Long marketId, @PathVariable Integer optionNum, Model model) {

		MarketOption marketOption = this.sdhMarketOptionDao.findById(marketId, optionNum);
		List<MarketOption> marketOptions = this.sdhMarketOptionDao.findByIdWithAllLanguage(marketId, optionNum);

		model.addAttribute("marketOption", marketOption);
		model.addAttribute("marketOptions", marketOptions);
		return prefix+"/i18n";
	}

	@PostMapping("/i18n/save")
	@ResponseBody
	public ApiResult saveI18n(MarketOptionDto marketOptionDto, Model model) {
		log.error("Find MarketOpton [{}][{}] - [{}]",marketOptionDto.getMarketId(), marketOptionDto.getOptionNum(), marketOptionDto.getLanguage().getLanguageCode());
		this.saveDbI18N(marketOptionDto);
		model.addAttribute("successFlash", "Success!");

		ApiResult apiResult = new ApiResult();
		apiResult.setStatus(HttpStatus.OK);
		apiResult.setMessage("Save Success!");
		return apiResult;
	}

	@GetMapping("/data/{marketId}/{optionNum}")
	public String data(@PathVariable Long marketId, @PathVariable Integer optionNum, Model model) {
		/*
		MarketOptionPK id = new MarketOptionPK(expressId);
		this.findById(id, model);

		model.addAttribute("markets", Lists.newArrayList(this.sdhMarketDao.findAll()));
		*/

		MarketOption marketOption = this.sdhMarketOptionDao.findById(marketId, optionNum);

		if (null == marketOption) {
			marketOption = new MarketOption();
		}

		model.addAttribute("marketOption", marketOption);
		model.addAttribute("markets", this.sdhMarketDao.findAll());
		return prefix+"/data";
	}

	@PostMapping("/data/save")
	public String saveData(MarketOption marketOption, Model model) {
		this.saveDbData(marketOption);
		model.addAttribute("successFlash", "Success!");
		return prefix+"/dataIndex";
	}

	private List<MarketOptionDto> coverDto(List<MarketOption> markets) {
		List<MarketOptionDto> result = new ArrayList<>();
		for (MarketOption market : markets) {
			MarketOptionDto sd = new MarketOptionDto();

			BeanUtils.copyProperties(market, sd);

			for (MarketOption translatedRegion :this.sdhMarketOptionDao.findByIdWithLanguage(sd.getMarketId(), sd.getOptionNum())) {
				Translation translation = new Translation();

				translation.setLanguageCode(translatedRegion.getLanguage().getLanguageCode());
				translation.setLanguageName(translatedRegion.getLanguage().getLanguageName());
				translation.setTranslationValue(translatedRegion.getFullName());

				sd.getTranslations().add(translation);
			}
			result.add(sd);
		}
		return result;
	}

	private void saveDbI18N(MarketOption marketOption) {
		marketOption.setUpdatedTime(new Date());
		MarketOption mk = this.sdhMarketOptionDao.findByIdAndLanguageCodeWithLanguage(marketOption.getMarketId(), marketOption.getOptionNum(), marketOption.getLanguage().getLanguageCode());
		if (mk == null) {
			this.sdhMarketOptionDao.insertI18N(marketOption);
		} else {
			this.sdhMarketOptionDao.updateI18N(marketOption);
		}
	}

	private void saveDbData(MarketOption marketOption) {
		Integer count = this.sdhMarketOptionDao.countById(marketOption.getMarketId(), marketOption.getOptionNum());
		Date now = new Date();
		log.info("Save MarketOption [{}] - [{}]", marketOption.getMarketId(), marketOption.getOptionNum());
		if (count == 0) {

			Integer optionNum = this.sdhMarketOptionDao.findNextOptionNum(marketOption.getMarketId());
			if (null == optionNum) {
				optionNum = 1;
			}

			marketOption.setOptionNum(optionNum);
			marketOption.setUpdatedTime(now);
			this.sdhMarketOptionDao.insertData(marketOption);
		} else {
			marketOption.setUpdatedTime(now);
			this.sdhMarketOptionDao.updateData(marketOption);
		}
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
