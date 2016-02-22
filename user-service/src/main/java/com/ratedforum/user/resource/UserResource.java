package com.ratedforum.user.resource;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ratedforum.user.api.domain.User;
import com.ratedforum.user.exception.IncorrectParameterException;
import com.ratedforum.user.facade.UserCreator;
import com.ratedforum.user.model.RoleBO.Role;
import com.ratedforum.user.resource.hateos.factory.UserResourceHateoasBuilder;

@Controller
@RequestMapping(value="v1.0/users/")
public class UserResource{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);
	
	@Autowired
    private UserCreator userCreator;

	@Autowired
	private UserResourceHateoasBuilder userResourceHateoasBuilder;
	
	@RolesAllowed({"ROLE_ADMIN"})
    @RequestMapping(value = "admin", method=RequestMethod.POST)
    public ResponseEntity<ResourceSupport> createClubAdminUser(@RequestBody @Valid final User user, final BindingResult result) throws Exception{
    	LOGGER.info("Creating Club admin with username {}", user.getUsername());
		
		if (result.hasErrors()) {
			StringBuffer stringBuffer = new StringBuffer();
			result.getAllErrors().stream().parallel().forEach(t -> stringBuffer.append(t.getDefaultMessage() + " - "));
			throw new IncorrectParameterException(stringBuffer.toString());
		}
		
		userCreator.createUser(user, Role.ROLE_CLUB_ADMIN);
    	
    	ResourceSupport response = new ResourceSupport();
		response.add(userResourceHateoasBuilder.buildLinksForCreateClubAdminUser(user));
    	return new ResponseEntity<ResourceSupport>(response, org.springframework.http.HttpStatus.CREATED);
    }
}