package com.sollyj.service;

import com.sollyj.dto.MemberDTO;

public interface MemberService {
	// 로그인 처리를 위한 메서드
	public MemberDTO login(String mid, String mpw, String uuid);

	// 자동 로그인 처리를 위한 메서드
	public MemberDTO login(String uuid);
}
