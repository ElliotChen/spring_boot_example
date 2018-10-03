package tw.elliot.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyConfigService {

	@Cacheable(value = "myservicedata", key = "#key")
	public String findCacheData(String key) {
		log.warn("Cache not found for key[{}]", key);
		return "123";
	}

	@CachePut(value = "myservicedata", key = "#key")
	public String updateCacheData(String key, String value) {
		return value;
	}


	@CacheEvict(value = "myservicedata", key = "#key")
	public void cleanCacheData(String key) {
		//
	}
}
