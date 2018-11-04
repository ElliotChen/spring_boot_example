package com.sportradar.sdh.ctrl;

import com.sportradar.sdh.domain.sdp.Region;
import com.sportradar.sdh.dto.sdp.RegionDto;
import com.sportradar.sdh.service.BrRegionService;
import com.sportradar.sdh.service.DgtRegionService;
import com.sportradar.sdh.service.SdpRegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
