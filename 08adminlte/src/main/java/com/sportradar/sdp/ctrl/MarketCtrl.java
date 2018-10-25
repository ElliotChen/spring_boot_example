package com.sportradar.sdp.ctrl;

import com.sportradar.sdp.dao.sdh.SdhLanguageDao;
import com.sportradar.sdp.dao.sdh.SdhMarketDao;
import com.sportradar.sdp.domain.sdh.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/market/*")
public class MarketCtrl {
	private static final String prefix = "market";
	@Autowired
	private SdhMarketDao sdhMarketDao;

	private SdhLanguageDao sdhLanguageDao;

	@GetMapping("/findAll")
	@ResponseBody
	public DataTablesOutput<Market> findAll(@Valid DataTablesInput input) {
		DataTablesOutput<Market> ds = this.sdhMarketDao.findAll(input);
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
		Optional<Market> optionalRegion = this.sdhMarketDao.findById(id);
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


}
