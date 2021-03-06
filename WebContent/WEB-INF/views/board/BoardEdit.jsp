<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">

	<title>자유게시판 글 수정하기</title>

	<style type="text/css">
		h2 {
			margin-top: 100px;
			padding-top: 100px;
		}
		
		#summernote_wrapper {
			background-color: white;
			opacity: 0.95;
		}
		
		#btns {
			position: relative;
			margin-top: 90px;
		}
		
		#gotolist {
			position: absolute;
			right: 93px;
		}
		
		#top {
			position: absolute;
			right: 0;
		}
		
		label {
			margin-top: 10px;
		}
		
		#checkbox {
			margin-top: 10px;
		}
		
	</style>
	
	
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript">
		
		function check() {
	
			if (!board_write.boardtitle.value) {
				swal("안 돼요!", "제목을 입력하세요!", "warning"); /* https://sweetalert.js.org/guides/ */
				board_edit.boardtitle.focus();
				return false;
			}
			
			if (!board_write.boardcontent.value) {
				swal("안 돼요!", "내용을 입력하세요!", "warning");
				board_edit.boardcontent.focus();
				return false;
			}
			
			document.board_edit.submit();
			
		}
	
	</script>
</head>

<jsp:include page="/WEB-INF/views/header/header.jsp"></jsp:include>

<body>
	<div id="main" class="wrapper style1">
		<div class="container">
			<header class="major">
				<h2>글 수정하기</h2>
			</header>
			
			<!-- request 객체에 boarddata 넘어온 상태 -->
			
			<form name="board_edit" id="board_write" action="FreeBoardEditOk.Board" method="post" enctype="multipart/form-data">
			
				<c:set var="boarddata" value="${requestScope.boarddata}"></c:set>
				<c:set var="notice" value="${boarddata.boardnotice}" />
				
				<br>
				<input type="hidden" id="id" name="id" value="${boarddata.id}">
				<input type="hidden" id="boardnum" name="boardnum" value="${boarddata.boardnum}">
				
				제목<br> 
				<input type="text" name="boardtitle" id="boardtitle" placeholder="제목을 입력하세요" value="${boarddata.boardtitle}">
				<c:if test="${sessionScope.id == 'admin'}">
					<input type="hidden" id="prvnotice" name="prvnotice" value="${notice}">
					<input type="checkbox" id="notice" name="notice" value="Y">
					<label for="notice">공지사항</label>
				</c:if>
				<br>
				내용<br>
				<div id="summernote_wrapper">
					<textarea rows="10" cols="60" name="boardcontent" id="summernote">${boarddata.boardcontent}</textarea>
				</div>
				<br>
				첨부된 파일&nbsp;&nbsp;[${boarddata.boardfile}] <br>
				<input type="hidden" id="prvboardfile" name="prvboardfile" value="${boarddata.boardfile}">
				새 첨부 파일<br>
				<input type="file" id="file" name="file">
				
				
				<div id="btns">
					<input type="button" class="button primary small" value="수정하기" onclick="check();">
					<input type="reset" class="button primary small" value="다시 쓰기"> <!-- reset 안됨 손보기!-->
					<input type="button" class="button small" id="gotolist" value="목록으로" onclick="location.href='FreeBoardList.Board'"> <!-- cp, ps... --> 
					<input type="reset" class="button small" id="top" value="TOP" onclick="location.href='#'">
				</div>
			</form>
		</div> <!-- container end -->
	</div> <!-- wrapper end -->
</body>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
<script>

	$('#summernote').summernote({
		placeholder: 'Hello stand alone ui',
		tabsize: 2,
		height: 500,
		/* focus: true, */
		toolbar: [
					['style', ['style']],
					['font', ['bold', 'underline', 'clear']],
					['color', ['color']],
					['para', ['ul', 'ol', 'paragraph']],
					['table', ['table']],
					['insert', ['link', 'picture', 'video']],
					['view', ['fullscreen', 'codeview', 'help']]
				]
	});
	
	$(function() {
		
		var flag = '${notice}';
		if(flag == "Y") {
			$("input:checkbox[id='notice']").prop("checked", true);			
		}
	
	})
</script>
</html>