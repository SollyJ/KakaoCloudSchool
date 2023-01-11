package com.kakao.guestbook.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kakao.guestbook.domain.GuestBook;
import com.kakao.guestbook.dto.GuestBookDTO;
import com.kakao.guestbook.dto.PageRequestDTO;
import com.kakao.guestbook.dto.PageResponseDTO;

@SpringBootTest
class ServiceTest {
	@Autowired
	private GuestBookService guestBookService;

	@Test
	public void testRegister() {
		GuestBookDTO dto = GuestBookDTO.builder()
			.title("샘플제목")
			.content("샘플내용")
			.writer("리리")
			.build();
		System.out.println(guestBookService.register(dto));
	}

	@Test
	public void testList(){
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
		PageResponseDTO<GuestBookDTO, GuestBook> resultDTO = guestBookService.getList(pageRequestDTO);
		for (GuestBookDTO guestbookDTO : resultDTO.getDtoList()) {
			System.out.println(guestbookDTO);
		}
	}

	@Test
	public void testListInformation() {
	}
}