package com.sportradar.sdh.ctrl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sportradar.sdh.dao.sdp.SdpSportDao;
import com.sportradar.sdh.domain.sdp.Sport;
import com.sportradar.sdh.dto.dts.DataTablesInput;
import com.sportradar.sdh.dto.dts.DataTablesOutput;
import com.sportradar.sdh.dto.sdp.SportDto;
import com.sportradar.sdh.dto.sdp.Translation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/sport/*")
public class SportCtrl {

	@Autowired
	private SdpSportDao sdpSportDao;



	@GetMapping("/findAll")
	@ResponseBody
	public DataTablesOutput<SportDto> findAll(@Valid DataTablesInput input) {

		PageHelper.startPage((input.getStart() / input.getLength()) +1 , input.getLength());

		Page<Sport> page = this.sdpSportDao.findByPage();
		DataTablesOutput<SportDto> ds = new DataTablesOutput<SportDto>();

		ds.setData(coverDto(page.getResult()));
		ds.setDraw(input.getDraw());
		ds.setRecordsFiltered(page.getTotal());
		ds.setRecordsTotal(page.getTotal());

		return  ds;
	}

	private List<SportDto> coverDto(List<Sport> sports) {
		List<SportDto> result = new ArrayList<>();
		for (Sport sport : sports) {
			SportDto sd = new SportDto();

			BeanUtils.copyProperties(sport, sd);

			for (Sport translatedSport :this.sdpSportDao.findByIdWithLanguage(sd.getSportId())) {
				Translation translation = new Translation();

				translation.setLanguageCode(translatedSport.getLanguage().getLanguageCode());
				translation.setLanguageName(translatedSport.getLanguage().getLanguageName());
				translation.setTranslationValue(translatedSport.getSportName());

				sd.getTranslations().add(translation);
			}
			result.add(sd);
		}
		return result;
	}

	private void saveDbI18N(SportDto sport) {
		sport.setUpdatedTime(new Date());
		Sport language = this.sdpSportDao.findByIdAndLanguageCodeWithLanguage(sport.getSportId(), sport.getLanguage().getLanguageCode());
		if (language == null) {
			this.sdpSportDao.insertI18N(sport);
		} else {
			this.sdpSportDao.updateI18N(sport);
		}
	}

	@GetMapping("/")
	public String index() {
		return "sport/pairIndex";
	}

	@GetMapping("/i18nIndex")
	public String i18nIndex() {
		return "sport/i18nIndex";
	}

	@GetMapping("/dataIndex")
	public String dataIndex() {
		return "sport/dataIndex";
	}

	@GetMapping("/pair/{id}")
	public String pair(@PathVariable Long id, Model model) {
		Sport sport = this.sdpSportDao.findById(id);
		model.addAttribute("sport", sport);
		return "sport/pair";
	}

	@GetMapping("/i18n/{id}")
	public String i18n(@PathVariable Long id, Model model) {

		Sport sport = this.sdpSportDao.findById(id);
		List<Sport> sports = this.sdpSportDao.findByIdWithAllLanguage(id);

		model.addAttribute("sport", sport);
		model.addAttribute("sports", sports);
		return "sport/i18n";
	}

	@PostMapping("/saveI18n")
	@ResponseBody
	public String saveI18n(SportDto sport,Model model) {
		log.error("Find Sport [{}] - [{}]",sport.getSportId(), sport.getLanguage().getLanguageCode());
		this.saveDbI18N(sport);
		model.addAttribute("successFlash", "Success!");
		return "sport/i18nIndex";
	}

	/*
	@GetMapping("/findAll")
	@ResponseBody
	public DataTablesOutput<Sport> findAll(@Valid DataTablesInput input) {
		DataTablesOutput<Sport> ds = this.sdpSportDao.findAll(input);
		return  ds;
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

		Optional<Sport> optionalSport = this.sdpSportDao.findById(id);
		Sport sport = new Sport();
		if (optionalSport.isPresent()) {
			sport = optionalSport.get();
		}

		model.addAttribute("sport", sport);

		//this.sdpSportDao.findById(id).ifPresent(o -> model.addAttribute("sport", o));
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
		Optional<Sport> optionalSport = this.sdpSportDao.findById(id);
		Sport sport = new Sport();
		if (optionalSport.isPresent()) {
			sport = optionalSport.get();
		}

		model.addAttribute("sport", sport);

		return "sport/data";
	}
	*/
}
