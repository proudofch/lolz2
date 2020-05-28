<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>자유게시판</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>

<jsp:include page="/WEB-INF/views/header/header.jsp"></jsp:include>

<body>
	<div id="main" class="wrapper style1">
		<div class="container">
			<header class="major">
				<h2>Board</h2>
				<p>여기는 자유게시판입니다</p>
			</header>
			
			<div class="table-wrapper">
				<table>
					<thead>
						<tr>
							<th width="10%">글번호</th>
							<th width="50%">글제목</th>
							<th width="10%">글쓴이</th>
							<th width="10%">작성일</th>
							<th width="10%">조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="boardlist" value="${requestScope.boardlist}" />
						
						<!-- 게시물이 한 건도 없을 경우의 처리 (추가) -->
						
						<c:forEach var="list" items="${boardlist}">
						<tr>
							<td>${list.boardnum}</td>
							<td>
								<c:choose>
									<c:when test="${list.boardnotice == 'Y'}">
										[공지사항]&nbsp;&nbsp;
										<a href="FreeBoardRead.Board?boardnum=${list.boardnum}&cp=${requestScope.currentpage}&ps=${requestScope.pagesize}">
										<c:choose>
											<c:when test="${list.boardtitle != null && fn:length(list.boardtitle) > 30}">
			                            		${fn:substring(list.boardtitle, 0, 30)}...
			                        		</c:when>
											<c:otherwise>
			                                  	${list.boardtitle}
			                               </c:otherwise>
										</c:choose>
										</a>
									</c:when>
									
									<c:otherwise>
										<a href="FreeBoardRead.Board?boardnum=${list.boardnum}&cp=${requestScope.currentpage}&ps=${requestScope.pagesize}">
										<c:choose>
											<c:when test="${list.boardtitle != null && fn:length(list.boardtitle) > 30}">
			                            		${fn:substring(list.boardtitle, 0, 30)}...
			                        		</c:when>
											<c:otherwise>
			                                  	${list.boardtitle}
			                               </c:otherwise>
										</c:choose>
										</a>
									</c:otherwise>
								</c:choose>
							</td>
							<!--<td><a href="">${list.boardtitle}</a></td> <i class="fas fa-caret-right"></i>  i 앞에 &nbsp; 붙이기 -->
							<td>${list.id}</td>
							<td>${list.boarddate}</td>
							<td>${list.boardhit}</td>
						</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="5">
								<c:if test="${currentpage > 1}">
									<a href="FreeBoardList.Board?cp=${currentpage-1}&ps=${pagesize}">이전</a>
								</c:if>

								<c:forEach var="i" begin="1" end="${pagecount}" step="1">
									<c:choose>
										<c:when test="${currentpage == i}">
											<font color="tomato">[${i}]</font>
										</c:when>
										<c:otherwise>
											<a href="FreeBoardList.Board?cp=${i}&ps=${pagesize}">[${i}]</a>
										</c:otherwise>
									</c:choose>
								</c:forEach>

								<c:if test="${currentpage < pagecount}">
									<a href="FreeBoardList.Board?cp=${currentpage+1}&ps=${pagesize}">다음</a>
								</c:if>
								
							</td>
						</tr>
					</tfoot>
				</table>
			</div> <!-- table-wrapper end -->
		</div> <!-- container end -->
	</div> <!-- wrapper end -->
</body>
</html>