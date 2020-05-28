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
		
		hr.dot {
			border: none;
			border: 1px dashed #ffffff;
			opacity: 0.4;
			margin: 20px 0px; /* 0 > 40px */
		}
		
		#replybox, #reply {
			margin: 0 40px;
		}
		
		#reply_h4 {
			margin-left: 40px;
		}
		
	
		
	</style>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
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
				<h4 id="reply_h4">댓글</h4>
				<!-- <div class="replybox">
					<div id="reply_id"><b>아이디 aaaa1111 proudofch</b></div>
					<div id="reply_comment">코멘트내용이 들어갑니다</div>
					<div id="reply_date"><h6>20200527</h6></div>
				</div>
				
				<hr class="dot">
				<div class="replybox">
					<div id="reply_id"><b>hyeonachae</b></div>
					<div id="reply_comment">코멘트내용이 들어갑니다</div>
					<div id="reply_date"><h6>2020-05-27 11:41:23</h6></div>
				</div>
				<hr class="dot"> -->
				
				<div id="replybox"></div>
				<!-- 여기부터 댓글 -->
				
				
				
				<br>
				<form name="reply" id="reply" method="POST">
					${sessionScope.id}<br>
					<input type="hidden" name="boardnum" id="boardnum" value="${dto.boardnum}">
					<input type="hidden" name="id" id="id" value="${sessionScope.id}"> <!-- 여기를 뭘로 잡아줘야 댓글 입력에 반영이 될까... 원래는 dto.id였음  -->
					<textarea rows="2" cols="5" id="comment" name="comment"></textarea>
					<br>
					<input type="button" class="button primary small" value="댓글 등록" id="writecom">
					<input type="reset" class="button primary small" value="다시 쓰기">
				</form>
				
				
				
				<!-- 댓글 끝 -->
				
				
				
				<hr>

				<input type="button" class="button primary small" value="수정" onclick="location.href='FreeBoardEdit.Board?boardnum=${dto.boardnum}'">
				<input type="button" class="button primary small" value="목록으로" onclick="location.href='FreeBoardList.Board'">
				<!-- 목록으로에 파라미터 주기 !!! -->
				<input type="button" class="button primary small" value="삭제" id="delete" onclick="location.href='FreeBoardDelete.Board?boardnum=${dto.boardnum}'">
			
				
				
				<ul>
					<li><a href="#">top</a></li>
					<li><a href="#">답글</a></li>
				</ul>
			</div>
			
		</div>
	</div>
</body>


<script type="text/javascript">


	$.ajax({
		url:"GetReplyList",
		datatype: "json",
		data: { boardnum:'${dto.boardnum}'},
		success: function(data) {
			
			var html = "";
			
			$.each(JSON.parse(data), function(index, element) {
				html += "<div id='reply_id'><b>";
				html += element.id;
				html += "</b></div><div id='reply_comment'>";
				html += element.replycont;
				html += "</div><div id='reply_date'><h6>";
				html += element.replydate;
				html += "</h6></div><hr class='dot'>"
			});
			
			$('#replybox').append(html);
		}
	});



	
		$('#writecom').click(function(){
		
			if(!reply.comment.value) {
				swal('댓글 내용을 입력하세요!');
				reply.comment.focus();
				return false;
			}
			
			
			//댓글 쓰기
			$.ajax ({
				url:"ReplyInsert",
				datatype:"json",
				data: { boardnum:'${dto.boardnum}',
						id: '${dto.id}',
						comment: $('#comment').val()
					  },
				success: function(data) {
						console.log(data);
						
						$('#replybox').empty();
						var html = "";
						$.each(JSON.parse(data), function(index, element) {
							html += "<div id='reply_id'><b>";
							html += element.id;
							html += "</b></div><div id='reply_comment'>";
							html += element.replycont;
							html += "</div><div id='reply_date'><h6>";
							html += element.replydate;
							html += "</h6></div><hr class='dot'>"
						});
						
						$('#replybox').append(html);
						$('#comment').val("");
						
					}
					
			});
		});
	
	
	
	/* ----마지막에----- SWEET ALERT 참고(뭔가 있음)
	$(function(){
		/* var result = confirm("정말 삭제하시겠습니까?");
		if(result) {
			
		} */
	/*	$('#delete').click(function() {
			let result = confirm("정말 삭제하시겠습니까?");
			if(result) {
				location.href('index.jsp');
			}
		});
	});
	*/
	
	
	
</script>	

</html>