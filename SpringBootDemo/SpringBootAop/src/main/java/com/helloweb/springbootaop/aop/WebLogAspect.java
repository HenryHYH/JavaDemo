package com.helloweb.springbootaop.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Order(2)
public class WebLogAspect {

	private Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

	@Pointcut("execution(public * com.helloweb.springbootaop.controller..*.*(..))")
	public void webLog() {
	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		logger.info("URL: " + request.getRequestURL().toString());
		logger.info("HTTP_METHOD: " + request.getMethod());
		logger.info("IP: " + request.getRemoteAddr());
		logger.info("CLASS_METHOD: "
				+ joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName());
		logger.info("ARGS: " + Arrays.toString(joinPoint.getArgs()));
	}

	@After("webLog()")
	public void doAfter() {
		logger.info("DO AFTER");
	}

	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfterReturing(Object ret) {
		logger.info("RESPONSE: " + ret);
	}

}
