package com.sollyj.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sollyj.domain.MemberVO;

public class MemberDAO {
	// 싱글톤 패턴을 위한 코드
	private MemberDAO() {
	}

	private static MemberDAO dao;

	public static MemberDAO getInstance() {
		if (dao == null) {
			dao = new MemberDAO();
		}
		return dao;
	}

	// 데이터베이스 접속을 위한 드라이버 로드 코드
	static {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("드라이버 로드 성공");
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	// 데이터베이스 사용을 위한 속성
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet result;

	// 데이터베이스 연결
	{
		try {
			connection = DriverManager.getConnection("jdbc:mariadb//localhost:3306/mydb", "root", "center");
			System.out.println("데이터베이스 접속 성공");
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	// 로그인 처리를 위한 메서드
	// 아이디와 비밀번호를 받아서 처리한 후 회원정보를 리턴
	public MemberVO login(String mid, String mpw) {
		MemberVO vo = null;
		try {
			// 수행할 SQL작성
			String sql = "select * from tbl_member where mid=? and mpw=?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, mid);    // 1번 파라미터에 현재 함수 매개변수인 mid를 넣고
			ps.setString(2, mpw);    // 2번 파라미터에 현재 함수 매개변수인 mpw를 넣는다.

			// SQL실행
			result = ps.executeQuery();

			if (result.next()) {
				vo = new MemberVO();
				vo.setMid(result.getString("mid"));
				vo.setMname(result.getString("mname"));
			}
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}

		return vo;
	}

	// uuid를 가지고 로그인 하는 메서드
	public MemberVO login(String uuid) {
		MemberVO vo = null;
		try {
			String sql = "select * from tbl_member where uuid=?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, uuid);

			result = ps.executeQuery();

			if (result.next()) {
				vo = new MemberVO();
				vo.setUuid(result.getString("mUuid"));
			}
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}

		return vo;
	}

	// uuid를 업데이트 하는 메서드: 로그인 성공 시 호출되는 메서드
	public void updateUUID(String mid, String uuid) {
		try {
			String query = "update tbl_member set uuid=? where mid=?";
			ps = connection.prepareStatement(query);
			ps.setString(1, uuid);
			ps.setString(2, mid);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
}