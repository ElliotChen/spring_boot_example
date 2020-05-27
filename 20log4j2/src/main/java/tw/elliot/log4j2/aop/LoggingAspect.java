package tw.elliot.log4j2.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import tw.elliot.log4j2.interceptor.MDCLogging;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
	/*@Around("@annotation(tw.elliot.log4j2.annotation.ServiceLogger)")*/
	@Around("tw.elliot.log4j2.aop.ServicePointCut.servicePoint()")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		Signature signature = joinPoint.getSignature();
		String method = signature.getName();

		//Use stop watch to trace used time.
		StopWatch stopWatch = new StopWatch();
		log.info("Before Service [{}]-- ", method);

		stopWatch.start();

		Object proceed = joinPoint.proceed();

		stopWatch.stop();

		log.info("-- After Service. Used time [{}] ms.", stopWatch.getTotalTimeMillis());

		return proceed;
	}
}
