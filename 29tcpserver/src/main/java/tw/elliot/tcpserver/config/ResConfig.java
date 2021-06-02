package tw.elliot.tcpserver.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import tw.elliot.tcpserver.handler.EntryHandler;
import tw.elliot.tcpserver.handler.impl.DefaultEntryHandler;
import tw.elliot.tcpserver.handler.impl.LoginEntryHandler;
import tw.elliot.tcpserver.handler.impl.DummyEntryHandler;
import tw.elliot.tcpserver.support.SessionKeeper;

@Slf4j
@Configuration
@Order(1)
public class ResConfig {

	@Bean
	@Order(value=1)
	public EntryHandler loginEntryHandler() {
		return new LoginEntryHandler();
	}

	@Bean
	@Order(value=2)
	public EntryHandler dummyEntryHandler() {
		return new DummyEntryHandler();
	}

	@Bean
	@Order(value=3)
	public EntryHandler defaultEntryHandler() {
		return new DefaultEntryHandler();
	}

	@Bean
	public SessionKeeper defaultSessionKeeper() {
		return new SessionKeeper();
	}
}
