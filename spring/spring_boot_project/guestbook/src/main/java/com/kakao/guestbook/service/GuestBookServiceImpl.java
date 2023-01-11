package com.kakao.guestbook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kakao.guestbook.domain.GuestBook;
import com.kakao.guestbook.dto.GuestBookDTO;
import com.kakao.guestbook.dto.PageRequestDTO;
import com.kakao.guestbook.dto.PageResponseDTO;
import com.kakao.guestbook.repository.GuestBookRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class GuestBookServiceImpl implements GuestBookService{
	private final GuestBookRepository guestBookRepository;

	@Override
	public Long register(GuestBookDTO dto) {
		// 파라미터가 제대로 넘어오는지 확인
		log.info("삽입 데이터: " + dto.toString());
		GuestBook entity = dtoToEntity(dto);	// entity로 변형
		guestBookRepository.save(entity);
		return entity.getGno();
	}

	@Override
	public PageResponseDTO<GuestBookDTO, GuestBook> getList(PageRequestDTO requestDTO) {
		Pageable pageable = requestDTO.getPageable(
			Sort.by("gno").descending()
		);

		Page<GuestBook> result = guestBookRepository.findAll(pageable);
		Function<GuestBook, GuestBookDTO> fn = (entity -> entityToDto(entity));	// 데이터 목록을 받아서 순회하면서 제공된 메서드가 리턴하는 목록을 변경해주는 람다
		return new PageResponseDTO<>(result, fn);
	}
}
