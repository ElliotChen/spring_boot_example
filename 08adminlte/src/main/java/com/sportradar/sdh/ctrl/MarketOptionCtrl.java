package com.sportradar.sdh.ctrl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sportradar.sdh.dao.sdp.SdpLanguageDao;
import com.sportradar.sdh.dao.sdp.SdpMarketDao;
import com.sportradar.sdh.dao.sdp.SdpMarketOptionDao;
import com.sportradar.sdh.domain.br.Market;
import com.sportradar.sdh.domain.dgt.SportMarket;
import com.sportradar.sdh.domain.sdp.MarketOption;
import com.sportradar.sdh.domain.sdp.Sport;
import com.sportradar.sdh.dto.dts.DataTablesInput;
import com.sportradar.sdh.dto.dts.DataTablesOutput;
import com.sportradar.sdh.dto.sdp.MarketDto;
import com.sportradar.sdh.dto.sdp.MarketOptionDto;
import com.sportradar.sdh.dto.sdp.Translation;
import com.sportradar.sdh.dto.system.ApiResult;
import com.sportradar.sdh.service.BrMarketOptionService;
import com.sportradar.sdh.service.DgtMarketOptionService;
import com.sportradar.sdh.service.SdpMarketOptionService;
import com.sportradar.sdh.service.SdpMarketService;
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
	private SdpMarketOptionService sdpMarketOptionService;

	@Autowired
	private DgtMarketOptionService dgtMarketOptionService;

	@Autowired
	private BrMarketOptionService brMarketOptionService;

	@Autowired
	private SdpMarketService marketService;

	@Autowired
	private SdpLanguageDao sdhLanguageDao;

	@Autowired
	private SdpMarketDao sdhMarketDao;

	@GetMapping("/findByPage")
	@ResponseBody
	public DataTablesOutput<MarketOptionDto> findByPage(@Valid DataTablesInput input) {

		return  this.sdpMarketOptionService.findByPage(input);
	}

	/*********************************/
	/** Pair 匹配                   **/
	/*********************************/

	@GetMapping("/pair")
	public String index() {
		return prefix+"/pairIndex";
	}

	@GetMapping("/pair/{marketId}/{optionNum}")
	public String pair(@PathVariable Long marketId, @PathVariable Integer optionNum, Model model) {
		MarketOptionDto marketOption = this.sdpMarketOptionService.findById(marketId, optionNum);

		MarketDto marketDto = marketService.findById(marketId);
		SportMarket dgtSportMarket = marketDto.getDgtSportMarket();
		Market brMarket = marketDto.getBrMarket();

		model.addAttribute("marketOption", marketOption);
		model.addAttribute("dgtMarketOptions", this.dgtMarketOptionService.findByMarketId(dgtSportMarket.getMarketId()));

		model.addAttribute("brMarketOptions", this.brMarketOptionService.findByMarket(brMarket));
		return prefix+"/pair";
	}

	@PostMapping("/pair/save")
	public String savePair(MarketOptionDto marketOption, Model model) {
		this.sdpMarketOptionService.savePair(marketOption);
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

	@GetMapping("/i18n/{marketId}/{optionNum}")
	public String i18n(@PathVariable Long marketId, @PathVariable Integer optionNum, Model model) {

		MarketOptionDto marketOption = this.sdpMarketOptionService.findById(marketId, optionNum);
		List<MarketOptionDto> marketOptions = this.sdpMarketOptionService.findByIdWithAllLanguage(marketId, optionNum);

		model.addAttribute("marketOption", marketOption);
		model.addAttribute("marketOptions", marketOptions);
		return prefix+"/i18n";
	}

	@PostMapping("/i18n/save")
	@ResponseBody
	public ApiResult saveI18n(MarketOptionDto marketOptionDto, Model model) {
		this.sdpMarketOptionService.saveI18N(marketOptionDto);

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

	@GetMapping("/data/{marketId}/{optionNum}")
	public String data(@PathVariable Long marketId, @PathVariable Integer optionNum, Model model) {
		MarketOptionDto marketOption = this.sdpMarketOptionService.findById(marketId, optionNum);

		if (null == marketOption) {
			marketOption = new MarketOptionDto();
		}

		model.addAttribute("marketOption", marketOption);
		model.addAttribute("markets", this.sdhMarketDao.findAll());
		return prefix+"/data";
	}

	@PostMapping("/data/save")
	public String saveData(MarketOptionDto marketOption, Model model) {
		this.sdpMarketOptionService.saveData(marketOption);
		model.addAttribute("successFlash", "Success!");
		return prefix+"/dataIndex";
	}

}
