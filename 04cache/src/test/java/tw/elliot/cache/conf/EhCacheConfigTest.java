package tw.elliot.cache.conf;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EhCacheConfigTest {


	/*
	@Autowired
	private EhCacheCacheManager ehcacheManager;
	*/
	@Test
	public void test() {
		//Assert.assertNotNull(this.ehcacheManager);
	}
}