package com.kakao.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/sample/")
public class SampleController {
	// 모두 허용
	@GetMapping("/all")
	public void exAll(){
		log.info("모두 허용...");
	}

	// 멤버만 허용
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/member")
	public void exMember(){
		log.info("멤버만 허용...");
	}

	// 관리자만 허용
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin")
	public void exAdmin(){
		log.info("관리자만 허용...");
	}
}
