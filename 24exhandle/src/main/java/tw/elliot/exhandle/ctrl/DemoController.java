package tw.elliot.exhandle.ctrl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

	@GetMapping("/ctrlError")
	public String ctrlError() {
		throw new NullPointerException("Test Ctrl NPE");
		//return "CTRLError";
	}


}
