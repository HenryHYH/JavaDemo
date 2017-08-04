package com.helloweb.springbootaop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspect {

	@Pointcut("execution(public * com.helloweb.springbootaop.controller..*.*(..))")
	public void webLog() {
	}

	@Before("webLog()")
	public void doBefore() {
		System.out.println("doBefore");
	}

	@After("webLog()")
	public void doAfter() {
		System.out.println("doAfter");
	}

	@Around("webLog()")
	public Object doAround(ProceedingJoinPoint point) throws Throwable {
		Object[] args = point.getArgs();
		System.out.println("aroundBefore");
		Object returnValue = point.proceed(args);
		System.out.println("aroundAfter");

		return "[" + returnValue + "]";
	}

	@AfterReturning("webLog()")
	public void doAfterReturing() {
		System.out.println("doAfterReturing");
	}
}
