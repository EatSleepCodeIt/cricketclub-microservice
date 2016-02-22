package com.ratedforum.user.facade;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.config.DynamicPropertyFactory;
import com.ratedforum.user.api.domain.User;
import com.ratedforum.user.exception.ObjectExpiredException;
import com.ratedforum.user.exception.ObjectNotFoundException;
import com.ratedforum.user.model.UserBO;
import com.ratedforum.user.model.UserPasswordTokenBO;
import com.ratedforum.user.model.UserStatusBO;
import com.ratedforum.user.model.UserStatusBO.UserStatus;
import com.ratedforum.user.queue.publish.PublishService;
import com.ratedforum.user.service.TokenRevoker;
import com.ratedforum.user.service.UserPasswordTokenService;
import com.ratedforum.user.service.UserService;
import com.ratedforum.user.service.UserStatusService;

@Service
@Transactional
class UserUpdaterImpl implements UserUpdater {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserUpdaterImpl.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserStatusService userStatusService;
	
	@Autowired
	private UserPasswordTokenService userPasswordTokenService;
	
	@Autowired
	private PublishService publishService;
		
	@Autowired
	private TokenRevoker tokenRevoker;
	
	@Autowired
	private DynamicPropertyFactory dynamicPropertyFactory;
	
	@Override
	public void updateUser(final User user) {
		UserBO existingUser = userService.findById(user.getUserId());
		if(!existingUser.getUsername().equals(user.getUsername())){
			LOGGER.info("Changed username from {} to {}",existingUser.getUsername(), user.getUsername());
			existingUser.setUsername(user.getUsername());
			userService.save(existingUser);
		}	
	}

	@Override
	public void resetPassword(final Long userId, final String token, final String password) throws ObjectNotFoundException, ObjectExpiredException {
		UserPasswordTokenBO userPasswordToken = userPasswordTokenService.findByUserIdAndToken(userId, token);
		if(userPasswordToken == null){
			LOGGER.info("Token for user id {} and token {} not found", userId, token);
			throw new ObjectNotFoundException("Reset password token for user id " + userId + " and token " + token + " not found");
		}
		
		final Integer OFFSET = dynamicPropertyFactory.getStringProperty("user.reset.password.offset.hours","").getDynamicProperty().getInteger();
		
		final long oldTimeMiliseconds = userPasswordToken.getCreatedTs().getTime();
		final long currentTimeMiliseconds = new Date().getTime();
		final long offsetMiliseconds = OFFSET * 60 * 60 * 1000;
		
		if((currentTimeMiliseconds - offsetMiliseconds) > oldTimeMiliseconds){
			LOGGER.info("Token generated at {} for user id {} and token {} has expired", userPasswordToken.getCreatedTs(), userId, token);
			throw new ObjectExpiredException("Token generated at " +  userPasswordToken.getCreatedTs() + " for user id " + userId + " and token " + token + " has expired");
		}
				
		LOGGER.debug("Resetting password for user {}", userId);
		UserBO user = userPasswordToken.getUser();
		user.setPassword(DigestUtils.md5Hex(password));
		
		userService.save(user);
//		/publishService.sendMessage(PublishService.UPDATED_PASSWORD, user);
		userPasswordTokenService.delete(userPasswordToken);
	}

	@Override
	public void updatePassword(final Long userId, final String password) throws ObjectNotFoundException {
		LOGGER.info("Update password for user {}", userId);
		UserBO userBO = userService.findById(userId);
		if(userBO == null){
			LOGGER.info("User {} not found", userId);
			throw new ObjectNotFoundException("User " + userId + " not found");
		}
		
		LOGGER.debug("Updating password for user {}", userId);
		userBO.setPassword(DigestUtils.md5Hex(password));
		userService.save(userBO);
	}

	@Override
	public void updateUserStatus(final Long userId, final UserStatus userStatus) throws ObjectNotFoundException {
		LOGGER.info("Update user status for user {}", userId);
		UserBO userBO = userService.findById(userId);
		if(userBO == null){
			LOGGER.info("User {} not found", userId);
			throw new ObjectNotFoundException("User " + userId + " not found");
		}
		
		UserStatusBO userStatusBO = userStatusService.findByName(userStatus);
		userBO.setUserStatusBO(userStatusBO);
		userService.save(userBO);
		tokenRevoker.revoke(userId.toString());
	}
}
	