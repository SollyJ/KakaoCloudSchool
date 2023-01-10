package com.kakao.springbootjpa;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import com.kakao.springbootjpa.domain.Memo;
import com.kakao.springbootjpa.persistence.MemoRepository;

@SpringBootTest
public class RepositoryTest {
	@Autowired
	MemoRepository memoRepository;

	//주입 확인
	@Test
	public void testDependency(){
		System.out.println("주입 여부:" + memoRepository.getClass().getName());
	}

	// 삽입 테스트
	@Test
	public void testInsert() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			Memo memo = Memo.builder().memoText("Sample.." + i).build();
			memoRepository.save(memo);
		});
	}

	// 전체데이터 조회 테스트
	@Test
	public void testAll() {
		List<Memo> list = memoRepository.findAll();
		for(Memo memo : list) {
			System.out.println(memo);
		}
	}

	// 조회 테스트
	@Test
	public void getById() {
		Optional<Memo> result = memoRepository.findById(100L);	// 기본키를 가지고 조회하면 없거나 1개의 데이터 리턴하므로 Optional을 붙여준다.
		if(result.isPresent()) {
			System.out.println(result.get());
		} else {
			System.out.println("데이터가 존재하지 않습니다.");
		}
	}

	// 수정 테스트
	@Test
	public void testUpdate() {
		Memo memo = Memo.builder().mno(100L).memoText("Update Text").build();
		System.out.println(memoRepository.save(memo));
	}

	// 삭제 테스트
	@Test
	public void testDelete() {
		Long mno = 100L;
		memoRepository.deleteById(mno);
	}

	// 페이징 테스트
	@Test
	public void testPageDefault() {
		// 1페이지부터 10개
		Pageable pageable = PageRequest.of(0,10);
		Page<Memo> result = memoRepository.findAll(pageable);
		System.out.println(result);
		System.out.println ("--------------------------------------------------------------- ");
		System.out.println("Total Pages: "+result.getTotalPages()); //전체 페이지 개수
		System.out.println("Total Count: "+result.getTotalElements()); //전체 데이터 개수
		System.out.println("Page Number: "+result. getNumber ()); //현재 페이지 번호 0부터 시작
		System.out.println("Page Size: "+result .getSize()); //페이지당 데이터 개수
		System.out.println("Has next page?:"+ result.hasNext()); //다음 페이지존재 여부
		System.out.println("First page?: "+result.isFirst()); //시작 페이지 (0) 여부
		System.out.println("---------------------------------------------------------");
		//데이터 순회
		for (Memo memo : result.getContent()) {
			System.out.println(memo);
		}
	}

	@Test
	public void testPageSort() {
		Sort sort1 = Sort.by("mno").descending();	// 정렬 정보
		Pageable pageable = PageRequest.of(0, 10, sort1);	// 1페이지부터 10페이지까지 sort1정렬대로
		Page<Memo> result = memoRepository.findAll(pageable);
		result.get().forEach(memo -> {
			System.out.println(memo);
		});
	}

	// 정렬 조건 2개를 결합해서 페이징
	@Test
	public void testSortConcat() {
		Sort sort1 = Sort.by("mno").descending();
		Sort sort2 = Sort.by("memoText").ascending();
		Sort sortAll = sort1.and(sort2); //and를 이용한 연결
		Pageable pageable = PageRequest.of(0, 10, sortAll); //결합된 정렬 조건 사용

		Page<Memo> result = memoRepository.findAll(pageable);
		result.get().forEach(memo -> {
			System.out.println(memo);
		});
	}

	// 쿼리메서드 테스트
	@Test
	public void queryMethod1() {
		List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(30L, 40L);
		for(Memo memo : list) {
			System.out.println(memo);
		}
	}

	@Test
	public void queryMethod2() {
		Pageable pageable = PageRequest.of(1, 5);
		Page<Memo> result = memoRepository.findByMnoBetween(0L, 40L, pageable);
		for(Memo memo : result.getContent()) {
			System.out.println(memo);
		}
	}

	// 쿼리메서드 - 삭제메서드 테스트
	// 트랜잭션을 하지 않으면 예외가 발생
	// 트랜잭션을 하면 자동 commit되지 않으므로 commit을 호출해야 실제 작업이 수행된다.
	@Test
	@Transactional
	@Commit
	public void deleteMnoTest() {
		memoRepository.deleteByMnoLessThan(100L);
	}

	@Test
	public void updateMemoQuery() {
		System.out.println(memoRepository.updateMemoText(11L, "문자열"));
	}

}
