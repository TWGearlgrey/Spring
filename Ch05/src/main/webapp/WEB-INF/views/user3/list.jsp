<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>user3::register</title>
	</head>
	<style>
		table * {
			text-align: center;
		}
	</style>
	<body>
		<h3>user3 목록</h3>
		
		<a href="/Ch05/index">메인</a>
		<a href="/Ch05/user3/register">등록</a>
		
		<table border="1">
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>휴대폰</th>
				<th>나이</th>
				<th>관리</th>
			</tr>
			
			<c:forEach var="u" items="${users}">
			<tr>
				<td>${u.uid}</td>
				<td>${u.name}</td>
				<td>${u.hp}</td>
				<td>${u.age}</td>
				<td>
					<a href="/Ch05/user3/modify?uid=${u.uid}">수정</a>
					<a href="/Ch05/user3/delete?uid=${u.uid}">삭제</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</body>
</html>