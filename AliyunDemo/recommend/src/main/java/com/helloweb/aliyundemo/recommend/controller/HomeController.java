package com.helloweb.aliyundemo.recommend.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helloweb.aliyundemo.recommend.sdk.RecommendFactory;

@RestController
public class HomeController {

	@RequestMapping("")
	public String index() {
		return "Home/Index";
	}

	private String now() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
	}

	@RequestMapping("/home/rec")
	public String rec() throws Exception {
		RecommendFactory.rec();

		return now();
	}

	@RequestMapping("/home/log")
	public String log() throws Exception {
		List<String> logs = new LinkedList<>();
		logs.add("{\"action\":\"user\",\"user_id\":\"u1\",\"tags\":{\"age\":\"1\",\"gender\":\"1\"},\"plates\":\"1\"}");
		logs.add("{\"action\":\"user\",\"user_id\":\"u2\",\"tags\":{\"age\":\"1\",\"gender\":\"1\"},\"plates\":\"1\"}");
		logs.add("{\"action\":\"user\",\"user_id\":\"u3\",\"tags\":{\"age\":\"1\",\"gender\":\"1\"},\"plates\":\"1\"}");
		RecommendFactory.sendLog(logs);

		return now();
	}

}
