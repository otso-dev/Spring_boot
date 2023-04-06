package com.web.study.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ErrorResponseDto;
import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.UserAdditionDto;

class UserStore {
	public static Map<Integer, UserAdditionDto> userMap = new HashMap<>();
}

@RestController
public class UserRestController {
	@PostMapping("/api/user/addition")
	public ResponseEntity<? extends ResponseDto> addUser(@RequestBody UserAdditionDto userAdditionDto) {
		Map<Integer, UserAdditionDto> userMap = UserStore.userMap;
		int maxKey = 0;
		if (!userMap.keySet().isEmpty()) {
			maxKey = Collections.max(userMap.keySet());

		}
		userMap.put(maxKey + 1, userAdditionDto);
		System.out.println(userMap);
		return ResponseEntity.ok().body(DataResponseDto.of(userAdditionDto));
		// created()안에 url을 넣어서 데이터생성에 성공됐으면 넣은 url주소로 이동한다.
	}

	@GetMapping("/api/user/{id}")
	public ResponseEntity<? extends ResponseDto> getUser(@PathVariable int id) {
		// userMap에서 해당 id를 가진 객체를 응답
		// 만약에 해당 id가 존재하지 않으면 errorResponse로 응답. errorMessage = 존재하지 않는 id입니다.
		// Map<String, UserAdditionDto> userMap = UserStore.userMap;
		if (!UserStore.userMap.containsKey(id)) {
			try {
				throw new RuntimeException("존재하지 않는 id입니다.");
			} catch (Exception e) {
				return ResponseEntity.badRequest().body(ErrorResponseDto.of(HttpStatus.BAD_REQUEST, e));
			}
		}
		return ResponseEntity.ok().body(DataResponseDto.of(UserStore.userMap.get(id)));
	}

	@GetMapping("/api/users")
	public ResponseEntity<? extends ResponseDto> getUsers() {

		return ResponseEntity.ok().body(DataResponseDto.of(UserStore.userMap.values()));
	}
}



/*
 *Set<Entry<Integer, UserAdditionDto>> set = UserStore.userMap.entrySet();
List<Entry<Integer, UserAdditionDto>> list = new ArrayList<>();
list.addAll(set);
Collection<Entry<Integer, UserAdditionDto>> list2 = UserStore.userMap.entrySet().stream()
		.collect(Collectors.toCollection(ArrayList::new));

Comparator<Integer> comparator = (o1, o2) -> {
	if (o1 > o2) {
		return 1;
	} else if (o1 == o2) {
		return 0;
	} else {
		return -1;
	}
};
Collections.sort(null, null);
 
 */
