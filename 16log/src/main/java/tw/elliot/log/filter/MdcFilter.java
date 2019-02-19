package tw.elliot.log.filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Slf4j
public class MdcFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String userid = servletRequest.getParameter("userid");

        log.info("Check userid:[{}]", userid);
        if (!StringUtils.isEmpty(userid)) {
            MDC.put("userid", userid);
        }

        filterChain.doFilter(servletRequest, servletResponse);

        MDC.clear();
    }
}
