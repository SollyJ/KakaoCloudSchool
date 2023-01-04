import org.springframework.context.support.GenericXmlApplicationContext;

import di.controller.MemberController;

public class DiMain {

	public static void main(String[] args) {
		try(GenericXmlApplicationContext context = 
			new GenericXmlApplicationContext(
				"applicationContext.xml")) {
			//Controller 가져오기
			MemberController controller = 
				context.getBean("memberController", 
					MemberController.class);
			controller.findById("1");
			
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

	}

}
