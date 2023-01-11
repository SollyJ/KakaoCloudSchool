package com.kakao.guestbook.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class PageRequestDTO {
	private int page;	// 페이지 번호
	private int size;	// 페이지당 데이터 개수

	// 매개변수없는 생성자를 직접 만들어줬으므로 @NoArgsConstructor는 없어도된다.
	public PageRequestDTO() {
		// 기본값
		this.page = 1;
		this.size = 10;
	}

	// 정렬 적용 하는 메서드
	public Pageable getPageable(Sort sort){
		return PageRequest.of(page-1, size, sort);
	}
}
