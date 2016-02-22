package com.ratedforum.user.facade.transformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ratedforum.user.api.domain.User;
import com.ratedforum.user.model.UserBO;

@Service
class UserToUserBOTransformerImpl implements UserToUserBOTransformer{

	private static final Logger LOGGER = LoggerFactory.getLogger(UserToUserBOTransformerImpl.class);
		
	@Override
	public UserBO transform(final User user){
		if(user == null){
			LOGGER.info("User object is null");
			throw new IllegalArgumentException("User object is null");
		}
		
		LOGGER.info("Transforming user to UserBO for user {}", user.getUsername());
		UserBO userBO = new UserBO();
		userBO.setUsername(user.getUsername());
		userBO.setPassword(user.getPassword());
				
		return userBO;
	}
}
