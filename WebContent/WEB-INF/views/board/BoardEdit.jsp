<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>글 수정하기</title>
	
	<style type="text/css">
		h2 {
			margin-top: 100px;
			padding-top: 100px;
		}
		
		#summernote_wrapper {
			background-color: white;
			opacity: 0.95;
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
				<h2>Edit</h2>
				<p>글 수정하기...입니다...</p>
			</header>
			
			
			<!-- request 객체에 boarddata 넘어온 상태 -->
			
			<form name="board_edit" id="board_write" action="FreeBoardEditOk.Board" method="post" enctype="multipart/form-data">
			
				<c:set var="boarddata" value="${requestScope.boarddata}"></c:set>
				
				<input type="hidden" id="id" name="id" value="${boarddata.id}">
				<input type="hidden" id="boardnum" name="boardnum" value="${boarddata.boardnum}">
				
				제목<br> 
				<input type="text" name="boardtitle" id="boardtitle" placeholder="제목을 입력하세요" value="${boarddata.boardtitle}">
				<br>
				내용<br>
				<div id="summernote_wrapper">
					<textarea rows="10" cols="60" name="boardcontent" id="summernote">${boarddata.boardcontent}</textarea>
				</div>
				<br>
				첨부된 파일&nbsp;&nbsp;[${boarddata.boardfile}] <br>
				새 첨부 파일<br>
				<input type="file" id="boardfile" name="boardfile">
				<br>
				<input type="button" class="button primary small" value="수정하기" onclick="check();">
				<input type="reset" class="button primary small" value="다시 쓰기"> <!-- reset 안됨 손보기!-->
				<ul>
					<li><a href="FreeBoardList.Board">목록으로</a></li> <!-- cp, ps값 추적하기 / 파라미터 붙여줘야 돼....?currentpage=-->
					<li><a href="#">top</a></li>
				</ul>
				
			</form>
		</div> <!-- container end -->
	</div> <!-- wrapper end -->
</body>

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
<script>
	console.log($('#summernote'));

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
</script>
</html>