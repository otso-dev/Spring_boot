package com.web.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.web.study.IocAndDi.IocTest;
import com.web.study.IocAndDi.TestB;

@SpringBootApplication
public class StudyApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(StudyApplication.class, args);
//		iocAndDiTest();
	}
	
	
//	public static void iocAndDiTest() {
//		
//		//Inversion Of Control === IOC 제어의 역전
//		//원래 개발자가 직접 class를 만들고 프로그램을 돌아가게끔 만들었다면
//		//IOC는 컨테이너 내부에 객체를 만들어 두면 Spring이 알아서 돌아가게끔 해준다.
//		 
//		IocTest iocTest = new IocTest(new TestB());
//		
//		iocTest.run();
//	}
}
