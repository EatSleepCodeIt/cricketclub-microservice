package com.ratedforum.user.service;

import org.springframework.stereotype.Service;

import com.ratedforum.user.model.RoleBO;
import com.ratedforum.user.model.RoleBO.Role;

@Service
public interface RoleService {

	public RoleBO findById(final Integer id);

	public RoleBO findByName(final Role role);
}
