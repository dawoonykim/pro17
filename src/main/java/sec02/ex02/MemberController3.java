package sec02.ex02;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/mem3/*")
public class MemberController3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MemberDAO3 dao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("MemberController 객체 생성");

		dao = new MemberDAO3();
	}

	@Override
	public void destroy() {
		System.out.println("MemberController 객체 소멸");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("get 방식");
		doHandle(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("post 방식");
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// URL Path정보에 따른 분기
		String action = request.getPathInfo();
		System.out.println("액션: " + action);

		String nextPage = null; // 컨트롤에서 지정한 다음 페이지
		List<MemberVO3> memberLists = null;
		if (action == null || action.equals("/listMembers03.do") || action.equals("/")) {
			// 회원 가져옴
			memberLists = dao.listMembers();
			System.out.println(memberLists);

			// 화면에 회원 목록을 뿌려야 함, 뿌리는 페이지로 셋팅해서 보냄, 해당 request에 키, 밸류로 설정해서
			// 보내야함(RequestDispatcher)
			request.setAttribute("memberLists", memberLists);

			nextPage = "/test03/listMembers03.jsp";
		} else if (action.equals("/addMember.do")) {

			System.out.println("회원 추가 메소드");

			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			System.out.println("id" + id);

			MemberVO3 vo3 = new MemberVO3(id, pwd, name, email);
			dao.addMember(vo3);
			memberLists = dao.listMembers();
			request.setAttribute("membersLists", memberLists);
			nextPage = "/test03/listMembers03.jsp";

		} else if (action.equals("/memberForm03.do")) {

			nextPage = "/test03/memberForm03.jsp";

		} else if (action.equals("/modMemberForm.do")) {

			String id = request.getParameter("id");

			//request.setAttribute("id",id);
			 MemberVO3 memInfo=dao.findMember(id);
			request.setAttribute("memInfo", memInfo);

			nextPage = "/test03/modMemberForm.jsp";

		} else if (action.equals("/modMember.do")) {

			System.out.println("회원 수정 하기");
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");

			MemberVO3 vo3 = new MemberVO3(id, pwd, name, email);

			dao.modMember(vo3);
//			request.setAttribute("msg", "modified");
			System.out.println("회원 수정 완료");
			memberLists = dao.listMembers();
			System.out.println("수정 후 화면 목록");
			request.setAttribute("membersLists", memberLists);
			nextPage = "/test03/listMembers03.jsp";

		} else if (action.equals("/delMember.do")) {

			String id = request.getParameter("id");

			dao.delMember(id);
			memberLists = dao.listMembers();
			request.setAttribute("membersLists", memberLists);
			// request.setAttribute("msg", "deleted");
			// request.setAttribute("msg", "modified");
			nextPage = "/mem3/listMembers03.do";

		} else {
			memberLists = dao.listMembers();
			request.setAttribute("memberLists", memberLists);
			nextPage = "/test03/listMembers03.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}

}
