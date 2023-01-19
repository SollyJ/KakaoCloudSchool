package com.kakao.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordTests {
	@Autowired
	PasswordEncoder passwordEncoder;

	@Test
	public void testEncode() {
		String password = "1114";
		String enPw1 = passwordEncoder.encode(password);
		System.out.println("PW: " + password);
		System.out.println("Encoded PW: " + enPw1);

		String enPw2 = passwordEncoder.encode(password);	// 똑같은 패스워드를 인코딩할때마다 결과 값이 다름
		System.out.println("Encoded PW: " + enPw2);

		Boolean result = passwordEncoder.matches(password, enPw2);	// matches로 비밀번호가 같은지 판별
		System.out.println(result);
	}
}
