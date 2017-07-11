package com.helloweb.schedulerservice.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.helloweb.schedulerservice.bean.AnotherBean;

public class SchedulerJob extends QuartzJobBean {

	private AnotherBean anotherBean;

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		anotherBean.printAnotherMessage();
	}

	public void setAnotherBean(AnotherBean anotherBean) {
		this.anotherBean = anotherBean;
	}
}
