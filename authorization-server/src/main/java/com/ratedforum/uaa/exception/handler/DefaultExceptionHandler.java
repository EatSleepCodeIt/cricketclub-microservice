package com.ratedforum.uaa.exception.handler;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.common.collect.Maps;
import com.ratedforum.uaa.exception.UserAuthenticationException;

@ControllerAdvice
public class DefaultExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionHandler.class);
	
	@RequestMapping(produces = { "application/vnd.captech-v1.0+json", "application/vnd.captech-v2.0+json" })
	@ExceptionHandler(UserAuthenticationException.class)
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public @ResponseBody Map<String, Object> handleUnsupportedMediaTypeException(HttpMediaTypeNotSupportedException ex) throws IOException {
		Map<String, Object> map = Maps.newHashMap();
		map.put("error", "Forbidden Error");
		map.put("message", ex.getMessage());
		return map;
	}
	
	@RequestMapping(produces = { "application/vnd.captech-v1.0+json", "application/vnd.captech-v2.0+json" })
	@ExceptionHandler({Exception.class,RuntimeException.class})
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody Map<String, Object> handleUncaughtException(Exception ex) throws IOException {
		LOGGER.error("Internal Server Error", ex);
		Map<String, Object> map = Maps.newHashMap();
		map.put("error", "Unknown Error");
		if (ex.getCause() != null ) {
			map.put("message", ex.getCause().getMessage());
		} else {
			map.put("message", ex.getMessage());
		}
		return map;
	}
}