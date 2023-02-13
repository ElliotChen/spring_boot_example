package tw.elliot.db.ctrl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.elliot.db.model.User;
import tw.elliot.db.service.DemoService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserCtrl {

	private final DemoService demoService;

	@GetMapping(path = "/{id}", produces = "application/json")
	public User findById(@PathVariable Long id) {
		return demoService.findUser(id).orElse(new User());
	}
}
