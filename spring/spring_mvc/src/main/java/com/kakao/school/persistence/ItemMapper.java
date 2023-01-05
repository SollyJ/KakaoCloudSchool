package com.kakao.school.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.kakao.school.domain.ItemEntity;

@Repository
public interface ItemMapper {
	@Select("select * from item")
	public List<ItemEntity> allItem();
}
