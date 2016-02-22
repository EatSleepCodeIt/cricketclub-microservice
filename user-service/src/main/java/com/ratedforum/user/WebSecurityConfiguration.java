package com.ratedforum.user;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment env;
	
	@Bean
	protected RemoteTokenServices remoteTokenServices() {
		RemoteTokenServices rts = new RemoteTokenServices();
		rts.setClientId(env.getProperty("oauth.clientId"));
		rts.setClientSecret(env.getProperty("oauth.clientSecret"));
		rts.setCheckTokenEndpointUrl(env.getProperty("oauth.tokenEndpoint"));
		return rts;
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    OAuth2AuthenticationManager authenticationManager = new OAuth2AuthenticationManager();
	    authenticationManager.setTokenServices(remoteTokenServices());
	    return authenticationManager;
	}
	
	@Bean
	public TokenConverter getTokenConverter(){
		return new TokenConverter();
	}
	
	class TokenConverter extends DefaultAccessTokenConverter {
		
		private UserAuthenticationConverter userTokenConverter = new DefaultUserAuthenticationConverter();
		
		private final String USER_ID = "user_id";
		private final String USER_ROLE = "user_role";
		private final String USER_STATUS = "user_status";
		private final String USERNAME = "username";

		@SuppressWarnings("unchecked")
		@Override
		public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
			Map<String, String> parameters = new HashMap<String, String>();
			
			Set<String> scope = new LinkedHashSet<String>(map.containsKey(SCOPE) ? (Collection<String>) map.get(SCOPE) : Collections.<String>emptySet());
			
			Authentication user = userTokenConverter.extractAuthentication(map);
			String clientId = (String) map.get(CLIENT_ID);
			parameters.put(CLIENT_ID, clientId);
			parameters.put(USER_ID, (String) map.get(USER_ID));
			parameters.put(USERNAME, (String) map.get(USERNAME));
			parameters.put(USER_ROLE, (String) map.get(USER_ROLE));
			parameters.put(USER_STATUS, (String) map.get(USER_STATUS));
			
			Set<String> resourceIds = new LinkedHashSet<String>(map.containsKey(AUD) ? (Collection<String>) map.get(AUD) : Collections.<String>emptySet());
			
			OAuth2Request request = new OAuth2Request(parameters, clientId, null, true, scope, resourceIds, null, null,null);
			return new OAuth2Authentication(request, user);
		}
	}
}