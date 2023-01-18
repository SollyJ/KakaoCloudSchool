package com.kakao.moviereview.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kakao.moviereview.domain.Movie;
import com.kakao.moviereview.domain.MovieImage;
import com.kakao.moviereview.dto.MovieDTO;
import com.kakao.moviereview.persistence.MovieImageRepository;
import com.kakao.moviereview.persistence.MovieRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
	private final MovieRepository movieRepository;
	private final MovieImageRepository movieImageRepository;

	@Override
	@Transactional
	public Long register(MovieDTO movieDTO) {
		System.out.println("movieDTO:" + movieDTO);
		Map<String, Object> entityMap = dtoToEntity(movieDTO);
		Movie movie = (Movie)entityMap.get("movie");
		System.out.println("movie:" + movie);
		List<MovieImage> movieImageList = (List<MovieImage>)entityMap.get("imgList");
		System.out.println("movieImageList:" + movieImageList);
		movieRepository.save(movie);
		movieImageList.forEach(movieImage -> {
			movieImageRepository.save(movieImage);
		});
		return movie.getMno();
	}
}
