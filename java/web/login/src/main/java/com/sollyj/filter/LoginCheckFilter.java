package com.sollyj.filter;

import java.io.IOException;

import com.sollyj.dto.MemberDTO;
import com.sollyj.service.MemberService;
import com.sollyj.service.MemberServiceImpl;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class LoginCheckFilter extends HttpFilter implements Filter {
	private MemberService memberService;

	public LoginCheckFilter() {
		super();
		memberService = MemberServiceImpl.getInstance();
	}

	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
		ServletException,
		IOException {
		// request와 response 형변환
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;

		// Login 요청이 오면
		if (req.getRequestURI().equals("/JavaWebMaven/login")) {
			// 자동로그인을 한적이 있는지 쿠키 읽기
			Cookie[] cookies = req.getCookies();
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("remember-me")) {   // 자동로그인을 한적이 있다면 (기한은 이틀)
					String uuid = cookie.getValue();
					MemberDTO dto = memberService.login(uuid);
					req.getSession().setAttribute("loginInfo", dto);
					res.sendRedirect("./");   // 메인 페이지로 리다이렉트
					return;
				}
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
	}
}
