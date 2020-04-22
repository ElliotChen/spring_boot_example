package tw.elliot.redis.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class ServiceCtrl {

	private static final String DEMO_KEY = "demo";

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@GetMapping(path = "/demo")
	public String demoOperation() {
		ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
		operations.append(DEMO_KEY, "for redis master-slave demo");
		String value = operations.get(DEMO_KEY);
		return value;
	}

}
