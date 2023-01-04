package di.controller;

import di.service.MemberService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// Controller는 다른 클래스에 주입되지 않기 때문에 템플릿 메서드 패턴을 사용하지 않는다.
@Controller
@RequiredArgsConstructor
public class MemberController {
	// controller는 service를 주입받는다.
	@Autowired
	private MemberService memberService;
	
	public void findById(String id) {
		System.out.println(memberService.findById(id));
	}
}
