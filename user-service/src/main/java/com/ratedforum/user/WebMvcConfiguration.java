package com.ratedforum.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.ratedforum.user.resource.interceptor.UserAuthenticationInterceptor;

@Configuration
@ComponentScan
@EnableWebMvc
@EnableEntityLinks
@EnableSpringDataWebSupport //pagination
@EnableHypermediaSupport(type = { HypermediaType.HAL }) //link
class WebMvcConfiguration extends WebMvcConfigurationSupport {
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer c) {
		c.defaultContentType(MediaType.APPLICATION_JSON);
	}
	
	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping(){
		RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();
		mapping.setInterceptors(new Object[] { new UserAuthenticationInterceptor()});
		return mapping;
	}
}