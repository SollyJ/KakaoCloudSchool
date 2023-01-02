import org.junit.jupiter.api.Test;

import com.sollyj.persistance.MemberDAO;

public class TestCases {
	@Test
	public void daoTest() {
		MemberDAO dao = MemberDAO.getInstance();
		System.out.println(dao);

		System.out.println(dao.login("jsl", "1111"));    // id, pw가 일치하므로 데이터 출력
		System.out.println(dao.login("lsj", "1111"));    // id가 틀리므로 null
		System.out.println(dao.login("jsl", "2222"));    // pw가 틀리므로 null
	}
}
