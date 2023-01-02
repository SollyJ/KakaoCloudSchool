package com.sollyj.test;

import com.sollyj.persistance.MemberDAO;

public class Sample {
	public void daoTest() {
		MemberDAO dao = MemberDAO.getInstance();
		System.out.println(dao);
	}

	// public void serviceTest() {
	// 	MemberService service = MemberServiceImpl.getInstance();
	// 	System.out.println(service.login("jsl", "1111"));
	// 	System.out.println(service.login("jsl", "2222"));
	// }
}
