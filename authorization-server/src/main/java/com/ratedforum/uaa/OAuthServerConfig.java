package com.ratedforum.uaa;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import com.ratedforum.uaa.service.CustomTokenEnhancer;
import com.ratedforum.uaa.service.UserAuthenticationManager;

@Configuration
@EnableAuthorizationServer
public class OAuthServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

    @Autowired
    private UserAuthenticationManager authenticationManager;
    
    @Autowired
    private ClientDetailsService clientDetailsService;
    
    @Autowired
    private CustomTokenEnhancer customTokenEnhancer;
    
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	    
	@Bean
	public TokenStore tokenStore(){
		return new JdbcTokenStore(dataSource);
	}
	
	@Bean
	public OAuth2AuthenticationEntryPoint oAuth2AuthenticationEntryPoint(){
		OAuth2AuthenticationEntryPoint entryPoint = new OAuth2AuthenticationEntryPoint();
		entryPoint.setTypeName("Basic");
		return entryPoint;
	}
	
	@Bean
	public OAuth2AccessDeniedHandler oauthAccessDeniedHandler(){
		return new OAuth2AccessDeniedHandler();
	}
		
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints	
				.authenticationManager(authenticationManager)
				.accessTokenConverter(new DefaultAccessTokenConverter())
				.tokenEnhancer(customTokenEnhancer)
				.tokenStore(tokenStore());
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.checkTokenAccess("hasRole('ROLE_RESOURCE_SERVER')")
	            .passwordEncoder(passwordEncoder());
	}

	@Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }
}
