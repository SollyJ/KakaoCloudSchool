package com.kakao.thymeleaf.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kakao.thymeleaf.domain.SampleVO;

@Controller
public class PageController {
	@GetMapping("/")
	public String main(Model model) {
		Map<String, Object> map = new HashMap<>();
		map.put("Language", "Java");
		map.put("IDE", "IntelliJ");
		map.put("BuildTool", "Gradle");
		model.addAttribute("map", map);
		List<String> list = new ArrayList<>();
		list.add("BackEnd Developer");
		list.add("FrontEnd Developer");
		list.add("DBA");
		list.add("Operator");
		list.add("BigData");
		list.add("DevOps");
		list.add("AI");
		list.add("MLOps");
		model.addAttribute("list", list);
		return "main";
	}

	@GetMapping("/ex1")
	public void ex1() {
		System.out.println("ex1");
	}

	@GetMapping("/ex2")
	public void ex2(Model model) {
		List<SampleVO> list = IntStream.range(1, 20).asLongStream().mapToObj(
			i -> {
				SampleVO vo = SampleVO.builder()
					.sno(i)
					.first("First.." + i)
					.last("Last.." + i)
					.regTime((LocalDateTime.now()))
					.build();
				return vo;
			}).collect(Collectors.toList());
		model.addAttribute("list", list);
	}
}
