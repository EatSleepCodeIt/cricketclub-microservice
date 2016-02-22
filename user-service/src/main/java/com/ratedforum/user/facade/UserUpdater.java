package com.ratedforum.user.facade;

import org.springframework.stereotype.Service;

import com.ratedforum.user.api.domain.User;
import com.ratedforum.user.exception.ObjectExpiredException;
import com.ratedforum.user.exception.ObjectNotFoundException;
import com.ratedforum.user.model.UserStatusBO.UserStatus;

@Service
public interface UserUpdater {
	
	public void updateUser(final User user);
	
	public void updatePassword(final Long userId, final String password) throws ObjectNotFoundException;
	
	public void resetPassword(final Long userId, final String token, final String password) throws ObjectNotFoundException, ObjectExpiredException;
	
	public void updateUserStatus(final Long userId, final UserStatus userStatus) throws ObjectNotFoundException;
}

