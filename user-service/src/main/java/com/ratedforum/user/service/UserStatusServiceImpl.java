package com.ratedforum.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratedforum.user.model.UserStatusBO;
import com.ratedforum.user.model.UserStatusBO.UserStatus;
import com.ratedforum.user.repository.UserStatusRepository;

@Service
class UserStatusServiceImpl implements UserStatusService{

	@Autowired
	private UserStatusRepository userStatusRepository;
	
	@Override
	public UserStatusBO findById(final Integer id) {
		return userStatusRepository.getOne(id);
	}

	@Override
	public UserStatusBO findByName(final UserStatus userStatus) {
		return userStatusRepository.findByName(userStatus);
	}
}
