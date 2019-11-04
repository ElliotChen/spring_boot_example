package tw.elliot.log4j2.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import tw.elliot.log4j2.interceptor.MDCLogging;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
	@Autowired
	private MDCLogging mdcLogging;
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(mdcLogging);
	}
}
