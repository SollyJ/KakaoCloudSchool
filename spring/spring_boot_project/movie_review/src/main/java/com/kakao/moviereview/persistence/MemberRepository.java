package com.kakao.moviereview.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kakao.moviereview.domain.Member;
import com.kakao.moviereview.domain.Movie;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
