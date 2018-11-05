package com.sportradar.sdh.ctrl;

import com.sportradar.sdh.domain.sdp.Region;
import com.sportradar.sdh.dto.sdp.RegionDto;
import com.sportradar.sdh.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/ajax/*")
public class AjaxContentCtrl {

	@Autowired
	private SdpRegionService sdpRegionService;

	@Autowired
	private DgtRegionService dgtRegionService;

	@Autowired
	private BrRegionService brRegionService;

	@Autowired
	private DgtSportEventPartService dgtSportEventPartService;

	@Autowired
	private DgtPeriodService dgtPeriodService;

	@Autowired
	private BrMarketService brMarketService;
	@GetMapping("/findSdpRegionsBySport/{sportId}")
	@ResponseBody
	public List<RegionDto> findSdpRegionsBySport(@PathVariable Long sportId) {
		return this.sdpRegionService.findBySportId(sportId);
	}

	@GetMapping("/findDgtRegionsBySport/{sportId}")
	@ResponseBody
	public List<com.sportradar.sdh.domain.dgt.Region> findDgtRegionsBySport(@PathVariable Long sportId) {
		return this.dgtRegionService.findBySportId(sportId);
	}

	@GetMapping("/findBrRegionsBySport/{sportId}")
	@ResponseBody
	public List<com.sportradar.sdh.domain.br.Region> findBrRegionsBySport(@PathVariable Long sportId) {
		return this.brRegionService.findBySportId(sportId);
	}


	@PostMapping("/findDgtEventPartForSportMarket")
	@ResponseBody
	public List<com.sportradar.sdh.domain.dgt.SportEventPart> findDgtEventPartForSportMarket(com.sportradar.sdh.domain.dgt.SportMarket sportMarket) {
		return this.dgtSportEventPartService.findAllForSportMarket(sportMarket.getSportId());
	}

	@PostMapping("/findDgtPeriodForSportMarket")
	@ResponseBody
	public List<com.sportradar.sdh.domain.dgt.Period> findDgtPeriodForSportMarket(com.sportradar.sdh.domain.dgt.SportMarket sportMarket) {
		return this.dgtPeriodService.findAllForSportMarket(sportMarket.getSportId(), sportMarket.getEventPartId());
	}

	@PostMapping("/findBrMarketByMarketTypeId")
	@ResponseBody
	public List<com.sportradar.sdh.domain.br.Market> findBrMarketByMarketTypeId(com.sportradar.sdh.domain.br.Market market) {
		return brMarketService.findByMarketTypeId(market.getMarketTypeId());
	}
}
