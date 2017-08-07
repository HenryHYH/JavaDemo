package com.helloweb.springbootquartz.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.helloweb.springbootquartz.services.SimpleService;

public class TestJob implements Job {

	@Autowired
	private SimpleService service;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		service.processData("TestJob");
	}

}
