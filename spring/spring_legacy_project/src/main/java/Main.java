import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import domain.Item;
import persistence.ItemRepository;
import persistence.RepositoryFactory;

public class Main {

	public static void main(String[] args) {
		/* spring bean 생성 
		ApplicationContext context = new AnnotationConfigApplicationContext(RepositoryFactory.class);
		ItemRepository itemRepository = context.getBean("create", ItemRepository.class); 
		Item item = itemRepository.get();
		System.out.println(item); */
		
		/* 인스턴스 생성을 팩토리클래스를 이용해서 생성 
		//ItemRepository itemRepository = new ItemRepository();
		ItemRepository itemRepository = RepositoryFactory.create();	
		ItemRepository itemRepository1 = RepositoryFactory.create();	
		System.out.println(System.identityHashCode(itemRepository));	// 해쉬코드 출력 
		System.out.println(System.identityHashCode(itemRepository1));	// 스프링은 싱글톤패턴으로 생성하므로 해쉬코드는 일치 
 		Item item = itemRepository.get();
		System.out.println(item); */


		/* xml을 이용한 ApplicationContext생성 */
		try(GenericXmlApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml")) {
			ItemRepository itemRepository = context.getBean("ItemRepository", ItemRepository.class);
			Item item = itemRepository.get();
			System.out.println(item);
			context.close();
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

	}

}
