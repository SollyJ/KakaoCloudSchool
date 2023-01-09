package com.kakao.thymeleaf.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SampleVO {
	private Long sno;
	private String first;
	private String last;
	private LocalDateTime regTime;
}
