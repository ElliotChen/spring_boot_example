package com.sportradar.sdh.ctrl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sportradar.sdh.dao.sdp.SdpLanguageDao;
import com.sportradar.sdh.dao.sdp.SdpRegionDao;
import com.sportradar.sdh.domain.sdp.*;
import com.sportradar.sdh.dto.dts.DataTablesOutput;
import com.sportradar.sdh.dto.sdp.RegionDto;
import com.sportradar.sdh.dto.sdp.SportDto;
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
@RequestMapping("/region/*")
public class RegionCtrl {
	private static final String prefix = "region";
	@Autowired
	private SdpRegionDao sdpRegionDao;

	private SdpLanguageDao sdhLanguageDao;

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
	public DataTablesOutput<RegionDto> findAll(@Valid com.sportradar.sdh.dto.dts.DataTablesInput input) {

		PageHelper.startPage((input.getStart() / input.getLength()) +1 , input.getLength());

		Page<Region> page = this.sdpRegionDao.findByPage();
		DataTablesOutput<RegionDto> ds = new DataTablesOutput<RegionDto>();
		ds.setData(coverDto(page.getResult()));
		ds.setDraw(input.getDraw());
		ds.setRecordsFiltered(page.getTotal());
		ds.setRecordsTotal(page.getTotal());

		return  ds;
	}

	@GetMapping("/i18n/{id}")
	public String i18n(@PathVariable Integer id, Model model) {

		Region region = this.sdpRegionDao.findById(id);
		List<Region> regions = this.sdpRegionDao.findByIdWithAllLanguage(id);


		model.addAttribute("region", region);
		model.addAttribute("regions", regions);

		return prefix+"/i18n";
	}

	@PostMapping("/saveI18n")
	@ResponseBody
	public ApiResult saveI18n(RegionDto region, Model model) {
		log.error("Find Region [{}] - [{}]",region.getRegionNum(), region.getLanguage().getLanguageCode());
		this.saveDbI18N(region);
		model.addAttribute("successFlash", "Success!");

		ApiResult apiResult = new ApiResult();
		apiResult.setStatus(HttpStatus.OK);
		apiResult.setMessage("Save Success!");
		return apiResult;
	}

	private List<RegionDto> coverDto(List<Region> regions) {
		List<RegionDto> result = new ArrayList<>();
		for (Region region : regions) {
			RegionDto sd = new RegionDto();

			BeanUtils.copyProperties(region, sd);

			for (Region translatedRegion :this.sdpRegionDao.findByIdWithLanguage(sd.getRegionNum())) {
				Translation translation = new Translation();

				translation.setLanguageCode(translatedRegion.getLanguage().getLanguageCode());
				translation.setLanguageName(translatedRegion.getLanguage().getLanguageName());
				translation.setTranslationValue(translatedRegion.getRegionFullName());

				sd.getTranslations().add(translation);
			}
			result.add(sd);
		}
		return result;
	}

	private void saveDbI18N(RegionDto region) {
		region.setUpdatedTime(new Date());
		Region language = this.sdpRegionDao.findByIdAndLanguageCodeWithLanguage(region.getRegionNum(), region.getLanguage().getLanguageCode());
		if (language == null) {
			this.sdpRegionDao.insertI18N(region);
		} else {
			this.sdpRegionDao.updateI18N(region);
		}
	}



	/*




@GetMapping("/findAll")
	@ResponseBody
	public DataTablesOutput<Region> findAll(@Valid DataTablesInput input) {
		DataTablesOutput<Region> ds = this.sdpRegionDao.findAll(input);
		return  ds;
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



	@PostMapping("/saveData")
	public String saveData(Model model) {
		model.addAttribute("successFlash", "Success!");
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
