package com.sportradar.sdh.ctrl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sportradar.sdh.dao.sdp.SdpLanguageDao;
import com.sportradar.sdh.dao.sdp.SdpRegionDao;
import com.sportradar.sdh.domain.sdp.*;
import com.sportradar.sdh.dto.dts.DataTablesOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/region/*")
public class RegionCtrl {
	private static final String prefix = "region";
	@Autowired
	private SdpRegionDao sdpRegionDao;

	private SdpLanguageDao sdhLanguageDao;

	@GetMapping("/findAll")
	@ResponseBody
	public DataTablesOutput<Region> findAll(@Valid com.sportradar.sdh.dto.dts.DataTablesInput input) {

		PageHelper.startPage((input.getStart() / input.getLength()) +1 , input.getLength());

		Page<Region> page = this.sdpRegionDao.findByPage();
		DataTablesOutput<Region> ds = new DataTablesOutput<Region>();
		ds.setData(page.getResult());
		ds.setDraw(input.getDraw());
		ds.setRecordsFiltered(page.getTotal());
		ds.setRecordsTotal(page.getTotal());

		return  ds;
	}
	/*
	@GetMapping("/findAll")
	@ResponseBody
	public DataTablesOutput<Region> findAll(@Valid DataTablesInput input) {
		DataTablesOutput<Region> ds = this.sdpRegionDao.findAll(input);
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
		Optional<Region> optionalRegion = this.sdpRegionDao.findById(id);
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

*/
}
