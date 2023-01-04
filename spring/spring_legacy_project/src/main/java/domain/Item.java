package domain;

import lombok.Data;

@Data //getter, setter, equals, hashCode, toString 생성
public class Item {
	private int itemId;
	private String itemName;
	private int price;
	private String description;
	private String pictureUrl;
}
