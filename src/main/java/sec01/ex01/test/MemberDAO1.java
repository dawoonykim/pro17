package sec01.ex01.test;

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

public class MemberDAO1 {

	private DataSource dataFactory;
	private PreparedStatement pstmt;
	private Connection conn;
	// MemberDAO 객체 초기화(생성자)시에 위 정보를 불러오게 해라 - JNDI

	public MemberDAO1() {
		System.out.println("MemberDAO 객체 생성");
		try {
			System.out.println("MemberDAO 객체 생성");
			Context ctx = new InitialContext();
			Context enContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) enContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("DB 연결을 위한 MemberDAO 객체 생성시 에러");
		}
	}
	
	public List selectAllArticles() {
		List articleList=new ArrayList();
		
		try {
			conn=dataFactory.getConnection();
			String query="select level, articleNO, parentNO, title, content, id, writeDate"
					+" from t_board"
					+" START WITH parentNO=0"
					+" CONNECT BY PRIOR articleNO=parentNO"
					+" ORDER SIBLINGS BY articleNO DESC";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				int level=rs.getInt("level");
				int articleNO=rs.getInt("articleNO");
				int parentNO=rs.getInt("parentNO");
				String title=rs.getString("title");
				String content=rs.getString("content");
				String id=rs.getString("id");
				Date writeDate=rs.getDate("writeDate");
				
				MemberVO1 vo=new MemberVO1();
				vo.setLevel(level);
				vo.setArticleNO(articleNO);
				vo.setParentNO(parentNO);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setId(id);
				vo.setWriteDate(writeDate);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return articleList;
	}

	// 회원 목록 가져옴 (그 전에 연결)
	public List<MemberVO1> listMembers() {
		List<MemberVO1> list = new ArrayList<MemberVO1>();
		try {
			conn = dataFactory.getConnection();
			String sql = "select * from T_MEMBER";
//			ResultSet rs=pstmt.executeQuery();
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);

			while (rs.next()) {

				int level=rs.getInt("level");
				int articleNO=rs.getInt("articleNO");
				int parentNO=rs.getInt("parentNO");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String imageFileName = rs.getString("imageFileName");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");
				
				MemberVO1 vo = new MemberVO1();
				vo.setLevel(level);
				vo.setArticleNO(articleNO);
				vo.setParentNO(parentNO);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setId(id);
				vo.setWriteDate(writeDate);

				list.add(vo);
			}

			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("SQL 실행시 에러");
		}
		return list;
	}


}
