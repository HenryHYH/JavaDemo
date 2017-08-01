package com.helloweb.springbootdemo.configuration;

import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ConfigurationProperties(prefix = "myrandom")
@Configuration
@PropertySource("classpath:random.properties")
public class RandomConfig {

	private String value;
	private int number;
	private long longnumber;
	private UUID uuid;
	private int less;
	private int range;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public long getLongnumber() {
		return longnumber;
	}

	public void setLongnumber(long longnumber) {
		this.longnumber = longnumber;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public int getLess() {
		return less;
	}

	public void setLess(int less) {
		this.less = less;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}
}
