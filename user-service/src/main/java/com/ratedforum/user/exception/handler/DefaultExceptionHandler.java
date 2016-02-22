package com.ratedforum.user.exception.handler;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.common.collect.Maps;
import com.ratedforum.user.exception.IncorrectParameterException;
import com.ratedforum.user.exception.MissingParametersException;
import com.ratedforum.user.exception.ObjectAlreadyExistsException;
import com.ratedforum.user.exception.ObjectExpiredException;
import com.ratedforum.user.exception.ObjectNotFoundException;
import com.ratedforum.user.exception.PrincipalUserMismatchException;

@ControllerAdvice
public class DefaultExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionHandler.class);
	
	@RequestMapping(produces = { "application/vnd.captech-v1.0+json", "application/vnd.captech-v2.0+json" })
	@ExceptionHandler({ PrincipalUserMismatchException.class, UnauthorizedUserException.class })
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public @ResponseBody Map<String, Object> handleUnAuthorizedRequestException(Exception ex) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("error", "Unauthorized User");
		map.put("message", ex.getMessage());
		return map;
	}
	
	@RequestMapping(produces = { "application/vnd.captech-v1.0+json", "application/vnd.captech-v2.0+json" })
	@ExceptionHandler({ MissingServletRequestParameterException.class, UnsatisfiedServletRequestParameterException.class,MissingParametersException.class,
			HttpRequestMethodNotSupportedException.class, ServletRequestBindingException.class,IncorrectParameterException.class,MissingParametersException.class,
			ObjectExpiredException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody Map<String, Object> handleRequestException(Exception ex) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("error", "Bad Request");
		map.put("message", ex.getMessage());
		return map;
	}
	
	@RequestMapping(produces = { "application/vnd.captech-v1.0+json", "application/vnd.captech-v2.0+json" })
	@ExceptionHandler({ ObjectNotFoundException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody Map<String, Object> handleNotFountException(Exception ex) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("error", "Not Found");
		map.put("message", ex.getMessage());
		return map;
	}

	@RequestMapping(produces = { "application/vnd.captech-v1.0+json", "application/vnd.captech-v2.0+json" })
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	@ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	public @ResponseBody Map<String, Object> handleUnsupportedMediaTypeException(HttpMediaTypeNotSupportedException ex) throws IOException {
		Map<String, Object> map = Maps.newHashMap();
		map.put("error", "Unsupported Media Type");
		map.put("message", ex.getLocalizedMessage());
		map.put("supported", ex.getSupportedMediaTypes());
		return map;
	}
	
	@RequestMapping(produces = { "application/vnd.captech-v1.0+json", "application/vnd.captech-v2.0+json" })
	@ExceptionHandler({ObjectAlreadyExistsException.class })
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public @ResponseBody Map<String, Object> handleConflictException(Exception ex) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("error", "Conflict Error");
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