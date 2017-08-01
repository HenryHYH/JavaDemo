package com.helloweb.springbootdemo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.helloweb.springbootdemo.configuration.RandomConfig;
import com.helloweb.springbootdemo.configuration.TestConfig;
import com.helloweb.springbootdemo.configuration.ValueConfig;

@SpringBootApplication
@EnableConfigurationProperties({ ValueConfig.class, TestConfig.class, RandomConfig.class })
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