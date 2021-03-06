<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<% pageContext.setAttribute("replaceChar", "\n"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>공략게시판 글 상세보기</title>
	
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
		
		#btns_wrapper {
			text-align: right;
		}
		
		#btns {
			display: inline-block;
		}
		
	
		
	</style>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

</head>

<jsp:include page="/WEB-INF/views/header/header.jsp"></jsp:include>


<body>
	<div id="main" class="wrapper style1">
		<div class="container">
			<header class="major" style="display: none;">
				<h2>상세보기</h2>
				<p>header는 단 맞추기 용도</p>
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
				
				<c:if test="${sessionScope.id != null}">
				
					<h4 id="reply_h4">댓글</h4>
					
					<div id="replybox"></div>
					
					<!-- 댓글 폼 -->
					<br>
					<form name="reply" id="reply" method="POST">
						작성자&nbsp;&nbsp;${sessionScope.id}<br>
						<input type="hidden" name="boardnum" id="boardnum" value="${dto.boardnum}">
						<input type="hidden" name="id" id="id" value="${sessionScope.id}">
						<textarea rows="2" cols="5" id="comment" name="comment"></textarea>
						<br>
						<input type="button" class="button primary small" value="댓글 등록" id="writecom">
						<input type="reset" class="button primary small" value="다시 쓰기">
					</form>
					<!-- 댓글 폼 끝 -->
					
					<hr>
	
					<div id="btns_wrapper">
						<div id="btns">
							<c:if test="${sessionScope.id == dto.id}">
								<input type="button" class="button primary small" value="수정" onclick="location.href='KnowHowBoardEdit.Board?boardnum=${dto.boardnum}'">
								<input type="button" class="button primary small" value="삭제" id="delete" onclick="location.href='KnowHowBoardDelete.Board?boardnum=${dto.boardnum}'">
							</c:if>
								
							<input type="button" class="button small" value="답글" onclick="location.href='KnowHowBoardRewrite.Board?boardnum=${dto.boardnum}&cp=${requestScope.cp}&ps=${requestScope.ps}'">
				</c:if>
						<input type="button" class="button small" value="목록으로" onclick="location.href='KnowHowBoardList.Board?cp=${requestScope.cp}&ps=${requestScope.ps}'">
						<input type="button" class="button small" value="TOP" onclick="location.href='#'">
					</div>
				</div>
				
			</div>
		</div>
	</div>
</body>

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript">


	//모든 요소 load 후 댓글 목록과 댓글 쓰기 폼 불러오기
	$(function() {
		
		getReplyList();
		insertReply();
		
	});
	
	
	//댓글 목록 가져오기
	function getReplyList() {
		
		$.ajax({
			url:"GetReplyList",
			datatype: "json",
			data: { boardnum:'${dto.boardnum}'},
			success: function(data) {
				
				var html = "";
				console.log("data: "+data);
				
				$.each(JSON.parse(data), function(index, element) {
					
					
					html += "<form action='ReplyDelete' method='POST'>";
					html += "<div id='reply_id'><b>";
					html += element.id;
					html += "</b></div>";
					html += "<div id='reply_comment'>";
					html += element.replycont;
					html += "</div>";
					html += "<div id='reply_date'><h6>";
					html += element.replydate;
					html += "</h6></div>";
					html += "<input type='hidden' name='replynum' id='replynum' value='";
					html += element.replynum;
					html += "'> <input type='submit' value='삭제' class='button small' onclick='deleteReply(this.form)'>";
					html += "</form>";
					
				});
				
				$('#replybox').append(html);
				
			}
		});
		
	}
	
	
	//댓글 쓰기
	function insertReply() {
		$('#writecom').click(function(){
			
			if(!reply.comment.value) {
				swal('댓글 내용을 입력하세요!');
				reply.comment.focus();
				return false;
			}
			
			$.ajax ({
				
				url:"ReplyInsert",
				datatype:"json",
				data: { boardnum:'${dto.boardnum}',
						id: '${dto.id}',
						comment: $('#comment').val()
					  },
				success: function(data) {
						$('#replybox').empty();
						getReplyList();
						$('#comment').val("");
						
					}
					
			});
			
			return false;
			
		});
	}
	
	
	//댓글 삭제
	
	function deleteReply(form) {
		$(form).on("submit", function() {
			
			var data = $(this).serialize();
			
			$.ajax({
				url: "ReplyDelete",
				data: data,
				success: function(data) {
					$('#replybox').empty();
					getReplyList();
				}
			});
			return false;
		});
	}
	
	
</script>	

</html>