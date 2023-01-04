import java.sql.Connection;
import java.sql.DriverManager;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DBTest {
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void testConnection() {
		try {
			// 연결 
			Connection con = dataSource.getConnection();
			System.out.println(con);
		} catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
}
