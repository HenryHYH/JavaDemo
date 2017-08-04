package com.helloweb.springbootaop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class PerformanceAspect {

	private Logger logger = LoggerFactory.getLogger(WebLogAspect.class);
	private ThreadLocal<Long> startTime = new ThreadLocal<>();

	@Pointcut("execution(public * com.helloweb.springbootaop.controller..*.*(..))")
	public void webLog() {
	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) {
		startTime.set(System.currentTimeMillis());
	}

	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfterReturing(Object ret) {
		logger.info("SPEND TIME: " + (System.currentTimeMillis() - startTime.get()));
	}

}
