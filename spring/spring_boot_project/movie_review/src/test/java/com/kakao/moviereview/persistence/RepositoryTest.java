package com.kakao.moviereview.persistence;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.kakao.moviereview.domain.Member;
import com.kakao.moviereview.domain.Movie;
import com.kakao.moviereview.domain.MovieImage;
import com.kakao.moviereview.domain.Review;

@SpringBootTest
class RepositoryTest {
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private MovieImageRepository movieImageRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private ReviewRepository reviewRepository;

	@Test
	@Transactional
	@Commit
	public void insertMovie() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			Movie movie = Movie.builder().title("Movie..." + i).build();
			movieRepository.save(movie);

			int count = (int)(Math.random() * 5) + 1;
			for (int j = 0; j < count; j++) {
				MovieImage movieImage = MovieImage.builder()
					.uuid(UUID.randomUUID().toString())
					.movie(movie)
					.imgName("test" + j + ".jpg")
					.build();
				movieImageRepository.save(movieImage);
			}
		});
	}

	@Test
	public void insertMembers() {
		IntStream.rangeClosed(1,100).forEach(i -> {
			Member member = Member.builder()
				.email("r"+i +"@gmail.com")
				.pw("1111")
				.nickname("reviewer"+i).build();
			memberRepository.save(member);
		});
	}

	@Test
	public void insertMoviewReviews() {
		//200개의 리뷰를 등록
		IntStream.rangeClosed(1,200).forEach(i -> {
			//영화 번호
			Long mno = (long)(Math.random()*100) + 1;
			//리뷰어 번호
			Long mid  =  ((long)(Math.random()*100) + 1 );
			Member member = Member.builder().mid(mid).build();
			Review movieReview = Review.builder()
				.member(member)
				.movie(Movie.builder().mno(mno).build())
				.grade((int)(Math.random()* 5) + 1)
				.text("이 영화에 대한 느낌..."+i)
				.build();
			reviewRepository.save(movieReview);
		});
	}

	@Test
	public void joinTest() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mno"));
		Page<Object[]> result = movieRepository.getList(pageable);
		for(Object[] objects : result.getContent()) {
			System.out.println(Arrays.toString(objects));
		}
	}

}