package tw.elliot.redis.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyConfigServiceTest {

	@Autowired
	private MyConfigService service;

	@Test
	public void testCacheData() {
		String key = "akey";
		String value = service.findCacheData(key);
		Assert.assertEquals("123", value);

		value = service.findCacheData(key);
		Assert.assertEquals("123", value);

		value = service.findCacheData(key);
		Assert.assertEquals("123", value);
	}

	@Test
	public void testCachePutAndEvict() {
		String key = "akey";
		String oldValue = "123";
		String newValue = "456";

		String value = service.findCacheData(key);
		Assert.assertEquals(oldValue, value);

		service.updateCacheData(key, newValue);
		value = service.findCacheData(key);
		Assert.assertEquals(newValue, value);


		service.cleanCacheData(key);
		value = service.findCacheData(key);
		Assert.assertEquals(oldValue, value);

	}
}