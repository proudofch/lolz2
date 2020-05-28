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
</head>
<body><header id="header">

					<h1 id="logo"><a href="https://kr.leagueoflegends.com/ko-kr/">공식사이트</a></h1>

					<nav id="nav">
						<ul>
							<li><a href="index.jsp">Home</a></li>
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
					<li><a href="Search.Search">Search</a></li>
						
		
            	
				 <c:choose>
            	<c:when test="${sessionScope.id == null }">
		            <li><a href="Login.Lolz">Sign In</a></li>
					<li><a href="MemberAdd.Lolz" class="button primary">Sign Up</a></li>
            	</c:when>
            	<c:otherwise>
	            		<c:if test="${sessionScope.id=='admin' }">
						<li><a href="MemberList.Lolz">Member List</a></li>            	
	            		</c:if>
		            <li><a href="Logout.Lolz">Sign out</a></li>   
		        	    <c:if test="${sessionScope.id!='admin' }">      		
						<li><a href="MyPage.Lolz">My page</a></li>
						</c:if>
	            	<li><a href="FreeBoardWrite.Board" >글쓰기</a></li>
            	</c:otherwise>
            </c:choose>
			
					
					<li><a href="FreeBoardList.Board" >게시판목록</a></li>				
     
							

						</ul>
					</nav>
				</header>

</body>
</html>