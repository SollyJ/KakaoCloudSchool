package com.kakao.school.service;

import java.util.List;

import javax.swing.text.html.parser.Entity;

import com.kakao.school.domain.ItemEntity;
import com.kakao.school.dto.ItemDTO;

public interface ItemService {
	// 전체 데이터를 가져오는 메서드
	public List<ItemDTO> allItem();
	
	public default ItemEntity dtoToEntity(ItemDTO dto) {
		ItemEntity entity = ItemEntity.builder()
						.itemid(dto.getItemid())
						.itemname(dto.getItemname())
						.description(dto.getDescription())
						.pictureurl(dto.getPictureurl())
						.price(dto.getPrice())
						.build();
		
		return entity;
	}
	
	public default ItemDTO entityToDTO(ItemEntity entity) { 
		ItemDTO dto = ItemDTO.builder()
				.itemid(entity.getItemid())
				.itemname(entity.getItemname())
				.description(entity.getDescription())
				.pictureurl(entity.getPictureurl())
				.price(entity.getPrice())
				.build();
		
		return dto;
	}
}
