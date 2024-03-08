package sec01.ex02;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/mem/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MemberDAO dao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("MemberController 객체 생성");

		dao = new MemberDAO();
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

		if (action == null || action.equals("/listMembers.do") || action.equals("/")) {
			// 회원 가져옴
			List<MemberVO> memberLists = dao.listMembers();
			System.out.println(memberLists);

			// 화면에 회원 목록을 뿌려야 함, 뿌리는 페이지로 셋팅해서 보냄, 해당 request에 키, 밸류로 설정해서
			// 보내야함(RequestDispatcher)
			request.setAttribute("memberLists", memberLists);

			nextPage = "/test01/listMembers.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}

}
