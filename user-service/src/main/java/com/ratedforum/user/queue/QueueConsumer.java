package com.ratedforum.user.queue;

import org.springframework.integration.annotation.MessageEndpoint;

@MessageEndpoint
public class QueueConsumer {

	//@Override
	public void onMessage(String message) {
		/*if(message.getMessageProperties().getHeaders().containsKey("user-service")){
			System.out.println("printing user-service");
		}*/
		System.out.println("printing message " + message);
		//String a = new String(message.getBody());
		//System.out.println("Message = " + a);
	}
}
