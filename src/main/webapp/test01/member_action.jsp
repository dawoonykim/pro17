<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"
	import="java.util.*,sec01.ex02.*"%>
<%
request.setCharacterEncoding("utf-8");
MemberDAO dao = new MemberDAO();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:useBean id="m" class="sec01.ex02.MemberVO" />
<jsp:setProperty property="*" name="m" />
<%
dao.addMember(m);
List<MemberVO> memberLists = dao.listMembers();
request.setAttribute("memberLists", memberLists);
%>
<title>Insert title here</title>
</head>
<body>
	<jsp:forward page="/mem"></jsp:forward>
</body>
</html>