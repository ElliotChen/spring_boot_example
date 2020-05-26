package tw.elliot.apm.conf;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@Order(2)
public class AppMetrixConf {

	@Autowired
	private MeterRegistry meterRegistry;

	@Bean("orderCounter")
	public Counter createOrderCounter() {
		Counter counter = this.meterRegistry.counter("order.count", "Channel", "DateTime");
		return counter;
	}
}
