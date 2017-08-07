package com.helloweb.springbootscheduler.jobs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ConfigJob {

	@Scheduled(fixedRateString = "${scheduled.configJob.run.fixedrate}")
	public void run() {
		String str = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
		System.out.println(String.format("%s\t: %s", "run", str));
	}

}
