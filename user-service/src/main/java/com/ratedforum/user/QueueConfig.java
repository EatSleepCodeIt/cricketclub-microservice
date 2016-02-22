package com.ratedforum.user;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class QueueConfig {


	public final static String QUEUE_NAME = "user-service";
	public final static String EXCHANGE_NAME = "rated-forum";

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Bean
	Queue queue() {
		return new Queue(QUEUE_NAME, true);
	}

	@Bean
	FanoutExchange exchange() {
		return new FanoutExchange(EXCHANGE_NAME);
	}
	
	@Bean
	public PublishSubscribeChannel publishSubscribeChannel(){
		return new PublishSubscribeChannel();
	}

	@Bean
	Binding binding(Queue queue, FanoutExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange);
	}

	@Bean
	public IntegrationFlow amqpOutboundFlow() {
	    return IntegrationFlows.from("publishSubscribeChannel")
	                .handleWithAdapter(h ->
	                    h.amqp(amqpTemplate).exchangeName(EXCHANGE_NAME))
	                .get();
	}
	
	@Bean
	public IntegrationFlow amqpInboundFlow() {
	    return IntegrationFlows
	            .from("publishSubscribeChannel")
	            .handle("queueConsumer", "onMessage")
	            .get();
	}
	
	/*@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(QUEUE_NAME);
		container.setMessageListener(new MessageListenerAdapter(consumerServiceImpl()));
		return container;
	}

	@Bean
	QueueConsumer consumerServiceImpl() {
		return new QueueConsumer();
	}

	@Bean
	MessageListenerAdapter listenerAdapter(QueueConsumer consumerServiceImpl) {
		return new MessageListenerAdapter(consumerServiceImpl);
	}*/
}