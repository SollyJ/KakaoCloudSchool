package com.sollyj.service;

import com.sollyj.domain.MemberVO;
import com.sollyj.dto.MemberDTO;
import com.sollyj.persistance.MemberDAO;

public class MemberServiceImpl implements MemberService {
	private MemberDAO memberDAO;    // 서비스에는 DAO가 주입돼야함

	// 싱글톤패턴
	private MemberServiceImpl() {
		memberDAO = MemberDAO.getInstance();
	}

	private static MemberService service;

	public static MemberService getInstance() {
		if (service == null) {
			service = new MemberServiceImpl();
		}
		return service;
	}

	@Override
	public MemberDTO login(String mid, String mpw, String uuid) {
		MemberDTO dto = null;
		MemberVO vo = memberDAO.login(mid, mpw);
		// vo를 dto로 변환
		if (vo != null) {
			dto = new MemberDTO();
			dto.setMid(vo.getMid());
			dto.setMname(vo.getMname());
			// UUID 업데이트
			memberDAO.updateUUID(mid, uuid);
		}
		return dto;
	}

	@Override
	public MemberDTO login(String uuid) {
		MemberDTO dto = null;
		MemberVO vo = memberDAO.login(uuid);

		if (vo != null) {
			dto = new MemberDTO();
			dto.setMid(vo.getMid());
			dto.setMname(vo.getMname());
		}

		return dto;
	}
}
