package com.ratedforum.user.resource.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ratedforum.user.exception.PrincipalUserMismatchException;
import com.ratedforum.user.validation.NotPending;

@Component
public class UserAuthenticationInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthenticationInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		OAuth2Authentication auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
		Boolean isAdmin = auth.getAuthorities()
								.parallelStream()
								.anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

		String tokenUserId = getUserId(auth);
		LOGGER.info("Userid {} making a request to {}", tokenUserId, request.getRequestURI());

		if(!isAdmin && !tokenUserId.equalsIgnoreCase("anonymousUser")){
			StringBuilder uri = new StringBuilder(request.getRequestURI());
			if(uri.indexOf("/users/") == -1){
				return true;
			}
				
			NotPending notPending = ((HandlerMethod) handler).getMethod().getDeclaredAnnotation(NotPending.class);
			if(!isAuthorizedUser(auth, notPending, uri)){
				LOGGER.info("Unauthorized - user {} is in PENDING status.", tokenUserId);
				throw new UnauthorizedUserException("Unauthorized - user is in PENDING status.");
			}
			
			uri.delete(0, uri.indexOf("/users/") + 7);
			uri.delete(uri.indexOf("/") == -1 ? uri.length() : uri.indexOf("/"), uri.length());
							
			String userId = uri.toString();
			if (tokenUserId.equals(userId)) {
				LOGGER.info("UserId {} in principal matches URI userId {}", tokenUserId, userId);
				return true;
			}

			LOGGER.info("UserId {} in principal does not match URI userId {}", tokenUserId, userId);
			throw new PrincipalUserMismatchException("User in principal does not match URI userId");
		}
		
		return true;
	}
	
	private String getUserId(final OAuth2Authentication authentication){
		final Map<String, String> additionalInfo = authentication.getOAuth2Request().getRequestParameters();
		return additionalInfo.get("userId");
	}
	
	private boolean isAuthorizedUser(final OAuth2Authentication auth, final NotPending notPending, final StringBuilder uri){	
		if(notPending == null){
			return true;
		}
		
		final Map<String, String> additionalInfo = auth.getOAuth2Request().getRequestParameters();
		String userStatus = additionalInfo.get("userStatus");
		return !userStatus.equalsIgnoreCase("PENDING");
	}
}