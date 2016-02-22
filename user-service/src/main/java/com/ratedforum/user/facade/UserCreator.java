package com.ratedforum.user.facade;

import org.springframework.stereotype.Service;

import com.ratedforum.user.api.domain.User;
import com.ratedforum.user.exception.ObjectAlreadyExistsException;
import com.ratedforum.user.model.RoleBO.Role;

@Service
public interface UserCreator {

	public void createUser(final User user, final Role role) throws ObjectAlreadyExistsException;
}
