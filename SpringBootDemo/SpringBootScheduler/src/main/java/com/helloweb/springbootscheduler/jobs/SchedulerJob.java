package com.helloweb.springbootscheduler.jobs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerJob {

	@Scheduled(fixedDelay = 2000) // 上一次执行完毕时间点后2秒再次执行
	public void fixedDelayJob() throws Exception {
		Thread.sleep(1000);
		show("FixedDelayJob");
	}

	@Scheduled(fixedRate = 2000) // 上一次开始实行时间点后2秒再次执行
	public void fixedRateJbo() {
		show("FixedRateJob");
	}

	@Scheduled(cron = "0/5 * * * * ?")
	public void cornJob() {
		show("CornJob");
	}

	private void show(String key) {
		String str = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
		System.out.println(String.format("%s\t: %s", key, str));
	}
}
