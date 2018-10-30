package com.sportradar.sdh.dto.system;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiResult {
	private HttpStatus status;
	private String message;
}
