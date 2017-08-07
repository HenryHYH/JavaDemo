package com.helloweb.springbootquartz.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.helloweb.springbootquartz.services.SimpleService;

public class SimpleJob implements Job {

	@Autowired
	private SimpleService service;

	@Override
	public void execute(JobExecutionContext context) {
		service.processData("SimpleJob");
	}

}
