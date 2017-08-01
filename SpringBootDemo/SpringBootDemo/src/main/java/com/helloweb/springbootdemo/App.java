package com.helloweb.springbootdemo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(App.class);
		Map<String, Object> defaultProperties = new HashMap<>();
		defaultProperties.put("server.port", 8082);
		app.setDefaultProperties(defaultProperties);
		app.run(args);

		// java -jar xx.jar --server.port=8090
	}
}