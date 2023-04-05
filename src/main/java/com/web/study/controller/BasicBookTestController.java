package com.web.study.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ErrorResponseDto;
import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.BookTestDto;

@RestController
public class BasicBookTestController {
	private List<Book> books = new ArrayList<>();

	public BasicBookTestController() {
		books.add(new Book(1, "a", "2013.01.01", "추천없음"));
		books.add(new Book(2, "b", "2014.02.02", "20대가 읽어야할 100가지 책"));
		books.add(new Book(3, "c", "2015.03.03", "30대가 읽어야할 100가지 책"));
		books.add(new Book(4, "d", "2016.04.04", "40대가 읽어야할 100가지 책"));
	}

	@GetMapping("/book") // @RequestParam("bookname") 생략가능
	public ResponseEntity<? extends ResponseDto> read(String bookname) {

		Map<String, String> errorData = new HashMap<>();
		errorData.put(bookname, "책이 없습니다.");
		for (Book book : books) {
			if (book.getBookname().equals(bookname)) {
				Book findBook = book;
				return ResponseEntity.ok().body(DataResponseDto.of(findBook));
			}
		}
		return ResponseEntity.ok().body(ErrorResponseDto.of(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/book/{bookcode}")
	public ResponseEntity<? extends ResponseDto> codeRead(@PathVariable("bookcode") int bookcode) {

		for (Book book : books) {
			if (book.getBookcode() == bookcode) {
				Book findBook = book;
				return ResponseEntity.ok().body(DataResponseDto.of(findBook));
			}
		}
		return ResponseEntity.ok().body(ErrorResponseDto.of(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/books")
	public ResponseEntity<? extends ResponseDto> reads(BookTestDto bookTestDto) {//int start, int end
		List<Book> findbooks = new ArrayList<>();

		int lastcode = books.size();

		if (bookTestDto.getStart() <= 0) {
			return ResponseEntity.ok().body(ErrorResponseDto.of(HttpStatus.BAD_REQUEST));
		}

		if (lastcode < bookTestDto.getEnd()) {
			return ResponseEntity.ok().body(ErrorResponseDto.of(HttpStatus.BAD_REQUEST));
		}

		for (int i = bookTestDto.getStart() - 1; i < bookTestDto.getEnd(); i++) {
			findbooks.add(books.get(i));
		}

		return ResponseEntity.ok().body(DataResponseDto.of(findbooks));
	}
	
	@PostMapping("/bookcreateform")
	public ResponseEntity<? extends ResponseDto> bookCreateForm(Book book){
		books.add(book);
		
		System.out.println(books);
		
		return ResponseEntity.created(null).body(DataResponseDto.of(book));
	}
	
	@PostMapping("/bookcreatejson")
	public ResponseEntity<? extends ResponseDto> bookCreateJson(@RequestBody Book book){
		books.add(book);
		
		System.out.println(books);
		
		return ResponseEntity.created(null).body(DataResponseDto.of(book));
	}

}
