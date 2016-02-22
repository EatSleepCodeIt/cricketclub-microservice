package com.ratedforum.uaa.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.ratedforum.uaa.exception.UserAuthenticationException;
import com.ratedforum.uaa.model.UserBO;
import com.ratedforum.uaa.repository.UserRepository;

@Service
public class UserAuthenticationManager implements AuthenticationManager{

	@Autowired
	private UserRepository userRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Object username = authentication.getPrincipal();
		Object password = authentication.getCredentials();
		
		String encryptedPassword = DigestUtils.md5Hex(password.toString());
		UserBO userBO = userRepository.findByUsername(username);
        if(userBO == null || !userBO.getPassword().equals(encryptedPassword)){
        	throw new UserAuthenticationException("User authentication failed");
        }
		
        return new UsernamePasswordAuthenticationToken(userBO.getUsername(), userBO.getPassword(), userBO.getRoles());
	}

	public UserBO findByUsername(final String username) {
		return userRepository.findByUsername(username);
	}
}
