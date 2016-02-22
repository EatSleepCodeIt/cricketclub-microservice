package com.ratedforum.user.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ratedforum.user.api.domain.User;
import com.ratedforum.user.exception.ObjectAlreadyExistsException;
import com.ratedforum.user.facade.transformer.UserToUserBOTransformer;
import com.ratedforum.user.model.RoleBO;
import com.ratedforum.user.model.RoleBO.Role;
import com.ratedforum.user.model.UserBO;
import com.ratedforum.user.model.UserStatusBO;
import com.ratedforum.user.model.UserStatusBO.UserStatus;
import com.ratedforum.user.queue.publish.PublishService;
import com.ratedforum.user.service.RoleService;
import com.ratedforum.user.service.UserService;
import com.ratedforum.user.service.UserStatusService;

@Service
@Transactional
class UserCreatorImpl implements UserCreator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserCreatorImpl.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserStatusService userStatusService;
	
	@Autowired
	private UserToUserBOTransformer userToUserBOTransformer;
	
	@Autowired
	private PublishService publishService;

	@Override
	public void createUser(final User user, final Role role) throws ObjectAlreadyExistsException {
		LOGGER.info("Creating user {}", user.getUsername());
		UserBO userBO = userService.findByUsername(user.getUsername());
		if(userBO == null){
			LOGGER.info("Username {} already exists", user.getUsername());
			throw new ObjectAlreadyExistsException("Username " + user.getUsername() + " already exists");
		}
		
		userBO = userToUserBOTransformer.transform(user);
		final UserStatusBO userStatusBO = userStatusService.findByName(UserStatus.PENDING);		
		final RoleBO userRole = roleService.findByName(role);
		
		userBO.setUserStatusBO(userStatusBO);
		userBO.getRoles().add(userRole);
		
		userService.save(userBO);
		user.setUserId(userBO.getId());
		LOGGER.info("Published {} for user {}", PublishService.CREATED_USER, user.getUsername());
		//publishService.sendMessage(PublishService.CREATED_USER, user);
	}
}
