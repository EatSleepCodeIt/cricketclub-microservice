package com.ratedforum.uaa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ratedforum.uaa.model.UserBO;

@Repository
public interface UserRepository extends JpaRepository<UserBO, Long>{

	public UserBO findByUsername(final Object username);

}
