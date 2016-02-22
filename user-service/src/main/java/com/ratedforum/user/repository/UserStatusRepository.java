package com.ratedforum.user.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ratedforum.user.model.UserStatusBO;
import com.ratedforum.user.model.UserStatusBO.UserStatus;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatusBO, Integer>{
	
	@Cacheable("UserStatusRepository.findById")
	public UserStatusBO findById(final Integer id);
	
	@Cacheable("UserStatusRepository.findByName")
	public UserStatusBO findByName(final UserStatus userStatus);
}
