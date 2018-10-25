package com.sportradar.sdp.ctrl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@ControllerAdvice
@Slf4j
public class GeneralCtrlAdvice {

	@ModelAttribute
	public void addGeneralModel(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (null == authentication || null == authentication.getPrincipal()) {
			log.debug("A connection to controller but without authentication.");
			return;
		}

		Object principal = authentication.getPrincipal();

		if (!(principal instanceof User)) {
			log.warn("Security Issue - principal isn't a User instance.");
			return;
		}

		User user = (User) principal;
		model.addAttribute("user", user);

		log.debug("Add Security User[{}] to Model.", user.getUsername());
	}

	@ExceptionHandler
	public void handleControllerException(HttpServletRequest request, HttpServletResponse response, Throwable ex) throws IOException {
		log.error("handleControllerException,url:{}",request.getRequestURI(),ex);

		String ajax = request.getHeader("X-Requested-With");

		if (StringUtils.isEmpty(ajax)) {
			response.sendRedirect("/error");
		} else {
			ObjectMapper om = new ObjectMapper();
			String json = "";
			response.setCharacterEncoding(StandardCharsets.UTF_8.name());
			response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			response.getWriter().write(json);
		}

	}

}
