package com.helloweb.springbootdemo.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helloweb.springbootdemo.configuration.RandomConfig;
import com.helloweb.springbootdemo.configuration.TestConfig;
import com.helloweb.springbootdemo.configuration.ValueConfig;

@RestController
public class ValueController {

	@Value("${value.name}")
	private String name;

	@Value("${value.number}")
	private int number;

	@RequestMapping("/value/{key}")
	public String getValue(@PathVariable String key) {

		String result = "default";

		switch (key) {
		case "name":
			result = name;
			break;
		case "number":
			result = String.valueOf(number);
			break;
		default:
			break;
		}

		return result;
	}

	@Autowired
	private ValueConfig valueConfig;

	@RequestMapping("/value2/{key}")
	public String getValue2(@PathVariable String key) {

		String result = "default";

		switch (key) {
		case "name":
			result = valueConfig.getName();
			break;
		case "number":
			result = String.valueOf(valueConfig.getNumber());
			break;
		case "combine":
			result = valueConfig.getCombine();
		default:
			break;
		}

		return result;
	}

	@Autowired
	private TestConfig testConfig;

	@RequestMapping("/valueTest/{key}")
	public String getTestValue(@PathVariable String key) {

		String result = "default";

		switch (key) {
		case "name":
			result = testConfig.getName();
			break;
		case "number":
			result = String.valueOf(testConfig.getNumber());
			break;
		case "combine":
			result = testConfig.getCombine();
		default:
			break;
		}

		return result;
	}

	@Autowired
	private RandomConfig randomConfig;

	@RequestMapping("/value/random")
	public JsonData getRandomValue() {
		JsonData obj = new JsonData();
		obj.setValue(randomConfig.getValue());
		obj.setNumber(randomConfig.getNumber());
		obj.setLongnumber(randomConfig.getLongnumber());
		obj.setUuid(randomConfig.getUuid());
		obj.setLess(randomConfig.getLess());
		obj.setRange(randomConfig.getRange());

		return obj;
	}

	class JsonData {

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
}
