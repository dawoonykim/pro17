<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"
	import="java.util.*,sec02.ex01.*"%>
<%
request.setCharacterEncoding("utf-8");
MemberDAO dao = new MemberDAO();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:useBean id="m" class="sec02.ex01.MemberVO" />
<jsp:setProperty property="*" name="m" />
<%
dao.addMember(m);
List<MemberVO> memberLists = dao.listMembers();
request.setAttribute("memberLists", memberLists);
%>
<title>Insert title here</title>
</head>
<body>
	<jsp:forward page="/mem2"></jsp:forward>
</body>
</html>