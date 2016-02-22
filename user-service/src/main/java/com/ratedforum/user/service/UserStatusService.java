package com.ratedforum.user.service;

import org.springframework.stereotype.Service;

import com.ratedforum.user.model.UserStatusBO;
import com.ratedforum.user.model.UserStatusBO.UserStatus;

@Service
public interface UserStatusService {
	
	public UserStatusBO findById(final Integer id);
	public UserStatusBO findByName(final UserStatus userStatus);
}
