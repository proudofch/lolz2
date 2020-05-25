<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시판 글쓰기</title>
	
	<style type="text/css">
		h2 {
			margin-top: 100px;
			padding-top: 100px;
		}
	</style>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript">
		
		function check() {
	
			if (!board_write.title.value) {
				alert('제목을 입력하세요');
				board_write.title.focus();
				return false;
			}
			
			if (!board_write.content.value) {
				alert('내용을 입력하세요');
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
				<h2>Write</h2>
				<p>notice blahblahblah</p>
			</header>
				<form name="board_write" id="board_write" action="FreeBoardWriteOk.Board" method="post" enctype="multipart/form-data">
				<input type="hidden" id="id" name="id" value="idishello"> <!--  value="${sesssionScope.id}" -->
				제목<br> 
				<input type="text" name="title" id="title" placeholder="제목을 입력하세요">
				<br>
				내용<br>
				<textarea name="content" id="content" placeholder="내용을 입력하세요" rows="6"></textarea>
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

</html>