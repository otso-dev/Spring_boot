package com.web.study.dto;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class DataResponseDto<T> extends ResponseDto {

	
	private final T data;
	
	private DataResponseDto(T data) {
		super(true, HttpStatus.OK.value(),"Successfully"); //httpstatus enum으로 만들어짐 키워드를 생성할 수 있다.
		this.data = data;
	}
	
	private DataResponseDto(T data, String message) {
		super(true, HttpStatus.OK.value(), message);
		this.data = data;
	}
	
	public static <T> DataResponseDto<T> of(T data){
		return new DataResponseDto<T>(data);
	}
	
	public static <T> DataResponseDto<T> of(T data, String message){
		return new DataResponseDto<T>(data, message);
	}
	
	public static <T> DataResponseDto<T> empty () {
		return new DataResponseDto<T>(null);
	}

}
