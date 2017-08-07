package com.helloweb.springbootquartz.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.helloweb.springbootquartz.services.SimpleService;

public class XmlJob implements Job {

	private static int COUNT = 0;

	@Autowired
	private SimpleService service;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println(++COUNT);
		service.processData("XmlJob");
	}

}
