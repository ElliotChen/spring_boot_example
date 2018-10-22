package tw.elliot.cache.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheServiceTest {

	public static final String KEY = "cachekey";
	@Autowired
	private CacheService service;

	@Test
	public void testFindData() {

		String value = service.findData(KEY);

		Assert.assertEquals(CacheService.CACHED_VALUE, value);

		value = service.findData(KEY);

		Assert.assertEquals(CacheService.CACHED_VALUE, value);
	}
}