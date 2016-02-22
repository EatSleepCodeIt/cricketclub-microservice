package com.ratedforum.user.queue.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.core.MessageSelector;
import org.springframework.messaging.Message;

public class QueueFilterImpl implements MessageSelector {

	@Autowired
	private org.springframework.core.env.Environment environment;

	@Override
	public boolean accept(Message<?> message) {

		String filter = environment.getProperty("aws.queue.filter");

		if ((message.getHeaders().containsKey("payment-service") || 
			message.getHeaders().containsKey("profile-service") ||
			message.getHeaders().containsKey("user-service") ||
			message.getHeaders().containsKey("tmprofile-service") ||
			message.getHeaders().containsKey("hoprofile-service") ||
			message.getHeaders().containsKey("job-service") ||
			message.getHeaders().containsKey("notification-service") ||
			message.getHeaders().containsKey("billing-service")) &&
			message.getHeaders().containsKey(filter)) {
			return true;
		}
		return false;
	}

}
