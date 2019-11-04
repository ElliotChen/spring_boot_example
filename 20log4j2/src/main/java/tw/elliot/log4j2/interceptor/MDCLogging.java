package tw.elliot.log4j2.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Component
@Slf4j
public class MDCLogging extends HandlerInterceptorAdapter {

	public static final String MDC_USER_ID = "MDC_USER_ID";
	public static final String MDC_SESSION_ID = "MDC_SESSION_ID";
	public static final String MDC_REQUEST_ID = "MDC_REQUEST_ID";

	private static ThreadLocal<Boolean> MDC_ADDED = new ThreadLocal<>();


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//MDC_ADDED.set(Boolean.TRUE);

		if (null == MDC_ADDED.get() || !MDC_ADDED.get()) {

			/*Session*/
			HttpSession session = request.getSession();
			if (null != session) {
				MDC.put(MDC_SESSION_ID, session.getId());
			}

			/*User*/
			MDC.put(MDC_USER_ID, "DemoUser_1234");

			/*Request ID*/
			MDC.put(MDC_REQUEST_ID, UUID.randomUUID().toString().toUpperCase().replaceAll("-", ""));

			MDC_ADDED.set(Boolean.TRUE);
		} else {
			log.debug("MDC_ADDED is TRUE, Skip MDC preHandle step.");
		}

		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		MDC.clear();
		MDC_ADDED.set(Boolean.FALSE);
		super.afterCompletion(request, response, handler, ex);
	}
}
