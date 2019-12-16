package tw.elliot.apm.ctrl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ctrl")
public class DemoController {

	@GetMapping("/hello")
	public String hello() {
		return "Hi";
	}
}
