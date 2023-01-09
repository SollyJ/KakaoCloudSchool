package com.kakao.springbootbasic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParamDTO {
	private String name;
	private String email;
	private String organization;
}
