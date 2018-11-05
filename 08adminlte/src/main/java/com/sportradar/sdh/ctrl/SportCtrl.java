package com.sportradar.sdh.ctrl;

import com.sportradar.sdh.dto.dts.DataTablesInput;
import com.sportradar.sdh.dto.dts.DataTablesOutput;
import com.sportradar.sdh.dto.sdp.SportDto;
import com.sportradar.sdh.dto.system.ApiResult;
import com.sportradar.sdh.service.BrSportService;
import com.sportradar.sdh.service.DgtSportService;
import com.sportradar.sdh.service.SdpSportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/sport")
public class SportCtrl {

	private static final String prefix = "sport";

	@Autowired
	private SdpSportService sdpSportService;

	@Autowired
	private DgtSportService dgtSportService;

	@Autowired
	private BrSportService brSportService;

	@GetMapping("/findByPage")
	@ResponseBody
	public DataTablesOutput<SportDto> findByPage(@Valid DataTablesInput input) {
		return this.sdpSportService.findByPage(input);
	}

	/*********************************/
	/** Pair 匹配                   **/
	/*********************************/
	@GetMapping("/pair")
	public String index() {
		return "sport/pairIndex";
	}

	@GetMapping("/pair/{id}")
	public String pair(@PathVariable Long id, Model model) {
		SportDto sport = this.sdpSportService.findById(id);
		model.addAttribute("sport", sport);
		model.addAttribute("dgtSports", this.dgtSportService.findAll());
		model.addAttribute("brSports", this.brSportService.findAll());
		return prefix+"/pair";
	}

	@PostMapping("/pair/save")
	public String savePair(SportDto sport, Model model) {
		log.info("Find save target : Sport [{}] - DGT[{}],BR[{}]",sport.getSportId(),
				sport.getDgtSport().getSportId(), sport.getBrSport().getSportId());

		this.sdpSportService.savePair(sport);
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

		SportDto sport = this.sdpSportService.findById(id);
		List<SportDto> sports = this.sdpSportService.findByIdWithAllLanguage(id);

		model.addAttribute("sport", sport);
		model.addAttribute("sports", sports);
		return prefix+"/i18n";
	}

	@PostMapping("/i18n/save")
	@ResponseBody
	public ApiResult saveI18n(SportDto sport,Model model) {
		log.info("Find Sport [{}] - [{}]",sport.getSportId(), sport.getLanguage().getLanguageCode());
		this.sdpSportService.saveI18N(sport);
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

		SportDto sport = this.sdpSportService.findById(id);
		if (null == sport) {
			sport = new SportDto();
		}

		model.addAttribute("sport", sport);

		return prefix+"/data";
	}

	@PostMapping("/data/save")
	public String saveData(SportDto sport, Model model) {
		log.info("Find save target : Sport [{}] - Name[{}],Priority[{}]",sport.getSportId(), sport.getSportName(), sport.getPriority());

		this.sdpSportService.saveData(sport);
		model.addAttribute("successFlash", "Success!");
		return prefix+"/dataIndex";
	}

}
