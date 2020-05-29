<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>자유게시판 글쓰기</title>
    
	<style type="text/css">
		/* h2 {
			margin-top: 100px;
			padding-top: 100px;
		} */
		
		#summernote_wrapper {
			background-color: white;
			opacity: 0.95;
		}
		
		#checkbox {
			margin-top: 10px;
		}
	</style>
	
	
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript">
		
		function check() {
	
			if (!board_write.title.value) {
				swal("안 돼요!", "제목을 입력하세요!", "warning"); /* https://sweetalert.js.org/guides/ */
				board_write.title.focus();
				return false;
			}
			
			if (!board_write.content.value) {
				swal("안 돼요!", "내용을 입력하세요!", "warning");
				board_write.content.focus();
				return false;
			}
			
			document.board_write.submit();
			
		}
	
	</script>
</head>

<jsp:include page="/WEB-INF/views/header/header.jsp"></jsp:include>

<body>
	<div id="main" class="wrapper style1">
		<div class="container">
			<header class="major">
				<h2>글쓰기</h2>
			</header>
			<form name="board_write" id="board_write" action="FreeBoardWriteOk.Board" method="post" enctype="multipart/form-data">
				<input type="hidden" id="id" name="id" value="${sessionScope.id}">
				
				제목<br> 
				<input type="text" name="title" id="title" placeholder="제목을 입력하세요">
				<c:if test="${sessionScope.id == 'admin'}">
					<div id="checkbox">
						<input type="checkbox" id="notice" name="notice" value="Y">
						<label for="notice">공지사항</label>
					</div>
				</c:if>
				<br>
				내용<br>
				<div id="summernote_wrapper">
					<textarea rows="10" cols="60" name="content" id="summernote" placeholder="내용을 입력하세요"></textarea>
				</div>
				파일<br>
				<input type="file" id="file" name="file">
				<br>
				<input type="button" class="button primary small" value="글쓰기" onclick="check();">
				<input type="reset" class="button primary small" value="다시 쓰기">
				목록으로 돌아가기
				
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
		placeholder: '내용을 입력하세요',
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