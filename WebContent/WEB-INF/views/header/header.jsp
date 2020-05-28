<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" href="assets/css/main.css" />
	<noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
	<title>Header</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script type="text/javascript">
		$(function() {
			
			$('#nav > ul:nth-child(3)').mouseover(function(){
				$(this).addClass('opener active');
			});
			
			$('#nav > ul:nth-child(3)').mouseout(function(){
				$(this).removeClass('opener active');
				$(this).addClass('opener');
			});
			
		});
	</script>
	<style type="text/css">
		
		body {
			margin-top: 80px;
			/* margin-bottom: 120px; */
		}
		
	</style>
	
</head>

<body>
	<header id="header">
		<h1 id="logo">
			<a href="https://kr.leagueoflegends.com/ko-kr/">공식사이트로</a>
		</h1>
		<nav id="nav">
			<ul>
				<li><a href="index.jsp">HOME</a></li>
				<!-- 
				<li>
					<a href="#">Layouts</a>
					<ul>
						<li><a href="left-sidebar.html">Left Sidebar</a></li>
						<li><a href="right-sidebar.html">Right Sidebar</a></li>
						<li><a href="no-sidebar.html">No Sidebar</a></li>
						<li>
							<a href="#">Submenu</a>
							<ul>
								<li><a href="#">Option 1</a></li>
								<li><a href="#">Option 2</a></li>
								<li><a href="#">Option 3</a></li>
								<li><a href="#">Option 4</a></li>
							</ul>
						</li>
					</ul>
				</li>
				<li><a href="elements.html">Elements</a></li>
				-->
				<li><a href="Search.Search">소환사검색</a></li>
				<li>
					<a href="#">게시판</a>
					<ul>
						<li><a href="FreeBoardList.Board">자유게시판</a></li>
						<li><a href="#">공략게시판</a></li>
					</ul>
				</li>
            	
				<c:choose>
	            	<c:when test="${sessionScope.id == null }">
			            <li><a href="Login.Lolz">로그인</a></li>
						<li><a href="MemberAdd.Lolz" class="button primary">회원가입</a></li>
	            	</c:when>
	            	<c:otherwise>
						<li><a href="MyPage.Lolz">마이페이지</a></li>
	            		<c:if test="${sessionScope.id=='admin'}">
						<li><a href="MemberList.Lolz" class="button">회원목록</a></li>
            			</c:if>
			            <li><a href="Logout.Lolz">로그아웃</a></li>         		
	            	</c:otherwise>
            	</c:choose>
            	
			</ul>
		</nav>
	</header>
</body>
</html>