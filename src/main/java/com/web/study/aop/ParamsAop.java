package com.web.study.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class ParamsAop {

	@Pointcut("@annotation(com.web.study.aop.annotation.ParamsAspect)")
	private void pointCut() {
	}

	@Around("pointCut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

		StringBuilder builder = new StringBuilder();

		CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();// 매개변수명을 들고올려면 CodeSignature로
																				// DownCasting을 해줘야한다.
		String[] parameterNames = codeSignature.getParameterNames();
		Object[] args = joinPoint.getArgs();

		for (int i = 0; i < args.length; i++) {
			if(i != 0) {
				builder.append(", ");
			}
			builder.append(parameterNames[i] + ": " + args[i]);
		}
		log.info("[ Params ] >>> {}", builder.toString());
		return joinPoint.proceed();// return을 바로 해주면 전처리만 한다.
	}
}
