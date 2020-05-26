<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<% pageContext.setAttribute("replaceChar", "\n"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>글 상세보기</title>
	
	<style type="text/css">

		h2 {
			margin-top: 100px;
			padding-top: 100px;
		}
		
	</style>
</head>

<jsp:include page="/WEB-INF/views/header/header.jsp"></jsp:include>

<body>
	<div id="main" class="wrapper style1">
		<div class="container">
			<header class="major" style="display: none;">
				<h2>Board</h2>
				<p>여기는 상세보기입니다... 일단 안 보이는 중...</p>
			</header>
			<c:set var="dto" value="${requestScope.dto}"></c:set>
			<div>
				[게시판 이름을 넣으려면 join을 해야 할까요]
				<h2>${dto.boardtitle}</h2>
				<h5>작성자 || ${dto.id}&nbsp;&nbsp;&nbsp;&nbsp;작성일 || ${dto.boarddate}&nbsp;&nbsp;&nbsp;&nbsp;조회수 || ${dto.boardhit}</h5>
				<hr>
				<%-- ${dto.boardcontent} --%>
				<c:if test="${dto.boardcontent != null}">
					${fn:replace(dto.boardcontent, replaceChar, "<br/>")}
				</c:if>
				
				<hr>
				댓글<br>
				<textarea rows="5" cols="5"></textarea>
				등록버튼<br>
				목록으로 / top<br>
				수정하기 / 삭제 / 답글
			</div>
			
		</div>
	</div>
</body>
</html>