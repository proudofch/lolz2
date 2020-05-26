<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page</title>
</head>
<jsp:include page="/WEB-INF/views/header/header.jsp"></jsp:include>
<body>
<div id="main" class="wrapper style1">
   <div class="container">
     	<header class="major">
				<h2>마이 페이지</h2>		
		</header>
		<ul>
		<li>${sessionScope.id}님의 </li>
		<li>소환사 ${sessionScope.summonerId} 의 정보입니다.</li>
<button type="button" onclick='location.href="prvMemberEdit.Lolz?id=${sessionScope.id}"'class="button small">내 정보 수정</button>
 <button type="button" class="button small">내가 쓴 글/댓글</button>
 
		</ul>
	</div>
</div>
</body>
</html>