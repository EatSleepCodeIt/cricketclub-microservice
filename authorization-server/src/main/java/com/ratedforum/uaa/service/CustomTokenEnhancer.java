package com.ratedforum.uaa.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Service;

import com.ratedforum.uaa.model.UserBO;

@Service
public class CustomTokenEnhancer implements TokenEnhancer {
	
	@Autowired
	private UserAuthenticationManager userAuthenticationManager;
	
	private List<TokenEnhancer> delegates = Collections.emptyList();

	public void setTokenEnhancers(List<TokenEnhancer> delegates) {
	    this.delegates = delegates;
	}
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		DefaultOAuth2AccessToken tempResult = (DefaultOAuth2AccessToken) accessToken;

	    final Map<String, Object> additionalInformation = new HashMap<String, Object>();
	    UserBO userBO = userAuthenticationManager.findByUsername(authentication.getName());
		
	    additionalInformation.put("userId", userBO.getId().toString());
	    additionalInformation.put("username", userBO.getUsername());
	    additionalInformation.put("userRoles", userBO.getRoles());
	    additionalInformation.put("userStatus", userBO.getUserStatusBO().getName());
	    tempResult.setAdditionalInformation(additionalInformation);

	    OAuth2AccessToken result = tempResult;
	    for (TokenEnhancer enhancer : delegates) {
	        result = enhancer.enhance(result, authentication);
	    }
	    return result;
	}
}
