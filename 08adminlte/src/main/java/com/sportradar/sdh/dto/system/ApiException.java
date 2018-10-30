package com.sportradar.sdh.dto.system;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class ApiException {
	private HttpStatus status;
	private String message;
	private List<String> errors;
}
