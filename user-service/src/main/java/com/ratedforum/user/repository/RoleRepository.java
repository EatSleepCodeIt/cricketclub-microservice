package com.ratedforum.user.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ratedforum.user.model.RoleBO;
import com.ratedforum.user.model.RoleBO.Role;

@Repository
public interface RoleRepository extends JpaRepository<RoleBO, Integer>{

	@Cacheable("RoleService.findById")
	public RoleBO findById(final Integer id);
	
	@Cacheable("RoleService.findByName")
	public RoleBO findByName(final Role role);
}
