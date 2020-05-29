<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" href="assets/css/main.css" />
	<noscript><link rel="stylesheet" href="/assets/css/noscript.css" /></noscript>
	<style type="text/css">
		
		#joinform {
			text-align: center;
			margin: 0 300px;
		}
		
		#btnbox {
			display: inline-block;
			margin-top: 90px;
		}
		
		label {
			text-align: left;
		}
		
	</style>
</head>

<jsp:include page="/WEB-INF/views/header/header.jsp"></jsp:include>

<body>
	<div id="main" class="wrapper style1">
		<div class="container">
			<header class="major">
				<h2>로그인</h2>		
			</header>
	      	<form action="LoginOk.Lolz" method="post" id="joinform">
				<div class="form-group">
					<label for="id">ID</label>
					<input type="text" class="form-control" id="id" name="id">
				</div>
				<br>
				<div class="form-group">
					<label for="pwd">비밀번호</label>
					<input type="password" class="form-control" id="pwd" name="pwd">
				</div>
				<div id="btnbox">
					<input type="submit" class="button primary" value="로그인">
				</div>
			</form>
		</div>
	</div>
</body>
</html>