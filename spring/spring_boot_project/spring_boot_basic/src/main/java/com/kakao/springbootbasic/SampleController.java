package com.kakao.springbootbasic;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
	// hello라는 요청을 Get 방식으로 요청한 경우
	@GetMapping("/hello")
	public String[] hello() {
		return new String[] {"STS", "IntelliJ"};
	}
}
