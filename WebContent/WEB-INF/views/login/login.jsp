<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <title>로그인 페이지</title>
   <link rel="stylesheet" href="./style/common.css">
</head>

<jsp:include page="/WEB-INF/views/header/header.jsp"></jsp:include>

<body>
<div id="main" class="wrapper style1">
   <div class="container">
      		<header class="major">
				<h2>로그인</h2>		
		    </header>
		<br>
      	<form action="LoginOk.Lolz" method="post" id="joinform">

		   <div class="form-group">
		      <label for="id">ID</label>
		      <input type="text" class="form-control" id="id" name="id">
		   </div>
		   <div class="form-group">
		      <label for="pwd">비밀번호</label>
		      <input type="password" class="form-control" id="pwd" name="pwd">
		   </div>
         <input type="submit" class="btn btn-warning" value="로그인">
      </form>
   </div>
 </div>
</body>
</html>