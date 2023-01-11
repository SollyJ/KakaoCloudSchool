package com.kakao.guestbook.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.kakao.guestbook.domain.GuestBook;
import com.kakao.guestbook.domain.QGuestBook;
import com.kakao.guestbook.repository.GuestBookRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@SpringBootTest
public class RepositoryTest {
	@Autowired
	private GuestBookRepository guestBookRepository;

	// 데이터 삽입 테스트
	@Test
	public void insertDummies() {
		IntStream.rangeClosed(1, 300).forEach(i -> {
			GuestBook guestBook = GuestBook.builder()
				.title("제목..." + i)
				.content("내용..." + i)
				.writer("user" + (i % 10))
				.build();
			System.out.println(guestBookRepository.save(guestBook));
		});
	}

	// 데이터 수정 테스트
	@Test
	public void updateDummies() {
		GuestBook guestBook = GuestBook.builder()
			.gno(1001L)
			.title("제목 변경")
			.content("내용 변경")
			.writer("리리")
			.build();
		System.out.println(guestBookRepository.save(guestBook));
	}

	// 조건 1개인 검색 테스트
	@Test
	public void testQuery1() {
		// 10개씩 첫번째 페이지의 데이터를 조회
		// modDate의 내림차순 정렬
		Pageable pageable = PageRequest.of(0, 10, Sort.by("modDate").descending());

		// querydsl 수행
		QGuestBook qGuestBook = QGuestBook.guestBook;

		// title에 1이 포함된 조건을 생성
		String keyword = "1";
		BooleanBuilder builder = new BooleanBuilder();
		BooleanExpression expression = qGuestBook.title.contains(keyword);
		builder.and(expression);

		Page<GuestBook> result = guestBookRepository.findAll(builder, pageable);
		for (GuestBook gb : result.getContent()) {
			System.out.println(gb);
		}
	}

	// 다중 항목 검색 테스트
	@Test
	public void testQuery2() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

		QGuestBook qGuestBook = QGuestBook.guestBook;

		String keyword = "1";
		BooleanBuilder builder = new BooleanBuilder();

		BooleanExpression exTitle = qGuestBook.title.contains(keyword);    // 조건1: title에 특정한 키워드가 있는지 확인
		BooleanExpression exContent = qGuestBook.content.contains(keyword); // 조건2: content에 특정한 키워드가 있는지 확인

		BooleanExpression exAll = exTitle.or(exContent); // 두개의 조건을 or로 결합

		builder.and(exAll); //2-------
		// gno가 100보다 작은 데이터만 검색
		builder.and(qGuestBook.gno.lt(1100L));

		Page<GuestBook> result = guestBookRepository.findAll(builder, pageable);
		for (GuestBook gb : result.getContent()) {
			System.out.println(gb);
		}
	}
}
