package com.web.study.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;


//dto에있는 class구조가 응답인터페이스라고 한다.
@Data
@RequiredArgsConstructor
public class ResponseDto {
	private final boolean success;
	private  final int code;
	private  final String message;
	
	public static ResponseDto of(boolean success, int code, String message) {
		return new ResponseDto(success, code, message);
	}
	
}
