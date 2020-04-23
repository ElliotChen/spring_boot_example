package tw.elliot.redis.ctrl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@Slf4j
@RestController
@RequestMapping("/service")
public class ServiceCtrl {

	private static final String DEMO_KEY = "demo";

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisLockRegistry redisLockRegistry;

	@GetMapping(path = "/demo")
	public String demoOperation() {
		ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
		operations.append(DEMO_KEY, "for redis master-slave demo");
		String value = operations.get(DEMO_KEY);
		return value;
	}

	@GetMapping(path = "/lock")
	public String lockAndDo() {
		/*
		1. Obtain
		2. TryLock
		3. Unlock
		 */
		String lockKey = "SKU_2020_NIKE_AJ_M_BlUE_9";
		log.info("Try to get Lock with key[{}]", lockKey);
		Lock lock = redisLockRegistry.obtain(lockKey);

		try {
			boolean lockStatus = lock.tryLock(10, TimeUnit.SECONDS);
			log.info("Lock with key[{}] is [{}]", lockKey, lockStatus);

			if (lockStatus) {
				try {
					/*
					Do our process here
			 		*/
					// pretend to be busy.
					TimeUnit.SECONDS.sleep(5);
				} finally {
					log.info("Unlock key[{}] success!", lockKey);
					lock.unlock();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Finish";
	}
}
