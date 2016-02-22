package com.ratedforum.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ratedforum.user.model.UserPasswordTokenBO;

@Repository
public interface UserPasswordTokenRepository extends JpaRepository<UserPasswordTokenBO, Long>{
	
	@Query("select upt from UserPasswordTokenBO upt where upt.user.id = :userId and upt.token = :token")
	public UserPasswordTokenBO findByUserIdAndToken(@Param("userId") final Long userId, @Param("token") final String token);
	
	@Query("select upt from UserPasswordTokenBO upt where upt.user.id = :userId")
	public UserPasswordTokenBO findByUserId(@Param("userId") final Long userId);
}
