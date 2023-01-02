package com.sollyj.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

@WebFilter("*.jsp")
public class LogFilter implements Filter {
	public LogFilter() {
		super();
	}

	// 필터 처음 사용될 때 호출되는 메서드
	public void init(FilterConfig config) throws ServletException {
	}

	// 필터가 파괴될 때 호출되는 메서드
	public void destroy() {
	}

	// URL에 해당하는 요청이 왔을때 호출되는 메서드
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
		ServletException,
		IOException {
		// 여기에 작성하면 요청을 처리하기 전 수행
		System.out.println("요청 처리 전");

		chain.doFilter(request, response);

		// 여기에 작성하면 요청 처리 후 수행
		System.out.println("요청 처리 후");
	}
}
