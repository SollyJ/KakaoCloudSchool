package com.kakao.moviereview.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kakao.moviereview.domain.Movie;
import com.kakao.moviereview.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
