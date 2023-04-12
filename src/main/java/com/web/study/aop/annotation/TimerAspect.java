package com.web.study.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//실행중일때 이 어노테이션을 적용시켜라
@Target({ElementType.METHOD}) //적용시킬 대상 -> ElementType으로 method,class등등 지정할수있음.
public @interface TimerAspect {
	
}
