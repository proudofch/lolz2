<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 목록 조회</title>
</head>
<jsp:include page="/WEB-INF/views/header/header.jsp"></jsp:include>
<body>
<div id="main" class="wrapper style1">
   <div class="container">
     	<header class="major">
				<h2>회원 목록 조회</h2>		
		</header>
		
		<c:set var="memberlist" value="${requestScope.Memberlist}"></c:set>
		<table>
			<thead>
				<tr>
					<th>아이디</th>
			 		<th>비밀번호</th> 
			 		<th>이메일</th> 
			 		<th>생일</th> 
			 		<th>소환사 ID</th> 
			 	</tr>
			</thead>
			<tbody>
				<c:forEach var="member" items="${memberlist}">
				<tr>
					<td>${member.id}</td>
					<td>${member.pwd}</td>
					<td>${member.email}</td>
					<td>${member.bd}</td>
					<td>${member.summonerId}</td>
					<td><a href="MemberDelete.Lolz?id=${member.id}">삭제</a></td>
				</tr>				
				</c:forEach>
			</tbody>
		</table>
		
		
		
	</div>
</div>
</body>
</html>




