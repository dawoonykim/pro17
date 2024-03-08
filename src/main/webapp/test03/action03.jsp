<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"
	import="java.util.*,sec02.ex02.*"%>
<%
request.setCharacterEncoding("utf-8");
MemberDAO3 dao = new MemberDAO3();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:useBean id="m" class="sec02.ex02.MemberVO3" />
<jsp:setProperty property="*" name="m" />
<%
dao.addMember(m);
List<MemberVO3> memberLists = dao.listMembers();
request.setAttribute("memberLists", memberLists);
%>
<title>Insert title here</title>
</head>
<body>
	<jsp:forward page="/mem3"></jsp:forward>
</body>
</html>