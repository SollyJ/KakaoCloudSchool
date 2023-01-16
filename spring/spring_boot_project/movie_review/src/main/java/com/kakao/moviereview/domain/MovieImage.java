package com.kakao.moviereview.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "movie")	// toString을 할 때 movie는 제외
@Embeddable	// 부모 테이블을 만들 때 이 속성의 값을 포함시켜 생성
public class MovieImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long inum;
	private String uuid;
	private String imgName;

	// 하나의 디렉토리에 너무 많은 파일이 저장되지 않도록 업로드한 날짜 별로 파일을 기록하기 위한 디렉토리 이름
	private String path;
	@ManyToOne(fetch = FetchType.LAZY)
	private Movie movie;
}
