package com.kakao.security.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class CustomUserDetailService implements UserDetailsService {
	private PasswordEncoder passwordEncoder;

	// 생성자
	public CustomUserDetailService() {
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	// 아이디를 입력하고 로그인 요청을 하면 아이디에 해당하는 데이터를 찾아오는 메서드
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername: " + username);

		// 로그인에 성공한 경우
		UserDetails userDetails = User.builder()
			.username("sollyj")	// id
			.password(passwordEncoder.encode("1114"))	// password
			.authorities("ROLE_USER")
			.build();

		return userDetails;
	}
}