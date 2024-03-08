<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록 창</title>
</head>
<%-- ${memberLists } --%>
<body>
	<!-- 회원 목록 페이지 -->
	<table align="center" border="1">
		<tr align="center" bgcolor="green">
			<td width="7%">아이디</td>
			<td width="7%">비밀번호</td>
			<td width="7%">이름</td>
			<td width="7%">이메일</td>
			<td width="7%">가입일</td>
			<td width="7%">수정</td>
		</tr>


		<c:forEach var="member" items="${memberLists }">
			<tr align="center">
				<td>${member.id }</td>
				<td>${member.pwd }</td>
				<td>${member.name }</td>
				<td>${member.email }</td>
				<td>${member.joinDate }</td>
				<td><a href="http://localhost:8080/pro17/mem2/addMember.do?">수정</a></td>
			</tr>
		</c:forEach>
	</table>

	<a href="http://localhost:8080/pro17/test02/memberForm02.jsp">회원추가</a>


</body>
</html>