package com.helloweb.springbootscheduler.jobs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestJob {

	@Scheduled(fixedRate = 1000) // 上一次开始实行时间点后2秒再次执行
	public void fixedDelayJob() throws Exception {
		show("JobStart");
		Thread.sleep(5000);
		show("JobFinish");
	}

	private void show(String key) {
		String str = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
		System.out.println(String.format("%s\t: %s", key, str));
	}

}
