package com.ratedforum.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratedforum.user.model.RoleBO;
import com.ratedforum.user.model.RoleBO.Role;
import com.ratedforum.user.repository.RoleRepository;

@Service
class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public RoleBO findById(final Integer id) {
		return roleRepository.findById(id);
	}

	@Override
	public RoleBO findByName(final Role role) {
		return roleRepository.findByName(role);
	}
}
