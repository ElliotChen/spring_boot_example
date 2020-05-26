package tw.elliot.apm.ctrl;

import com.google.common.util.concurrent.AtomicDouble;
import io.micrometer.core.instrument.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/ctrl")
public class DemoController {

	@Autowired
	@Qualifier("orderCounter")
	private Counter orderCounter;

	@Autowired
	private MeterRegistry meterRegistry;

	AtomicInteger n = new AtomicInteger(0);
	AtomicDouble c = new AtomicDouble(0);

	private Counter etsCounter;
	private Counter irsCounter;

	private FunctionCounter regionCounter;

	private Gauge gauge;

	private TimeGauge timeGauge;

	private DistributionSummary distributionSummary;

	@PostConstruct
	public void init() {
		etsCounter = Counter.builder("app.order.create")
				.tag("service", "ETS")
				.register(meterRegistry);

		irsCounter = Counter.builder("app.order.create")
				.tag("service", "IRS")
				.register(meterRegistry);

		regionCounter = FunctionCounter.builder("app.func.counter", n, (AtomicInteger t) -> { return t.get(); })
				.tag("region", "taipei")
				.register(meterRegistry);

		gauge = Gauge.builder("app.order.gauge", c, AtomicDouble::get)
				.tag("app", "all")
				.register(meterRegistry);

		timeGauge = TimeGauge.builder("app.order.time", c, TimeUnit.MINUTES, AtomicDouble::get)
				.tag("app", "all")
				.register(meterRegistry);


		distributionSummary = DistributionSummary.builder("app.order.dbs")
				.tag("service", "all")
				.register(meterRegistry);
	}

	@GetMapping("/hello")
	public String hello() {
		return "Hi";
	}


	@GetMapping("/etsCreateOrder")
	public String etsCreateOrder() {
		etsCounter.increment(1);

		c.addAndGet(1);

		distributionSummary.record(1);

		distributionSummary.count();
		return "ETS Sucess!";
	}

	@GetMapping("/irsCreateOrder")
	public String irsCreateOrder() {
		irsCounter.increment(2);
		distributionSummary.record(2);

		distributionSummary.count();
		c.addAndGet(2);
		return "IRS Sucess!";
	}

	@GetMapping("/regionCounter")
	public void regionCounter() {

		n.incrementAndGet();
	}

	@GetMapping("/releaseGauge")
	public String releaseGauge() {
		c.set(0);
		return "released";
	}

	@GetMapping("/timer01")
	public void funcTimer() {
		Timer timer = Timer.builder("app.create")
				.tag("region", "Taipei")
				.register(meterRegistry);

		timer.record(() -> {
			System.out.println("--------------------");
		});

	}
}
