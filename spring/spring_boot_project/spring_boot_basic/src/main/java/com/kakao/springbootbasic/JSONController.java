package com.kakao.springbootbasic;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kakao.springbootbasic.dto.ParamDTO;

@RestController
@RequestMapping("/api/v1/rest-api")
public class JSONController {
	// 로깅 객체 생성
	private final Logger LOGGER = LoggerFactory.getLogger(JSONController.class);

	// 일반적인 GET 수행
	@GetMapping("/hello")
	public String getHello() {
		LOGGER.info("hello 요청이 왔습니다.");
		return "Get Hello";
	}

	// 파라미터가 1개인 GET 수행
	@GetMapping("product/{num}")
	public String getNum(@PathVariable("num") int num) {
		return num + "";
	}

	@GetMapping("/param")
	public String getParam(ParamDTO paramDTO){
		return paramDTO.getName() + "(" + paramDTO.getEmail() + "): " + paramDTO.getOrganization();
	}

	@PostMapping("/param")
	public String getPostParam(@RequestBody ParamDTO paramDTO){
		return paramDTO.toString();
	}

	@PutMapping("/param")
	public ParamDTO getPutParam(@RequestBody ParamDTO paramDTO){
		return paramDTO;
	}

	@PutMapping("/param2")
	public ResponseEntity<ParamDTO> getPutParam2(@RequestBody ParamDTO paramDTO){
		return ResponseEntity.status(HttpStatus.ACCEPTED)	// 상태코드를 설정해서 결과를 리턴하는 것이 가능
			.body(paramDTO);
	}

	@DeleteMapping("/product/{num}")
	public String DeleteParamNum(@PathVariable("num") int num){
		return num + "";
	}
}
