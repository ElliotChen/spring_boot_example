package com.sportradar.sdh.ctrl;

import com.sportradar.sdh.dao.sdp.SdpRegionDao;
import com.sportradar.sdh.domain.sdp.Region;
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
	private SdpRegionDao sdpRegionDao;

	@GetMapping("/findRegionsBySport/{sportId}")
	@ResponseBody
	public List<Region> findRegionsBySport(@PathVariable Long sportId) {
		return this.sdpRegionDao.findBySportId(sportId);
	}
}
