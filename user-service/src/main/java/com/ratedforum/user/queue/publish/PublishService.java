package com.ratedforum.user.queue.publish;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway
public interface PublishService {

	public static final String RESET_PASSWORD = "RESET_PASSWORD";
	public static final String UPDATED_PASSWORD = "UPDATED_PASSWORD";
	public static final String CREATED_USER = "CREATED_USER";
	
	@Gateway(requestChannel="publishSubscribeChannel")
	public void sendMessage(Message<String> message);
}