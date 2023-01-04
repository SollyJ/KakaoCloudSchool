package di.persistence;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TransactionRepository {
	@Autowired
	private SimpleJdbcInsert template;
	
	@Transactional
	public void insert() {
		template.withTableName("goods");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 4);
		map.put("name", "상쾌환");
		map.put("manufacture", "큐원");
		map.put("price", 30000);
		
		template.execute(map);
		template.execute(map);	// 기본키가 겹치기 때문에 예외발생 
	}
}
