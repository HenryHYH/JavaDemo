package com.helloweb.springbootmessaging.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(
		queues = "hello")
public class Receiver {

	@RabbitHandler
	public void receive(String message) {
		System.out.println("Receive <" + message + ">");
	}

}
