package com.ratedforum.user.service;

import org.springframework.stereotype.Service;

import com.ratedforum.user.model.UserPasswordTokenBO;

@Service
public interface UserPasswordTokenService {
	public void save(final UserPasswordTokenBO userPasswordTokenBO);
	public void delete(final UserPasswordTokenBO userPasswordTokenBO);
	public UserPasswordTokenBO findByUserIdAndToken(final Long userId, final String token);
	public UserPasswordTokenBO findByUserId(final Long userId);
}
