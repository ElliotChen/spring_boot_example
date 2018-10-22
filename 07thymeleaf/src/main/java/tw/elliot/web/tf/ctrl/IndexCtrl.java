package tw.elliot.web.tf.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexCtrl {

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("name", "Ec");

		return "index";
	}
}
