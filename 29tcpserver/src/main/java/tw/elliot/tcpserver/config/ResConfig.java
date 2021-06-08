package tw.elliot.tcpserver.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import tw.elliot.tcpserver.handler.EntryHandler;
import tw.elliot.tcpserver.handler.impl.*;
import tw.elliot.tcpserver.support.SessionKeeper;

@Slf4j
@Configuration
@Order(1)
public class ResConfig {

	@Autowired
	private ResConfigData resConfigData;

	@Bean
	@Order(value=1)
	public EntryHandler ignoreEntryHandler() {
		return new IgnoreEntryHandler();
	}

	@Bean
	@Order(value=2)
	public EntryHandler bsoEntryHandler() {
		return new BSOEntryHandler();
	}

	@Bean
	@Order(value=3)
	public EntryHandler loginEntryHandler() {
		return new LoginEntryHandler();
	}

	@Bean
	@Order(value=4)
	public EntryHandler defaultEntryHandler() {
		DefaultEntryHandler handler = new DefaultEntryHandler();
		handler.setFileExtension(resConfigData.getFileExtension());
		handler.setCharEncoding(resConfigData.getCharEncoding());
		handler.setEntryResultPath(resConfigData.getEntryResultPath());
		return handler;
	}

	@Bean
	public SessionKeeper defaultSessionKeeper() {
		return new SessionKeeper();
	}
}
