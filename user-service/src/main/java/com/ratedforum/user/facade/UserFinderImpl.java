package com.ratedforum.user.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratedforum.user.service.UserService;

@Service
class UserFinderImpl implements UserFinder {

	@Autowired
	private UserService userService;

}
