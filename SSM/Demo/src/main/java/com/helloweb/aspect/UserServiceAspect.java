package com.helloweb.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

import com.helloweb.entity.User;
import com.helloweb.util.logging.LogUtil;

@Service
@Aspect
public class UserServiceAspect {

	@Before("execution(* com.helloweb.service.IUserService.selectUser(..))")
	public void logBefore(JoinPoint joinPoint) {
		LogUtil.info("Before");
	}

	@After("execution(* com.helloweb.service.IUserService.selectUser(..))")
	public void logAfter(JoinPoint joinPoint) {
		LogUtil.info("After");
	}

	@AfterReturning(pointcut = "execution(* com.helloweb.service.IUserService.selectUser(..))", returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, List<User> result) {
		LogUtil.info("After returning");
		LogUtil.info("Result = " + (null == result ? "NULL" : result.size()));
	}

	@Around("execution(* com.helloweb.service.IUserService.selectUser(..))")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		LogUtil.info("Around before");
		Object result = joinPoint.proceed();
		LogUtil.info("Around after");

		return result;
	}
}
