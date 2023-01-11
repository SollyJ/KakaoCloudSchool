package com.kakao.guestbook.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Data;

// 다른 종류의 entity에서도 사용할 수 있도록 generic 사용
@Data
public class PageResponseDTO<DTO, EN> {
	private List<DTO> dtoList;    // 데이터 목록
	private int totalPage;    // 전체 페이지 개수
	private int page;    // 현재 페이지
	private int size;
	private int start, end;    // 시작 페이지 번호, 마지막 페이지 번호
	private boolean prev, next;
	private List<Integer> pageList;    // 페이지 번호 목록

	// Paging결과를 가지고 추가한 항목들을 계산해주는 메서드
	private void makePageList(Pageable pageable) {
		this.page = pageable.getPageNumber() + 1;    // JPA는 페이지 번호가 0부터 시작하므로 +1
		this.size = pageable.getPageSize();

		// 임시로 마지막 페이지 번호를 생성
		int tempEnd = (int)(Math.ceil(page / 10.0)) * 10;

		start = tempEnd - 9;
		prev = start > 1;
		end = totalPage > tempEnd ? tempEnd : totalPage;    // 마지막 페이지 번호 계산
		next = tempEnd < totalPage;
		// start~end까지를 스트림으로 만들어서 list로 변환
		pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
	}

	// Page에 함수를 적용해서 List로 변환해주는 메서드
	public PageResponseDTO(Page<EN> result, Function<EN, DTO> fn) {    // <page 단위의 entity, 데이터 변환을 위한 메서드>
		// EN(Entity)과 DTO(클래스타입)을 변환해주는 함수를 매개변수로 받아서 DTO타입의 List로 변환해주는 것
		dtoList = result.stream().map(fn).collect(
			Collectors.toList());
		totalPage = result.getTotalPages();
		makePageList(result.getPageable());
	}
}
