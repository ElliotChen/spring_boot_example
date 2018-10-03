package tw.elliot.cache.conf;


import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.ehcache.EhCacheManagerUtils;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableCaching
@EnableConfigurationProperties(CacheProperties.class)
public class CacheConfig {

	private CacheProperties cacheProperties;


	private RedisConnectionFactory redisConnectionFactory;

	public CacheConfig(CacheProperties cacheProperties, RedisConnectionFactory redisConnectionFactory) {
		this.cacheProperties = cacheProperties;
		this.redisConnectionFactory = redisConnectionFactory;
	}
	/*
	public EhCacheCacheManager ehCacheCacheManager(EhCacheCacheManager ehCacheCacheManager) {
		return ehCacheCacheManager;
	}
	*/

	/*
	public RedisCacheManager createRedisCacheManager(RedisConnectionFactory rcf) {
		RedisCacheManager.RedisCacheManagerBuilder rcb = RedisCacheManager.builder(rcf);
		return rcb.build();
	}
	*/


	private EhCacheCacheManager buildEhCAcheCacheManager() {
		Resource resource = this.cacheProperties.getEhcache().getConfig();
		Resource location = this.cacheProperties.resolveConfigLocation(resource);
		return new EhCacheCacheManager(EhCacheManagerUtils.buildCacheManager(location));
	}

	private RedisCacheManager buildRedisCacheManager() {
		RedisCacheManager.RedisCacheManagerBuilder rcb = RedisCacheManager.builder(redisConnectionFactory);
		return rcb.build();
	}

	@Bean
	public CacheManager buildCompositeCacheManager() {
		EhCacheCacheManager ehCacheCacheManager = this.buildEhCAcheCacheManager();
		RedisCacheManager redisCacheManager = this.buildRedisCacheManager();

		CompositeCacheManager cm = new CompositeCacheManager();

		List<CacheManager> cms = new ArrayList<>();
		cms.add(ehCacheCacheManager);
		cms.add(redisCacheManager);

		cm.setCacheManagers(cms);
		return cm;
	}
}
