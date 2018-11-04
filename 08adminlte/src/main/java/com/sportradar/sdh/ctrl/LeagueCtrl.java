package com.sportradar.sdh.ctrl;

import com.sportradar.sdh.domain.sdp.League;
import com.sportradar.sdh.dto.dts.DataTablesInput;
import com.sportradar.sdh.dto.dts.DataTablesOutput;
import com.sportradar.sdh.dto.sdp.LeagueDto;
import com.sportradar.sdh.dto.sdp.RegionDto;
import com.sportradar.sdh.dto.system.ApiResult;
import com.sportradar.sdh.service.SdpLeagueGroupService;
import com.sportradar.sdh.service.SdpLeagueService;
import com.sportradar.sdh.service.SdpRegionService;
import com.sportradar.sdh.service.SdpSportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/league")
public class LeagueCtrl {
	private static final String prefix = "league";

	@Autowired
	private SdpLeagueService sdpLeagueService;

	@Autowired
	private SdpRegionService sdpRegionService;

	@Autowired
	private SdpSportService sdpSportService;

	@Autowired
	private SdpLeagueGroupService sdpLeagueGroupService;

	@GetMapping("/findByPage")
	@ResponseBody
	public DataTablesOutput<LeagueDto> findByPage(@Valid DataTablesInput input) {

		return this.sdpLeagueService.findByPage(input);
	}


	/*********************************/
	/** Pair 匹配                   **/
	/*********************************/
	@GetMapping("/pair")
	public String index() {
		return prefix+"/pairIndex";
	}

	@GetMapping("/pair/{id}")
	public String pair(@PathVariable Long id, Model model) {
		League league = this.sdpLeagueService.findById(id);
		model.addAttribute("league", league);

		model.addAttribute("dgtLeagues", this.sdpLeagueService.findAllDgtLeagues());
		model.addAttribute("brLeagues", this.sdpLeagueService.findAllBrLeagues());

		return prefix+"/pair";
	}

	@PostMapping("/pair/save")
	public String savePair(LeagueDto league, Model model) {
		log.info("Find save target : Sport [{}] - DGT[{}],BR[{}]",league.getLeagueId(),
				league.getDgtLeague().getLeagueId(), league.getBrLeague().getLeagueId());

		this.sdpLeagueService.savePair(league);
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
	public String i18n(@PathVariable Long id, Model model) {

		LeagueDto league = this.sdpLeagueService.findById(id);
		List<LeagueDto> leagues = this.sdpLeagueService.findByIdWithAllLanguage(id);

		model.addAttribute("league", league);
		model.addAttribute("leagues", leagues);
		return prefix+"/i18n";
	}

	@PostMapping("/i18n/save")
	@ResponseBody
	public ApiResult saveI18n(LeagueDto league, Model model) {
		log.info("Find League [{}] - [{}]",league.getLeagueId(), league.getLanguage().getLanguageCode());
		this.sdpLeagueService.saveI18N(league);
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
	public String data(@PathVariable Long id, Model model) {
		League league = this.sdpLeagueService.findById(id);

		if (null == league) {
			league = new League();
		}

		model.addAttribute("league", league);

		/* 因RegionSport是由 Sport 与 Region连动，所以第一次时仅找出
		 * 全部的Sport，单一的Region，之后再根据选取的Sport找出可用的Region.
		 * */

		List<RegionDto> regions = new ArrayList<>();
		if (null != league.getRegionNum()) {
			RegionDto region = this.sdpRegionService.findById(league.getRegionNum());
			if (null != region) {
				regions.add(region);
			}
		}

		model.addAttribute("leagueGroups", this.sdpLeagueGroupService.findAll());
		model.addAttribute("sports", this.sdpSportService.findAll());
		model.addAttribute("regions", regions);

		return prefix+"/data";
	}

	@PostMapping("/data/save")
	public String saveData(LeagueDto league,Model model) {
		this.sdpLeagueService.saveData(league);
		model.addAttribute("successFlash", "Success!");
		return prefix+"/dataIndex";
	}
}
