package com.sportradar.sdp.ctrl;

import com.sportradar.sdp.dao.sdh.SdhLanguageDao;
import com.sportradar.sdp.dao.sdh.SdhRegionDao;
import com.sportradar.sdp.dao.sdh.SdhSportDao;
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
@RequestMapping("/region/*")
public class RegionCtrl {
	private static final String prefix = "region";
	@Autowired
	private SdhRegionDao sdhRegionDao;

	private SdhLanguageDao sdhLanguageDao;

	@GetMapping("/findAll")
	@ResponseBody
	public DataTablesOutput<Region> findAll(@Valid DataTablesInput input) {
		DataTablesOutput<Region> ds = this.sdhRegionDao.findAll(input);
		return  ds;
	}

	@GetMapping("/")
	public String index() {
		return prefix+"/pairIndex";
	}

	@GetMapping("/pair/{id}")
	public String pair(@PathVariable Integer id, Model model) {
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
	public String i18n(@PathVariable Integer id, Model model) {

		Region region = this.findById(id, model);

		this.addLanguages(model);

		this.addUsedLanguageCodes(region, model);

		return prefix+"/i18n";
	}

	@GetMapping("/dataIndex")
	public String dataIndex() {
		return prefix+"/dataIndex";
	}

	@GetMapping("/data/{id}")
	public String data(@PathVariable Integer id, Model model) {
		this.findById(id, model);

		return prefix+"/data";
	}


	private Region findById(Integer id, Model model) {
		Optional<Region> optionalRegion = this.sdhRegionDao.findById(id);
		Region region = new Region();
		if (optionalRegion.isPresent()) {
			region = optionalRegion.get();
		}

		model.addAttribute("region", region);

		return region;
	}

	private void addLanguages(Model model) {
		List<Language> languageCodes = new ArrayList<>();
		languageCodes.add(new Language(1,"English"));
		languageCodes.add(new Language(512,"中文"));

		model.addAttribute("languageCodes", languageCodes);
	}

	private void addUsedLanguageCodes(Region region, Model model) {
		List<Integer> usedCodes = new ArrayList<>();
		if (null != region.getLanguages()) {
			for (RegionLanguage rl : region.getLanguages()) {
				usedCodes.add(rl.getLanguageCode());
			}
		}

		model.addAttribute("usedCodes", usedCodes);
	}


}
