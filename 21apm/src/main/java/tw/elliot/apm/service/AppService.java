package tw.elliot.apm.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author elliot
 */
@Service
public class AppService implements InitializingBean {

	@Autowired
	private MeterRegistry meterRegistry;

	private AtomicInteger onlineUser = new AtomicInteger(0);
	//private AtomicInteger irsOrderCounter = new AtomicInteger(0);
	//private AtomicInteger etsOrderCounter = new AtomicInteger(0);

	private Gauge onlineUserGauge;

	private Counter irsOrderCounter;
	private Counter etsOrderCounter;

	private Random random = new Random();
	@Override
	public void afterPropertiesSet() throws Exception {
		//(onlineUser)->{return onlineUser.intValue();}
		onlineUserGauge = Gauge.builder("app.online.user", onlineUser, AtomicInteger::intValue).register(meterRegistry);

		irsOrderCounter = Counter.builder("app.order")
				.tag("service","irs").description("Order Count with Service[IRS]")
				.register(meterRegistry);

		etsOrderCounter = Counter.builder("app.order")
				.tag("service","ets").description("Order Count with Service[ETS]")
				.register(meterRegistry);
	}

	public void login() {
		onlineUser.incrementAndGet();
	}

	public void logout() {
		onlineUser.decrementAndGet();
	}

	public void irsOrder() {
		irsOrderCounter.increment(random.nextInt(5));
	}

	public void etsOrder() {
		etsOrderCounter.increment(random.nextInt(5));
	}
}
