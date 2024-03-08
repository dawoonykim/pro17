package sec02.ex02;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.jasper.tagplugins.jstl.core.Catch;

import oracle.jdbc.internal.ObjectDataFactory;
import oracle.sql.ARRAY;

public class MemberDAO3 {

	private DataSource dataFactory;
	private PreparedStatement pstmt;
	private Connection conn;
	// MemberDAO 객체 초기화(생성자)시에 위 정보를 불러오게 해라 - JNDI

	public MemberDAO3() {
		System.out.println("MemberDAO 객체 생성");
		try {
			// System.out.println("MemberDAO 객체 생성");
			Context ctx = new InitialContext();
			Context enContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) enContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("DB 연결을 위한 MemberDAO 객체 생성시 에러");
		}
	}

	// 회원 목록 가져옴 (그 전에 연결)
	public List<MemberVO3> listMembers() {
		List<MemberVO3> list = new ArrayList<MemberVO3>();
		try {
			conn = dataFactory.getConnection();
			String sql = "select * from T_MEMBER order by joindate asc";
			// ResultSet rs=pstmt.executeQuery();
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);

			while (rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				System.out.println(id + " " + pwd + " " + name + " " + email + " " + joinDate);

				MemberVO3 vo = new MemberVO3();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);

				list.add(vo);
			}

			rs.close();
		} catch (Exception e) {
			System.out.println("SQL 실행시 에러");
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public void addMember(MemberVO3 memberVO) {
		try {
			conn = dataFactory.getConnection();
			String id = memberVO.getId();
			String pwd = memberVO.getPwd();
			String name = memberVO.getName();
			String email = memberVO.getEmail();

			String query = "insert into t_member(id,pwd,name,email) VALUES(?,?,?,?)";
			System.out.println("회원 추가 sql문: " + query);

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			System.out.println(id + " " + pwd + " " + name + " " + email);

			// int num = pstmt.executeUpdate();
			// System.out.println(num + "명이 추가됨");

			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("회원 추가시 에러");
		} finally {

			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void delMember(String id) {
		System.out.println("삭제하고자 하는 id: " + id);
		try {
			conn = dataFactory.getConnection();
			String query = "delete from t_member" + " where id=?";
			System.out.println("preparedStatement: " + query);

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);

			pstmt.executeUpdate();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MemberVO3 findMember(String _id) {
		System.out.println("수정할 id: " + _id);
		MemberVO3 memInfo = null;
		try {
			conn = dataFactory.getConnection();
			String query = "select * from t_member where id=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, _id);
			// System.out.println(query);
			ResultSet rs = pstmt.executeQuery();

			rs.next();
			String id = rs.getString("id");
			String pwd = rs.getString("pwd");
			String name = rs.getString("name");
			String email = rs.getString("email");
			Date joinDate = rs.getDate("joinDate");

			memInfo = new MemberVO3(id, pwd, name, email, joinDate);
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memInfo;
	}

	public void modMember(MemberVO3 vo3) {

		System.out.println("수정할 객체: " + vo3.toString());
		String id = vo3.getId();
		String pwd = vo3.getPwd();
		String name = vo3.getName();
		String email = vo3.getEmail();

		try {
			conn = dataFactory.getConnection();
			String query = "update t_member set pwd=?, name=?, email=? where id=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pwd);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			pstmt.setString(4, id);

			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
