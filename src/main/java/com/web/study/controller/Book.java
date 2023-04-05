package com.web.study.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Data
public class Book {
	private int bookcode;
	private String bookname;
	private String date;
	private String recommend;
}
