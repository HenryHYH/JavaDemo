package com.helloweb.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import com.helloweb.util.logging.LogUtil;

@Service
@Aspect
public class LogAspect {
	@Pointcut("@annotation(Log)")
	public void logPointcut() {
	}

	@Around("logPointcut()")
	public Object doSurround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Object[] objects = proceedingJoinPoint.getArgs();
		String name = proceedingJoinPoint.getSignature().getName();
		String className = proceedingJoinPoint.getTarget().getClass().getName();
		long startTime = System.currentTimeMillis();

		Object result = proceedingJoinPoint.proceed();

		LogUtil.info("----------");
		LogUtil.info(String.format("Class name: %s, Function name: %s", className, name));
		if (null != objects) {
			for (Object item : objects) {
				LogUtil.info("In args: " + item);
			}
		}
		LogUtil.info("Out args: " + result);
		LogUtil.info(String.format("Execute time: %s", System.currentTimeMillis() - startTime));
		LogUtil.info("----------");

		return result;
	}
}
