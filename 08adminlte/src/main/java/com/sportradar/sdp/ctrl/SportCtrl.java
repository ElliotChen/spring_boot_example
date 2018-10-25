package com.sportradar.sdp.ctrl;

import com.sportradar.sdp.dao.sdh.SdhSportDao;
import com.sportradar.sdp.domain.sdh.Language;
import com.sportradar.sdp.domain.sdh.Sport;
import com.sportradar.sdp.domain.sdh.SportLanguage;
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
@RequestMapping("/sport/*")
public class SportCtrl {
	@Autowired
	private SdhSportDao sdhSportDao;

	@GetMapping("/")
	public String index() {
		return "sport/pairIndex";
	}

	@GetMapping("/findAll")
	@ResponseBody
	public DataTablesOutput<Sport> findAll(@Valid DataTablesInput input) {
		DataTablesOutput<Sport> ds = this.sdhSportDao.findAll(input);
		return  ds;
	}

	@GetMapping("/pair/{id}")
	public String pair(@PathVariable Long id, Model model) {
		Optional<Sport> optionalSport = this.sdhSportDao.findById(id);
		Sport sport = new Sport();
		if (optionalSport.isPresent()) {
			sport = optionalSport.get();
		}

		model.addAttribute("sport", sport);

		return "sport/pair";
	}

	@PostMapping("/savePair")
	public String savePair(Model model) {
		model.addAttribute("successFlash", "Success!");
		return "sport/pairIndex";
	}

	@PostMapping("/saveI18n")
	public String saveI18n(Model model) {
		model.addAttribute("successFlash", "Success!");
		return "sport/i18nIndex";
	}

	@PostMapping("/saveData")
	public String saveData(Model model) {
		model.addAttribute("successFlash", "Success!");
		return "sport/dataIndex";
	}

	@GetMapping("/i18nIndex")
	public String i18nIndex() {
		return "sport/i18nIndex";
	}

	@GetMapping("/i18n/{id}")
	public String i18n(@PathVariable Long id, Model model) {

		Optional<Sport> optionalSport = this.sdhSportDao.findById(id);
		Sport sport = new Sport();
		if (optionalSport.isPresent()) {
			sport = optionalSport.get();
		}

		model.addAttribute("sport", sport);

		//this.sdhSportDao.findById(id).ifPresent(o -> model.addAttribute("sport", o));
		List<Language> languageCodes = new ArrayList<>();
		languageCodes.add(new Language(1,"English"));
		languageCodes.add(new Language(512,"中文"));

		model.addAttribute("languageCodes", languageCodes);

		List<Integer> usedCodes = new ArrayList<>();
		if (null != sport.getLanguages()) {
			for (SportLanguage sl : sport.getLanguages()) {
				usedCodes.add(sl.getLanguageCode());
			}
		}

		model.addAttribute("usedCodes", usedCodes);

		return "sport/i18n";
	}

	@GetMapping("/dataIndex")
	public String dataIndex() {
		return "sport/dataIndex";
	}

	@GetMapping("/data/{id}")
	public String data(@PathVariable Long id, Model model) {
		Optional<Sport> optionalSport = this.sdhSportDao.findById(id);
		Sport sport = new Sport();
		if (optionalSport.isPresent()) {
			sport = optionalSport.get();
		}

		model.addAttribute("sport", sport);

		return "sport/data";
	}
}
