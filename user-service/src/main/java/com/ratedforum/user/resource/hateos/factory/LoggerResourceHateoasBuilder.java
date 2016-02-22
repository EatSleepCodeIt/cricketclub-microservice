package com.ratedforum.user.resource.hateos.factory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Level;

@Service
public class LoggerResourceHateoasBuilder {
	
	@Autowired
	private LinksFactory linksFactory;
	
	public List<Link> buildForChangeLoggingLevel(final Level level) throws Exception{
		final List<Link> linkLists = new ArrayList<Link>();	
		linkLists.add(linksFactory.getUpdateCurrentLogLevelLink(level, Link.REL_SELF));
		linkLists.add(linksFactory.getCurrentLogLevelLink("get-log-level"));
		
		return linkLists;
	}
	
	public List<Link> buildForGetCurrentLoggingLevel(final Level level) throws Exception{
		final List<Link> linkLists = new ArrayList<Link>();	
		linkLists.add(linksFactory.getCurrentLogLevelLink(Link.REL_SELF));
		linkLists.add(linksFactory.getUpdateCurrentLogLevelLink(level, "update-log-level"));	
		return linkLists;
	}
}