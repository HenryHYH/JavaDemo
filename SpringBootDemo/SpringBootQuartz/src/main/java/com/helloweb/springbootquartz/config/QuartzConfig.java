package com.helloweb.springbootquartz.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import com.helloweb.springbootquartz.AutowiringSpringBeanJobFactory;
import com.helloweb.springbootquartz.jobs.SimpleJob;
import com.helloweb.springbootquartz.jobs.TestJob;

@Configuration
@ImportResource("classpath:quartz-config.xml")
public class QuartzConfig {

	@Autowired
	private List<Trigger> listOfTrigger;

	@Autowired
	private Map<String, Trigger> mapOfTrigger;

	@Bean
	public Properties quartzProperties() throws IOException {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
		propertiesFactoryBean.afterPropertiesSet();

		return propertiesFactoryBean.getObject();
	}

	@Bean
	public JobFactory jobFactory(ApplicationContext applicationContext) {
		AutowiringSpringBeanJobFactory factory = new AutowiringSpringBeanJobFactory();
		factory.setApplicationContext(applicationContext);

		return factory;
	}

	@Bean
	public SchedulerFactoryBean schedulerFactory(
			JobFactory jobFactory,
			@Qualifier("simpleJobTrigger") Trigger simpleJobTrigger,
			@Qualifier("testJobTrigger") Trigger testJobTrigger) throws IOException {

		SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
		factoryBean.setJobFactory(jobFactory);
		factoryBean.setQuartzProperties(quartzProperties());

		List<Trigger> list = new ArrayList<>();
		// list.add(simpleJobTrigger);
		// list.add(testJobTrigger);
		if (null != listOfTrigger && !listOfTrigger.isEmpty()) {
			list.addAll(listOfTrigger);
		}
		factoryBean.setTriggers(list.toArray(new Trigger[list.size()]));

		System.out.println(list.size());
		if (null != mapOfTrigger && !mapOfTrigger.isEmpty()) {
			for (Entry<String, Trigger> item : mapOfTrigger.entrySet()) {
				System.out.println(item.getKey());
			}
		}

		return factoryBean;
	}

	@Bean
	public JobDetailFactoryBean testJobDetail() {
		return createJobDetail(TestJob.class);
	}

	@Bean("testJobTrigger")
	public CronTriggerFactoryBean testJobTrigger(
			@Qualifier("testJobDetail") JobDetail jobDetail,
			@Value("${custom.testjob.corn}") String cronExpression) {
		return createCronTrigger(jobDetail, cronExpression);
	}

	@Bean
	public JobDetailFactoryBean simpleJobDetail() {
		return createJobDetail(SimpleJob.class);
	}

	@Bean("simpleJobTrigger")
	public SimpleTriggerFactoryBean simpleJobTrigger(
			@Qualifier("simpleJobDetail") JobDetail jobDetail,
			@Value("${custom.simplejob.frequency}") long frequency) {
		return createSimpleTrigger(jobDetail, frequency);
	}

	public static JobDetailFactoryBean createJobDetail(Class<?> jobClass) {
		JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
		factoryBean.setJobClass(jobClass);
		factoryBean.setDurability(true);

		return factoryBean;
	}

	public static CronTriggerFactoryBean createCronTrigger(JobDetail jobDetail, String cronExpression) {
		CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
		factoryBean.setJobDetail(jobDetail);
		factoryBean.setCronExpression(cronExpression);

		return factoryBean;
	}

	public static SimpleTriggerFactoryBean createSimpleTrigger(JobDetail jobDetail, long repeatInterval) {
		SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
		factoryBean.setJobDetail(jobDetail);
		factoryBean.setRepeatInterval(repeatInterval);
		factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);

		return factoryBean;
	}

}
