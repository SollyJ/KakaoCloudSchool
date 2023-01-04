package di.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.Good;

//@Repository
public class GoodRepository {
	//@Autowired
	private SqlSession sqlSession;
	
	public int insertGood(Good good) {
		return sqlSession.insert("insertGood", good);
	}

	public List<Good> allGoods() {
		return sqlSession.selectList("allGoods");
	}
	
	public Good getGood(int code) {
		return sqlSession.selectOne("getGood", code);
	}
}
