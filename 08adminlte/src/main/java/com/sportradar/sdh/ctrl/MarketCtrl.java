package com.sportradar.sdh.ctrl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sportradar.sdh.dao.sdp.SdpEventTypeDao;
import com.sportradar.sdh.dao.sdp.SdpLanguageDao;
import com.sportradar.sdh.domain.dgt.Period;
import com.sportradar.sdh.domain.dgt.SportEventPart;
import com.sportradar.sdh.domain.sdp.Market;
import com.sportradar.sdh.dto.dts.DataTablesInput;
import com.sportradar.sdh.dto.dts.DataTablesOutput;
import com.sportradar.sdh.dto.sdp.MarketDto;
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
@RequestMapping("/market/*")
public class MarketCtrl {
	private static final String prefix = "market";
	@Autowired
	private SdpMarketService sdpMarketService;

	@Autowired
	private DgtSportService dgtSportService;

	@Autowired
	private DgtSportEventPartService dgtSportEventPartService;

	@Autowired
	private DgtPeriodService dgtPeriodService;

	@Autowired
	private DgtMarketService dgtMarketService;

	@Autowired
	private SdpEventTypeDao sdpEventTypeDao;

	@Autowired
	private BrEventTypeService brEventTypeService;

	@Autowired
	private BrMarketService brMarketService;

	@Autowired
	private SdpSportService sdpSportService;

	private SdpLanguageDao sdhLanguageDao;

	@GetMapping("/findByPage")
	@ResponseBody
	public DataTablesOutput<MarketDto> findByPage(@Valid DataTablesInput input) {
		return this.sdpMarketService.findByPage(input);
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
		MarketDto market = this.sdpMarketService.findById(id);
		model.addAttribute("market", market);

		model.addAttribute("dgtSports", dgtSportService.findAllForSportMarket());
		model.addAttribute("dgtSportEventParts", dgtSportEventPartService.findAllForSportMarket(market.getDgtSportMarket().getSportId()));
		model.addAttribute("dgtPeriods", dgtPeriodService.findAllForSportMarket(market.getDgtSportMarket().getSportId(),
				market.getDgtSportMarket().getEventPartId()));
		model.addAttribute("dgtMarkets", this.dgtMarketService.findAll());

		log.debug("markettypeid[{}]", market.getBrMarket().getMarketTypeId());
		model.addAttribute("brEventTypes", brEventTypeService.findAllForMarket());
		model.addAttribute("brMarkets", brMarketService.findByMarketTypeId(market.getBrMarket().getMarketTypeId()));
		return prefix+"/pair";
	}

	@PostMapping("/pair/save")
	public String savePair(MarketDto market, Model model) {

		this.sdpMarketService.savePair(market);
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

		Market market = this.sdpMarketService.findById(id);
		List<MarketDto> markets = this.sdpMarketService.findByIdWithAllLanguage(id);

		model.addAttribute("market", market);
		model.addAttribute("markets", markets);
		return prefix+"/i18n";
	}

	@PostMapping("/i18n/save")
	@ResponseBody
	public ApiResult saveI18n(MarketDto market, Model model) {
		log.error("Find Market [{}] - [{}]",market.getMarketId(), market.getLanguage().getLanguageCode());
		this.sdpMarketService.saveI18N(market);
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
		Market market = this.sdpMarketService.findById(id);

		if (null == market) {
			market = new Market();
		}

		model.addAttribute("market", market);

		model.addAttribute("eventTypes", this.sdpEventTypeDao.findAll());
		model.addAttribute("sdpSports", this.sdpSportService.findAll());
		return prefix+"/data";

	}

	@PostMapping("/data/save")
	public String saveData(MarketDto market, Model model) {

		this.sdpMarketService.saveData(market);

		model.addAttribute("successFlash", "Success!");
		return prefix+"/dataIndex";
	}

}
