package com.kakao.moviereview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kakao.moviereview.dto.MovieDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/movie")
@Log4j2
@RequiredArgsConstructor
public class MovieController {
	@GetMapping("/register")
	public void register(MovieDTO movieDTO, RedirectAttributes redirectAttributes){
		log.info();

	}
}
