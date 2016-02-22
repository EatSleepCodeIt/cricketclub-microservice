package com.ratedforum.user.resource.hateos.factory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import com.ratedforum.user.api.domain.User;

@Service
public class UserResourceHateoasBuilder {

	@Autowired
	private LinksFactory linksFactory;
	
	public List<Link> buildLinksForCreateClubAdminUser(final User user) throws Exception{
		List<Link> links = new ArrayList<Link>();
		links.add(linksFactory.getCreateClubAdminUserLink(user, Link.REL_SELF));
		return links;
	}
}
