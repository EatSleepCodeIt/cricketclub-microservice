package com.ratedforum.user;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableResourceServer
public class OAuthResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private Environment env;
	
	@Autowired
	private DataSource dataSource;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources)
			throws Exception {
		resources.resourceId(env.getProperty("oauth.resourceId"));
	}
	
	@Bean
	public JdbcTokenStore tokenStore(){
		return new JdbcTokenStore(dataSource);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception { 
		// @formatter:off
		http
			.anonymous()
			.and()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
	        // For some reason we cant just "permitAll" OPTIONS requests which are needed for CORS support. Spring Security
	        // will respond with an HTTP 401 nonetheless.
	        // So we just put all other requests types under OAuth control and exclude OPTIONS.
	        .authorizeRequests()
	        	.antMatchers("/info").permitAll()
	        	.antMatchers("/metrics").permitAll()
	        	.antMatchers("/health").permitAll()
	        	.antMatchers("/**").permitAll()
	        	/*.antMatchers(HttpMethod.POST, "/v1.0/user/register**").permitAll()
	            .antMatchers(HttpMethod.GET, "/**").access("#oauth2.hasScope('read') or #oauth2.hasScope('all')")
	            .antMatchers(HttpMethod.POST, "/**").access("#oauth2.hasScope('write') or #oauth2.hasScope('all')")
	            .antMatchers(HttpMethod.PATCH, "/**").access("#oauth2.hasScope('write') or #oauth2.hasScope('all')")
	            .antMatchers(HttpMethod.PUT, "/**").access("#oauth2.hasScope('write') or #oauth2.hasScope('all')")
	            .antMatchers(HttpMethod.DELETE, "/**").access("#oauth2.hasScope('write') or #oauth2.hasScope('all')")*/
		    .and()
		        // Add headers required for CORS requests.
		        .headers().addHeaderWriter((request, response) -> {
		            response.addHeader("Access-Control-Allow-Origin", "*");
		            if (request.getMethod().equals("OPTIONS")) {
		                response.setHeader("Access-Control-Allow-Methods", request.getHeader("Access-Control-Request-Method"));
		                response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
		            }
		        });
					
		// @formatter:on
	}
}