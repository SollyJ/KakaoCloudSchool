package com.sollyj.controller;

import java.io.IOException;
import java.util.UUID;

import com.sollyj.dto.MemberDTO;
import com.sollyj.service.MemberService;
import com.sollyj.service.MemberServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService;    // 컨트롤러는 서비스를 주입받아야함

	public LoginController() {
		super();
		memberService = MemberServiceImpl.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
		ServletException,
		IOException {
		// webapp디렉토리의 member/login.jsp로 포워딩
		request.getRequestDispatcher("/member/login.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
		ServletException,
		IOException {
		// 로그인 처리
		// 파라미터 가져오기
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		// 자동 로그인 읽기
		String auto = request.getParameter("auto");
		// 자바에서 uuid를 생성하는 방법
		String uuid;
		if (auto == null) {
			uuid = null;
		} else {
			uuid = UUID.randomUUID().toString();
		}

		// 서비스 메서드 호출
		MemberDTO dto = memberService.login(mid, mpw, uuid);

		// 결과를 가지고 분기
		HttpSession session = request.getSession();
		if (dto == null) {    // 로그인 실패
			session.invalidate();
			// 로그인 페이지로 되돌아 가기
			response.sendRedirect("login?error=error");    // 로그인 실패했다는 것을 알려주기 위해서 error라는 파라미터를 넘어줌
		} else {    // 로그인 성공
			session.setAttribute("loginInfo", dto);    // 로그인 정보 저장 후
			// 만약 uuid가 있으면 쿠키에 저장(자동 로그인)
			if (uuid != null) {
				Cookie rememberCookie = new Cookie("remember-me", uuid);
				rememberCookie.setMaxAge(60 * 60 * 24 * 2);    // 2일 유효기간
				rememberCookie.setPath("/");
				response.addCookie(rememberCookie);

			}
			response.sendRedirect("./");   // 메인으로 리다이렉트
		}
	}
}
