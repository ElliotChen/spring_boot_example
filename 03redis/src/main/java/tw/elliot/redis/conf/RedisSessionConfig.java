package tw.elliot.redis.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig extends AbstractHttpSessionApplicationInitializer {

	@Autowired
	private RedisConnectionFactory redisConnectionFactory;

	/*
	@Bean
	public StringRedisTemplate buildStringRedisTemplate() {
		StringRedisTemplate srt = new StringRedisTemplate();
		srt.setConnectionFactory(this.redisConnectionFactory);
		srt.afterPropertiesSet();
		return srt;
	}
	 */
}
