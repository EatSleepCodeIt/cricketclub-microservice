package com.ratedforum.user.queue.publish;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
class PublishServiceImpl {

	private static final Logger LOGGER = LoggerFactory.getLogger(PublishServiceImpl.class);
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private PublishService publishService;

	private Gson gson = new Gson();
	
	@Scheduled(initialDelay = 50, fixedDelay = 100)
	public void send(){
		sendMessage("SAVED_HO", "Hello");
	}
	
	public void sendMessage(String action, Object object) {
		LOGGER.info("Publishing with routing key {}", action);
		
		String appname = environment.getProperty("spring.application.name");

		String json = gson.toJson(object);
		try {
			Message<String> m1 = null;
			Map<String, Object> headers = new HashMap<String, Object>();
			headers.put(appname, "");
			headers.put("ACTION", action);
			m1 = new GenericMessage<String>(json, headers);
			publishService.sendMessage(m1);
		} catch (Exception e) {
			LOGGER.error("error sending message {}. Message: {}", json, e.getMessage());
		}
	}

}