package tw.elliot.cache.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CacheServiceTest {

	public static final String KEY = "cachekey";
	@Autowired
	private CacheService service;

	@Test
	public void testFindData() {

		String value = service.findData(KEY);

		Assertions.assertEquals(CacheService.CACHED_VALUE, value);

		value = service.findData(KEY);

		Assertions.assertEquals(CacheService.CACHED_VALUE, value);
	}
}