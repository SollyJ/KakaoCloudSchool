package persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 팩토리클래스라는 어노테이션 
@Configuration
public class RepositoryFactory {
	// 매번 인스턴스를 생성해서 제공 
	// 인스턴스를 만들어주는 메서드 
	@Bean
	public static ItemRepository create() {
		return new ItemRepository();
	}
}
