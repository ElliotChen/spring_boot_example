package com.sportradar.sdh.ctrl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sportradar.sdh.dao.sdp.SdpLanguageDao;
import com.sportradar.sdh.dao.sdp.SdpRegionDao;
import com.sportradar.sdh.domain.dgt.Region;
import com.sportradar.sdh.domain.sdp.*;
import com.sportradar.sdh.dto.dts.DataTablesInput;
import com.sportradar.sdh.dto.dts.DataTablesOutput;
import com.sportradar.sdh.dto.sdp.RegionDto;
import com.sportradar.sdh.dto.sdp.SportDto;
import com.sportradar.sdh.dto.sdp.Translation;
import com.sportradar.sdh.dto.system.ApiResult;
import com.sportradar.sdh.service.*;
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
	private SdpRegionService sdpRegionService;


	@Autowired
	private DgtSportService dgtSportService;
	@Autowired
	private DgtRegionService dgtRegionService;


	@Autowired
	private BrSportService brSportService;
	@Autowired
	private BrRegionService brRegionService;


	@GetMapping("/findByPage")
	@ResponseBody
	public DataTablesOutput<RegionDto> findAll(@Valid DataTablesInput input) {
		return  this.sdpRegionService.findByPage(input);
	}

	/*********************************/
	/** Pair 匹配                   **/
	/*********************************/

	@GetMapping("/pair")
	public String index() {
		return prefix+"/pairIndex";
	}

	@GetMapping("/pair/{id}")
	public String pair(@PathVariable Integer id, Model model) {
		RegionDto region = this.sdpRegionService.findById(id);

		model.addAttribute("region", region);

		/* RegionSport, Sport 较少，Region 多*/

		List<com.sportradar.sdh.domain.dgt.Region> dgtRegions = new ArrayList<>();
		dgtRegions.add(this.dgtRegionService.findById(region.getDgtRegionSport().getRegionNum()));
		model.addAttribute("dgtSports", this.dgtSportService.findAllForRegion());
		model.addAttribute("dgtRegions", dgtRegions);

		List<com.sportradar.sdh.domain.br.Region> brRegions = new ArrayList<>();
		brRegions.add(this.brRegionService.findById(region.getBrRegionSport().getRegionNum()));
		model.addAttribute("brSports", this.brSportService.findAllForRegion());
		model.addAttribute("brRegions", brRegions);

		return prefix+"/pair";
	}

	@PostMapping("/pair/save")
	public String savePair(RegionDto region, Model model) {
		log.info("Find save target : Region [{}] - DGT[{}],BR[{}]",region.getCompositedId(),
				region.getDgtRegionSport().getCompositedId(), region.getBrRegionSport().getCompositedId());

		this.sdpRegionService.savePair(region);

		model.addAttribute("successFlash", "Success!");
		return prefix+"/pairIndex";
	}

	/*********************************/
	/** i18n 翻译                   **/
	/*********************************/

	@GetMapping("/i18n")
	public String i18nIndex() {
		return prefix+"/i18nIndex";
	}

	@GetMapping("/i18n/{id}")
	public String i18n(@PathVariable Integer id, Model model) {

		RegionDto region = this.sdpRegionService.findById(id);
		List<RegionDto> regions = this.sdpRegionService.findByIdWithAllLanguage(id);


		model.addAttribute("region", region);
		model.addAttribute("regions", regions);

		return prefix+"/i18n";
	}

	@PostMapping("/i18n/save")
	@ResponseBody
	public ApiResult saveI18n(RegionDto region, Model model) {
		log.error("Find Region [{}] - [{}]",region.getRegionNum(), region.getLanguage().getLanguageCode());
		this.sdpRegionService.saveI18N(region);
		model.addAttribute("successFlash", "Success!");

		ApiResult apiResult = new ApiResult();
		apiResult.setStatus(HttpStatus.OK);
		apiResult.setMessage("Save Success!");
		return apiResult;
	}

	/*********************************/
	/** Data 基本资料                **/
	/*********************************/

	@GetMapping("/data")
	public String dataIndex() {
		return prefix+"/dataIndex";
	}

	@GetMapping("/data/{id}")
	public String data(@PathVariable Integer id, Model model) {
		RegionDto region = this.sdpRegionService.findById(id);

		if (null == region) {
			region = new RegionDto();
		}

		model.addAttribute("region", region);
		return prefix+"/data";
	}


	@PostMapping("/data/save")
	public String saveData(RegionDto region, Model model) {

		this.sdpRegionService.saveData(region);

		model.addAttribute("successFlash", "Success!");
		return prefix+"/dataIndex";
	}
}
