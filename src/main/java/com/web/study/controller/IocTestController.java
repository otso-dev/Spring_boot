package com.web.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.IocAndDi.IocTest;
import com.web.study.IocAndDi.IocTest2;

import lombok.RequiredArgsConstructor;

@RestController
//@RequiredArgsConstructor
public class IocTestController {

	
//	@Autowired
//	private IocTest iocTest;
	
	@Autowired
	private  IocTest iocTest;
	@Autowired
	private  IocTest2 iocTest2;

	//Spring이 component가 달린 class를 찾아서 ioc 컨테이너 내부에 생성되어 넣고,
	//Autowired가 달린 class를 찾아서 Spring이 알아서 DI를 해준다. -> IOC와 DI의 핵심
	
	//@autowired와 RequiredArgsConstructor autowired는 부품이 많을때, RequiredArgsConstructor는 부품이 하나일때 
	//component를 등록하는 것을 bean이라고 한다.
	
	@GetMapping("/ioc/test")
	public Object test() {
		iocTest.run();
		iocTest2.run();
		return null;
	}
}

//@Autowired의 원현
/*
 * private final IocTest iocTest;
 * private final IocTest2 iocTest2;
 *  
 * public IocTest(IocTest iocTest, IocTest2 iocTest){
 *	this.iocTest = iocTest;
 *	this.iocTest2 = iocTest2;
 * 
 * }
 * 
 * @Component는 크게 4가지의 종류가 있음.
 * 
 */
