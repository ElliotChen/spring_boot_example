package tw.elliot.apm.conf;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order(1)
public class MetrixConf {

	public void test() {

	}

	@Bean
	public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
		/*
		new MeterRegistryCustomizer<MeterRegistry>() {
			public void customize(MeterRegistry registry) {
				registry.config().commonTags("application", "springapm");
			}
		};
		 */
		/*
		return (registry) -> {
			registry.config().commonTags("application", "springapm");
		};
		 */
		return registry -> registry.config().commonTags("application", "springapm");
	}

	@Bean
	public HttpTraceRepository httpTraceRepository() {
		InMemoryHttpTraceRepository repository = new InMemoryHttpTraceRepository();

		return repository;
	}
}
