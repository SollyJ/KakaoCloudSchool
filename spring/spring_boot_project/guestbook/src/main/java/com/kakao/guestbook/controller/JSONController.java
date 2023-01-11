package com.kakao.guestbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kakao.guestbook.domain.GuestBook;
import com.kakao.guestbook.dto.GuestBookDTO;
import com.kakao.guestbook.dto.PageRequestDTO;
import com.kakao.guestbook.dto.PageResponseDTO;
import com.kakao.guestbook.service.GuestBookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor    // 자동 주입 어노테이션
public class JSONController {
	private final GuestBookService guestBookService;

	@GetMapping("/guestbook/list.json")
	public PageResponseDTO<GuestBookDTO, GuestBook> list(PageRequestDTO pageRequestDTO) {
		return guestBookService.getList(pageRequestDTO);
	}
}
