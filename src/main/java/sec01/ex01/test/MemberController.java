package sec01.ex01.test;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/mem.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MemberDAO1 dao;
	public void init(ServletConfig config) throws ServletException {
		System.out.println("MemberController 객체 생성");
		
		dao=new MemberDAO1();
	}

	public void destroy() {
		System.out.println("MemberController 객체 소멸");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("get 방식");
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("post 방식");
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 회원 가져옴
		List<MemberVO1> memberLists=dao.listMembers();
		System.out.println(memberLists);
		
		// 화면에 회원 목록을 뿌려야 함, 뿌리는 페이지로 셋팅해서 보냄
		request.setAttribute("memberLists", memberLists);
		RequestDispatcher dispatcher=request.getRequestDispatcher("");
		dispatcher.forward(request, response);
	}

}
