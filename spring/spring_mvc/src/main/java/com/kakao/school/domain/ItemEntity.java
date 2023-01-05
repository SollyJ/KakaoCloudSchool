package com.kakao.school.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity {
	private int itemid;
	private String itemname;
	private int price;
	private String description;
	private String pictureurl;
}
