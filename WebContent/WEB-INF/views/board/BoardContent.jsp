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
				<h2>${dto.boardtitle}</h2>
				<h5>작성자 || ${dto.id}&nbsp;&nbsp;&nbsp;&nbsp;작성일 || ${dto.boarddate}
					&nbsp;&nbsp;&nbsp;&nbsp;조회수 || ${dto.boardhit}&nbsp;&nbsp;&nbsp;&nbsp;첨부파일 || ${dto.boardfile}</h5>
				<!-- 파일 다운로드 될 수 있게 만들기 -->
				<hr>
				<%-- ${dto.boardcontent} --%>
				<c:if test="${dto.boardcontent != null}">
					${fn:replace(dto.boardcontent, replaceChar, "<br/>")}
				</c:if>
				
				<hr>
				<b>댓글</b>
				<br>
				<textarea rows="2" cols="5"></textarea>
				<br>
				<input type="button" class="button primary small" value="등록" onclick="check();">
				<input type="reset" class="button primary small" value="다시 쓰기"> <!-- 왜 동작 안 한담...? -->
				<br>
				
				<ul>
					<li><a href="FreeBoardList.Board">목록으로</a></li> <!-- 글 있던 페이지로 가지 않는 듯? 확인 -->
					<li><a href="#">top</a></li>
					<li><a href="FreeBoardEdit.Board?boardnum=${dto.boardnum}">수정하기</a></li>
					<li><a href="#">삭제</a></li>
					<li><a href="#">답글</a></li>
				</ul>
			</div>
			
		</div>
	</div>
</body>

<script type="text/javascript">

	function check() {
		
	}
	
</script>	

</html>