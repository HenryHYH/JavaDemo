package com.helloweb.aliyundemo.recommend.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helloweb.aliyundemo.recommend.sdk.RecommandEngineFactory;

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
		// RecommendFactory.rec();

		return now();
	}

	@RequestMapping("/home/log")
	public String log() throws Exception {
		List<String> logs = new LinkedList<>();
		// logs.add("{\"action\":\"user\",\"user_id\":\"u1\",\"tags\":{\"age\":\"1\",\"gender\":\"1\"},\"plates\":NULL}");
		// logs.add(
		// "{\"user_id\":\"196\", \"item_id\":242, \"action\":\"view\",\"bhv_datetime\":
		// \"2016-05-26 00:00:00\"}");

		logs.add("{\"action\":\"behavior\", \"bhv_type\":\"click\", \"bhv_amt\": 1.0,"
				+ "  \"user_id\":\"1\", \"act_obj\":\"i1\", \"obj_type\":\"item\","
				+ "  \"trace_id\":null, \"bhv_datetime\":\"2017-09-20 11:11:11\"}");

		RecommandEngineFactory.sendLog(logs);

		return now();
	}

}
