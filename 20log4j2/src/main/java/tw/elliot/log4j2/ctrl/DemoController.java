package tw.elliot.log4j2.ctrl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.elliot.log4j2.service.OurService;

@RestController
@Slf4j
@RequestMapping("/demo")
public class DemoController {

	@Autowired
	private OurService ourService;

	public DemoController() {
		super();
		log.info("create controller instance");
	}

	@GetMapping("/first")
	public String first() {
		log.info("Check this !");

		//Test ServiceLogger
		ourService.doSomething();

		return "It's ok!";
	}

	@GetMapping("/exception")
	public String exception() {
		log.error("Create Exception Log");
		throw new RuntimeException("I don't care");
	}

	@GetMapping("/level")
	public String level() {
		log.debug("a debug test");
		log.warn("Fake warning message!");
		return "test level success!";
	}

	@GetMapping("/version")
	public String version() {
		Package pack = DemoController.class.getPackage();
		log.info("pack is {}", pack);
		log.info("package imp: {}", pack.getImplementationVersion());

		return pack.getImplementationVersion();
	}
}
