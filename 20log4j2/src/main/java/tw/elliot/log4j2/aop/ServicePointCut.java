package tw.elliot.log4j2.aop;

import org.aspectj.lang.annotation.Pointcut;

public class ServicePointCut {
	@Pointcut("within(tw.elliot.log4j2.service.*)")
	public void servicePoint() {

	}
}
