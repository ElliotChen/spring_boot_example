package tw.elliot.redis.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;

@Configuration
public class IntegrationConf {
	@Bean
	public RedisLockRegistry redisLockRegistry(RedisConnectionFactory redisConnectionFactory) {
		return new RedisLockRegistry(redisConnectionFactory, "eckey");
	}
}
