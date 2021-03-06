package com.ratedforum.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratedforum.user.model.UserPasswordTokenBO;
import com.ratedforum.user.repository.UserPasswordTokenRepository;

@Service
class UserPasswordTokenServiceImpl implements UserPasswordTokenService{

	private static final Logger LOGGER = LoggerFactory.getLogger(UserPasswordTokenServiceImpl.class);
	
	@Autowired
	private UserPasswordTokenRepository userPasswordTokenRepository;

	@Override
	public void save(final UserPasswordTokenBO userPasswordTokenBO) {
		LOGGER.info("Generating a reset password token for user {}",userPasswordTokenBO.getUser().getId());
		userPasswordTokenRepository.save(userPasswordTokenBO);
	}
	
	@Override
	public UserPasswordTokenBO findByUserId(final Long userId) {
		LOGGER.info("Getting a reset password token for user {}", userId);
		UserPasswordTokenBO userPasswordTokenBO = userPasswordTokenRepository.findByUserId(userId);
		
		if(userPasswordTokenBO == null){
			userPasswordTokenBO = new UserPasswordTokenBO();
		}
		
		return userPasswordTokenBO;
	}

	@Override
	public UserPasswordTokenBO findByUserIdAndToken(final Long userId, final String token) {
		LOGGER.info("Getting a reset password token for user {}", userId);
		return userPasswordTokenRepository.findByUserIdAndToken(userId, token);
	}

	@Override
	public void delete(UserPasswordTokenBO userPasswordTokenBO) {
		LOGGER.info("Remove reset password token for user {} and token {}", userPasswordTokenBO.getUser().getId(), userPasswordTokenBO.getToken());
		userPasswordTokenRepository.delete(userPasswordTokenBO);
	}
}
