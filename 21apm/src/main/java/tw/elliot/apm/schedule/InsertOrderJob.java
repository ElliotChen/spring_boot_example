package tw.elliot.apm.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tw.elliot.apm.service.AppService;

@Component
public class InsertOrderJob {
	@Autowired
	private AppService appService;

	@Scheduled(fixedDelay = 1000)
	public void insertIrsOrder() {
		this.appService.irsOrder();
	}

	@Scheduled(fixedDelay = 3000)
	public void insertEtsOrder() {
		this.appService.etsOrder();
	}
}
