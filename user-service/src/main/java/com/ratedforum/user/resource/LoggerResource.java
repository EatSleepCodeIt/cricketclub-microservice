package com.ratedforum.user.resource;

import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.qos.logback.classic.Level;

import com.ratedforum.user.logger.LoggerFactoryService;
import com.ratedforum.user.resource.hateos.factory.LoggerResourceHateoasBuilder;

@Controller
@FeignClient("user-service")
@RequestMapping(value = "loggers")
public class LoggerResource {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggerResource.class);
	
	@Autowired
	private LoggerFactoryService loggerFactoryService;
	
	@Autowired
	private LoggerResourceHateoasBuilder loggerResourceHateoasBuilder;
	
	@RolesAllowed({ "ROLE_ADMIN" })
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<ResourceSupport> changeLoggingLevel(@RequestParam("level") final Level level) throws Exception {
		LOGGER.info("Changing log level to {}", level);
		
		loggerFactoryService.setLogger(level);
		
		ResourceSupport response = new ResourceSupport();
		response.add(loggerResourceHateoasBuilder.buildForChangeLoggingLevel(level));
		
		return new ResponseEntity<ResourceSupport>(response, org.springframework.http.HttpStatus.OK);
	}
	
	@RolesAllowed({ "ROLE_ADMIN" })
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Resource<Level>>  getCurrentLoggingLevel() throws Exception {
		LOGGER.info("Getting current log level");
		
		Level level = loggerFactoryService.getLoggerLevel();
		
		Resource<Level> response = new Resource<Level>(level);
		response.add(loggerResourceHateoasBuilder.buildForGetCurrentLoggingLevel(level));
		
		return new ResponseEntity<Resource<Level>>(response, org.springframework.http.HttpStatus.OK);
	}
}
