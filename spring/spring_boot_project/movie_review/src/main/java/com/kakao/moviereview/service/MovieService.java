package com.kakao.moviereview.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.kakao.moviereview.domain.Movie;
import com.kakao.moviereview.domain.MovieImage;
import com.kakao.moviereview.dto.MovieDTO;
import com.kakao.moviereview.dto.MovieImageDTO;

public interface MovieService {
	Long register(MovieDTO movieDTO);	// 데이터 삽입 메서드

	default Map<String, Object> dtoToEntity(MovieDTO movieDTO) {
		Map<String, Object> entityMap = new HashMap<>();

		Movie movie = Movie.builder()
			.mno(movieDTO.getMno())
			.title(movieDTO.getTitle())
			.build();
		entityMap.put("movie", movie);

		List<MovieImageDTO> imageDTOList = movieDTO.getImageDTOList();

		//MovielmageDT0 처리
        if(imageDTOList != null && imageDTOList.size() > 0 ) {
			List<MovieImage> movielmageList = imageDTOList.stream(). map(movieImageDTO ->{
				MovieImage movieimage = MovieImage.builder()
					.path(movieImageDTO.getPath())
					.imgName(movieImageDTO.getImgName())
					.uuid(movieImageDTO.getUuid())
					.movie(movie)
					.build();
				return movieimage;
			}).collect(Collectors.toList());
			entityMap.put("imgList", movielmageList);
		}
        return entityMap;
	}

}
