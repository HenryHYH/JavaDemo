package com.helloweb.springbootmessaging.messaging;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {

	@Autowired
	private AmqpTemplate template;

	public void send() {
		String message = "Hello " + new Date();
		template.convertAndSend("hello", message);
	}

}
