package com.ratedforum.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ratedforum.user.model.UserBO;

@Repository
public interface UserRepository extends JpaRepository<UserBO, Long>{

	public UserBO findByUsernameAndPassword(final String username, final String password);
	
	public UserBO findById(final Long id);
	
	public UserBO findByUsername(final String username);
}
