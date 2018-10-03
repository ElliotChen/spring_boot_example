package tw.elliot.redis.ctrl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/session/*")
public class SessionCtrl {

	@RequestMapping("/index")
	public String index(HttpSession session) {
		System.out.println(session.getId());
		return "Success";
	}
}
