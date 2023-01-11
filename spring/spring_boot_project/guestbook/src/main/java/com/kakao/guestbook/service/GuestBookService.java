package com.kakao.guestbook.service;

import com.kakao.guestbook.domain.GuestBook;
import com.kakao.guestbook.dto.GuestBookDTO;
import com.kakao.guestbook.dto.PageRequestDTO;
import com.kakao.guestbook.dto.PageResponseDTO;

public interface GuestBookService {
	// 데이터 삽입
	public Long register(GuestBookDTO dto);

	// 목록 보기
	PageResponseDTO<GuestBookDTO, GuestBook> getList(PageRequestDTO requestDTO);

	default GuestBook dtoToEntity(GuestBookDTO dto) {
		GuestBook entity = GuestBook.builder()
			.gno(dto.getGno())
			.title(dto.getTitle())
			.content((dto.getContent()))
			.writer(dto.getWriter())
			.build();

		return entity;
	}

	default GuestBookDTO entityToDto(GuestBook entity) {
		GuestBookDTO dto = GuestBookDTO.builder()
			.gno(entity.getGno())
			.title(entity.getTitle())
			.content(entity.getContent())
			.writer(entity.getWriter())
			.build();

		return dto;
	}
}
