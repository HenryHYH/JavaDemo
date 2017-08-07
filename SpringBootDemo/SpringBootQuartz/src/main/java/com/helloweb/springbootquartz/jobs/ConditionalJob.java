package com.helloweb.springbootquartz.jobs;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Component;

import com.helloweb.springbootquartz.config.QuartzConfig;
import com.helloweb.springbootquartz.services.SimpleService;

@Component
public class ConditionalJob implements Job {

	@Autowired
	private SimpleService service;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		service.processData("ConditionalJob");
	}

	@Bean("conditionalJobBean")
	public JobDetailFactoryBean conditionalJob() {
		return QuartzConfig.createJobDetail(ConditionalJob.class);
	}

	@Bean
	@ConditionalOnProperty(value = "custom.conditionaljob.enable", havingValue = "true")
	public CronTriggerFactoryBean conditionalJobTrigger(
			@Qualifier("conditionalJobBean") JobDetail jobDetail) {
		return QuartzConfig.createCronTrigger(jobDetail, "0/3 * * * * ?");
	}

}
