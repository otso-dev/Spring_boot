package com.web.study.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.dto.DataResponseDto;
import com.web.study.dto.ResponseDto;
import com.web.study.dto.request.auth.LoginReqDto;
import com.web.study.dto.request.auth.RegisteUserReqDto;
import com.web.study.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	
	@PostMapping("/auth/register")
	public ResponseEntity<? extends ResponseDto> registe(@RequestBody RegisteUserReqDto registeUserReqDto){
		authService.duplicatedUsername(registeUserReqDto);
		authService.registeUser(registeUserReqDto);
		return ResponseEntity.ok().body(ResponseDto.ofDefalut());
	}
	
	@PostMapping("/auth/login")
	public ResponseEntity<? extends ResponseDto> login(@RequestBody LoginReqDto loginReqDto){
		
		return ResponseEntity.ok().body(DataResponseDto.of(authService.login(loginReqDto)));
	}
	
}
/* JWT를 이용한 회원가입과 로그인 전체 흐름
 * 
 * 1. 회원가입
 * 		- 회원가입할 정보를 입력
 * 		- 해당 정보로 회원가입 요청을 보낸다.
 * 		- 회원가입 정보(password 암호화**)를 DB에 저장한다.
 * 2. 로그인
 * 		- 로그인할 정보를 입력(username, password)
 * 		- 해당 정보로 로그인 요청을 보낸다.
 * 		- AuthenticationManager에게 username, password를 전달
 * 		- AuthenticationManager가 인증을 시작한다.
 * 		- UserDetailsService의 loadUserByUseranem(String username)이 호출된다.
 * 			UserDetails를 리턴받아서 Authentication객체를 생성하기 위해
 * 			이 때 해당 username으로 DB에서 조회된 UserEntity가 없으면 등록되지 않은 회원이다.(예외처리) ->여기까지가 security 작업
 * 			Authentication객체가 생성됐다면 로그인이 성공한 것.
 * 		- Authentication객체를 JWT로 변환하는 작업을 해야함.
 * 		- 변환된 JWT를 클라이언트에게 응답
 * 		- 클라이언트는 JWT토큰을 로컬스토리지나 쿠키에 저장.
 * 3. 요청시 토큰 인증
 * 		- 요청 Header에 Bearer 방식으로 JWT 토큰을 전달한다.
 * 		- Spring security에서 인증이 필요한 요청들은 JwtAuthenticationFilter를 통해 인증절차를 진행한다.
 * 			이때 인증의 최종 목표는 SecurityContextHolder객체의 Context에 Authetication등록을 하는 것이다.
 * 			Authentication 객체가 등록이 되어 있으면 인증이 된 것이다.
 * 		- JwtAuthentication에서 요청 Header에 들어있는 Authorization의 JWT 토큰을 추출한다.
 * 		- JWT토큰을 검증한다.
 * 			이 때 검증에 실패하여 Exception이 생성되면 AuthenticationEntryPoint가 실행되면서 401 응답을 하게 된다.
 * 		- JWT토큰 검증이 완료되면 JWT 토큰에서 Claims를 추출한다.
 * 		- Claims에서 username과 Authorities를 추출하여 Authentication 객체를 생성한다.
 * 		- 생성된 Authentication객체를 SecurityContext에 등록한다.
 * 		- 등록이 완료되면 다음 doChain이 호출된다.
 */