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
	<style type="text/css">
	
		.pagination {
		  	display: inline-block;
		}
		
		.pagination a {
		  	background-color: #EAEAEA;
		  	color: black;
		  	float: left;
		  	padding: 8px 16px;
		  	text-decoration: none;
		  	border: 1px solid #ddd;
		}
		
		.pagination a.active {
		  	background-color: #e44c65;
		  	color: white;
		  	border: 1px solid #e44c65;
		}
		
		.pagination a:hover:not(.active) {background-color: #ddd;}
		
		.pagination a:first-child {
		  	border-top-left-radius: 10px;
		  	border-bottom-left-radius: 10px;
		}
		
		.pagination a:last-child {
		  	border-top-right-radius: 10px;
		  	border-bottom-right-radius: 10px;
		}
		
		#pagination_wrapper, table, thead, tbody, tr {
			text-align: center;
		}
		
		.left {
			text-align: left;
		}
		
		#btn {
			float: right;
		}
		
	
	</style>
	<script type="text/javascript">
		
		$(function() {
			$('tr > th').css("text-align", "center");
		});
		
	</script>
</head>

<jsp:include page="/WEB-INF/views/header/header.jsp"></jsp:include>

<body>
	<div id="main" class="wrapper style1">
		<div class="container">
			<header class="major">
				<h2>자유게시판</h2>
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
						
						<c:if test="${boardlist == null}">
							<tr>
								<td colspan="5">게시물이 없습니다!</td> <!-- 동작하는 코드인지 테스트 안 해봄 -->
							</tr>
						</c:if>
						
						<c:forEach var="list" items="${boardlist}">
						<tr>
							<td>${list.boardnum}</td>
							<td class="left">
								<c:choose>
									<c:when test="${list.boardnotice == 'Y'}">
										<i class="far fa-flag"></i>&nbsp;공지&nbsp;&nbsp;
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
				  <c:forEach var="i" begin="1" end="${list.boarddepth}" step="1">
                    &nbsp;&nbsp;&nbsp;
                   </c:forEach>  
                   <c:if test="${list.boarddepth>0}">
				<img src='images/re.png' />
					</c:if>  
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
					<tfoot></tfoot>
				</table>
				
				<div id="pagination_wrapper">
					<div class="pagination">
						<c:if test="${currentpage > 1}">
							<a href="FreeBoardList.Board?cp=${currentpage-1}&ps=${pagesize}"><i class="fas fa-angle-double-left"></i></a>
						</c:if>
						
						<c:forEach var="i" begin="1" end="${pagecount}" step="1">
							<c:choose>
								<c:when test="${currentpage == i}">
									<a href="#" class="active">${i}</a>
								</c:when>
								<c:otherwise>
									<a href="FreeBoardList.Board?cp=${i}&ps=${pagesize}">${i}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						
						<c:if test="${currentpage < pagecount}">
							<a href="FreeBoardList.Board?cp=${currentpage+1}&ps=${pagesize}"><i class="fas fa-angle-double-right"></i></a>
						</c:if>
					</div>
				</div>
				
				<c:if test="${sessionScope.id != null }">
					<a href="FreeBoardWrite.Board" class="button primary" id="btn">글쓰기</a>
				</c:if>
				
			</div> <!-- table-wrapper end -->
		</div> <!-- container end -->
	</div> <!-- wrapper end -->
</body>
</html>