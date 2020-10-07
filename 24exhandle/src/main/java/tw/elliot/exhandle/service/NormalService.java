package tw.elliot.exhandle.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NormalService {

	public void FirstService() {
		log.info("do something");
	}

	public void NpeService() {
		throw new NullPointerException("Test NPE");
	}
}
