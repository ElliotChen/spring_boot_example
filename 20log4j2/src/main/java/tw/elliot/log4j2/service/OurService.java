package tw.elliot.log4j2.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tw.elliot.log4j2.annotation.ServiceLogger;

@Service
@Slf4j
public class OurService {

	@ServiceLogger
	public void doSomething() {
		log.info("do do do !");
	}
}
