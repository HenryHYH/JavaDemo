package com.helloweb.springbootmessaging;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.helloweb.springbootmessaging.messaging.Sender;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class HelloTest {

	@Autowired
	private Sender sender;

	@Test
	public void hello() {
		sender.send();
	}

	@Test
	public void many() {
		for (int i = 0; i < 100; i++) {
			sender.send();
		}
	}

}
