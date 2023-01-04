package persistence;

import domain.Item;

public class ItemRepository {
	// 다른 패키지에서는 인스턴스 생성을 못하도록 생성자의 접근 지정자를 package로 설정
	ItemRepository(){}

	public Item get() {
		Item item  = new Item();
		item.setItemId(1);
		item.setItemName("망고");
		item.setDescription("가장 좋아하는 과일");
		item.setPrice(3000);
		item.setPictureUrl("mango.jpg");
		return item;
	}
}
