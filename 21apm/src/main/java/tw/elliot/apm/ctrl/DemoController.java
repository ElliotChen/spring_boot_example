package tw.elliot.apm.ctrl;

import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.elliot.apm.service.AppService;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/ctrl")
public class DemoController {

	@Autowired
	private AppService appService;

	@PostConstruct
	public void init() {


	}

	@GetMapping("/login")
	public String login() {
		appService.login();

		return "Login Success";
	}

	@GetMapping("/logout")
	public String logout() {
		appService.logout();

		return "Logout Success";
	}


	@GetMapping("/hello")
	public String hello() {
		return "Hi";
	}


	@GetMapping("/etsCreateOrder")
	public String etsCreateOrder() {
		this.appService.etsOrder();
		return "ETS Sucess!";
	}

	@GetMapping("/irsCreateOrder")
	public String irsCreateOrder() {
		this.appService.irsOrder();
		return "IRS Sucess!";
	}

	@GetMapping("/regionCounter")
	public void regionCounter() {


	}

	@GetMapping("/releaseGauge")
	public String releaseGauge() {

		return "released";
	}

	@GetMapping("/timer01")
	public void funcTimer() {

	}

	@Timed(value = "func_timer2", extraTags = {"region","Taiwan"})
	@GetMapping("/timer02")
	public void funTimer2() {

	}
}
