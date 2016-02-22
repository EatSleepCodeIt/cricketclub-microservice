package com.ratedforum.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratedforum.user.model.UserBO;
import com.ratedforum.user.repository.UserRepository;

@Service
class UserServiceImpl implements UserService{

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void save(final UserBO user) {
		LOGGER.info("Saving user: ", user.getUsername());
		userRepository.save(user);
	}

	@Override
	public UserBO findById(final Long userId) {
		return userRepository.findOne(userId);
	}

	@Override
	public UserBO findByUsername(final String username) {
		return userRepository.findByUsername(username);
	}
}
