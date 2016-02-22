package com.ratedforum.user.service;

import org.springframework.stereotype.Service;

import com.ratedforum.user.model.UserBO;

@Service
public interface UserService {
	public void save(final UserBO user);
	public UserBO findById(final Long userId);
	public UserBO findByUsername(final String username);
}
