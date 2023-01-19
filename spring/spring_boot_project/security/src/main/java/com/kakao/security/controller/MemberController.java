package com.kakao.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
	@GetMapping("/login")
	public void login(String error, String logout) {
		log.info("error: " + error);	// 로그인 실패
		log.info("logout: " + logout);	// 로그아웃한 후 로그인으로 이동했을때의 파라미터
	}
}
