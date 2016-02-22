package com.ratedforum.user.resource.hateos.factory;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Level;

import com.ratedforum.user.api.domain.ExtendedLink;
import com.ratedforum.user.api.domain.User;
import com.ratedforum.user.resource.LoggerResource;
import com.ratedforum.user.resource.UserResource;

@Service
public class LinksFactory {

	public ExtendedLink getCreateClubAdminUserLink(final User user, final String rel) throws Exception{
		final Link link = linkTo(methodOn(UserResource.class).createClubAdminUser(user, null)).withRel(rel);
		
		return ExtendedLink.extend(link)
    			.withName("clubAdminUser")
    			.withType("application/vnd.ratedforum.user.resource.UserResource+json")
				.withMethods("POST")
    			.withDescription("Register a club admin user");
	}
	
	public ExtendedLink getCurrentLogLevelLink(final String rel) throws Exception{
		final Link link = linkTo(methodOn(LoggerResource.class).getCurrentLoggingLevel()).withRel(rel);
		
		return ExtendedLink.extend(link)
    			.withName("getCurrentLogLevel")
    			.withType("application/vnd.ratedforum.user.resource.LoggerResource+json")
				.withMethods("GET")
    			.withDescription("The current logging level of the application");
	}
	
	public ExtendedLink getUpdateCurrentLogLevelLink(final Level level, final String rel) throws Exception{
		final Link link = linkTo(methodOn(LoggerResource.class).changeLoggingLevel(level)).withRel(rel);
		
		return ExtendedLink.extend(link)
    			.withName("updateLogLevel")
    			.withType("application/vnd.ratedforum.user.resource.LoggerResource+json")
				.withMethods("PUT")
    			.withDescription("Update logging level of the application");
	}
}
