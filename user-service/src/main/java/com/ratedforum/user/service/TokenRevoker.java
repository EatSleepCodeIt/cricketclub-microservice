package com.ratedforum.user.service;

import org.springframework.stereotype.Service;

@Service
public interface TokenRevoker {
		
	public void revoke(final String userId);
}
