package tw.elliot.cache.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class CacheService {
	public static final String CACHED_VALUE = "123";


	@Cacheable(value = "service1", key = "#key")
	public String findData(String key) {
		log.warn("LOST KEY [{}]", key);
		return CACHED_VALUE;
	}
}
