package com.kakao.springbootjpa.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.kakao.springbootjpa.domain.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long> {   // <Entity클래스, Id속성의 자료형>
	List<Memo> findByMnoBetween(Long from, Long to);	// mno의 값이 from부터 to사이인 데이터 조회하는 메서드
	List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);	// mno의 값이 from부터 to사이인 데이터를 내림차순 정렬해서 조회하는 메서드
	Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable);	  // 페이징을 적용해서 조회하는 메서드
	void deleteByMnoLessThan(Long num);	// num보다 작은 데이터 삭제 하는 메서드
	@Transactional
	@Modifying
	// Entity 클래스명(Memo)을 적고 별칭(m)을 적는다.
	// 파라미터는 :뒤에
	@Query("update Memo m set m.memoText = :memoText where m.mno = :mno")
	public int updateMemoText(@Param("mno") long mno, @Param("memoText") String memoText);
}
