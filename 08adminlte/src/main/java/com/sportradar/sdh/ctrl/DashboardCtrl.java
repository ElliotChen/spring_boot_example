package com.sportradar.sdh.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardCtrl {

	@RequestMapping("/")
	public String index() {
		return "dashboard/index";
	}

}
