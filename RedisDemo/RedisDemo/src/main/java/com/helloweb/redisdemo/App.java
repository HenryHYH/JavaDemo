package com.helloweb.redisdemo;

import redis.clients.jedis.Jedis;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Start");

		test();

		System.out.println("End");
	}

	private static void test() {
		Jedis jedis = new Jedis();
		jedis.set("jedis", "hello world");
		String value = jedis.get("jedis");
		System.out.println(value);
	}
}
