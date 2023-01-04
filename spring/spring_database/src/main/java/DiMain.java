import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

import di.controller.MemberController;
import di.persistence.GoodRepository;
import domain.Good;
import persistence.GoodMapper;

public class DiMain {

	public static void main(String[] args) {
		try(GenericXmlApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml")) {
			SqlSessionFactory sqlFactory = context.getBean("sqlSessionFactory", SqlSessionFactory.class);
			System.out.println(sqlFactory);
			SqlSession session = sqlFactory.openSession();
			System.out.println(session);
			
			/* xml을 이용한 MyBatis */
			//GoodRepository repository = context.getBean(GoodRepository.class);
			/* insert
			Good good = Good.builder()
							.code(3)
							.name("우")
							.manufacture("박가네")
							.price(10000)
							.build();
			repository.insertGood(good); */
			
			/* select all 
			List<Good> list = repository.allGoods();
			for(Good good : list) {
				System.out.println(good);
			}*/
			
			/* select one 
			Good good = repository.getGood(2);
			System.out.println(good); */
			
			/* 인터페이스를 이용한 MyBatis */
			GoodMapper mapper = context.getBean(GoodMapper.class);
			List<Good> list = mapper.allGoods();
			for(Good good : list) {
				System.out.println(good);
			}

//			// Controller 가져오기 
//			MemberController controller = context.getBean("memberController", MemberController.class);
//			controller.findById("jsl");
		} catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
	
}
