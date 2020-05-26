package tw.elliot.apm.probe;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.binder.MeterBinder;

public class SimpleProbe implements MeterBinder {
	private static final double UP = 1.0;
	private static final double DOWN = 0.0;

	private String name;
	private String description;
	private Iterable<Tag> tags;
	@Override
	public void bindTo(MeterRegistry meterRegistry) {
		//Gauge.builder(name, this, value -> value.? UP : DOWN).description(description).tags(tags).baseUnit("status").register(meterRegistry);
	}
}
