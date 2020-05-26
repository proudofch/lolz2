<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
<title>Member Edit</title>


 <script type="text/javascript">
 function test(){
	 
 }
 
 function myFunction(){
	 var r=confirm("정말 탈퇴하시겠습니까?");
	 if(r==true){
		 location.href='MemberDelete.Lolz?id=${sessionScope.id}';
	 }
 }
  function validate(){
	  var re = /^[a-zA-Z0-9]{4,12}$/;
	  var re2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      var pw = document.getElementById("pwd");
      var email = document.getElementById("email");
      var checkpw = document.getElementById("pwdCheck");

      if(!check(re,pw,"패스워드는 4~12자의 영문 대소문자와 숫자로만 입력")) {
          return false;
      }

      if(memberadd.pw.value != checkpw.value) {
          alert("비밀번호가 다릅니다. 다시 확인해 주세요.");
          checkpw.value = "";
          checkpw.focus();
          return false;
      }
      if(memberadd.email.value=="") {
          alert("이메일을 입력해 주세요");
          memberadd.email.focus();
          return false;
      }
      if(memberadd.bd.value=="") {
          alert("생년월일을 입력해 주세요");
          memberadd.bd.focus();
          return false;
      }
      if(!check(re2, email, "적합하지 않은 이메일 형식입니다.")) {
          return false;
      }
  
       alert("회원가입이 완료되었습니다.");
  }
  function check(re, what, text) {
      if(re.test(what.value)) {
          return true;
      }
      alert(text);
      what.value = "";
      what.focus();
      return false;
  }
  
  $(document).ready(function(){
	  $('#bd').datepicker(
              {
                 dateFormat: "yy-mm-dd",
                 numberOfMonths: 1,
                 onSelect: function(date){
                    alert("onSelect : " + date);
                 }
                 ,
                 onClose: function(date){
                    let currentdate = $('#bd').datepicker('getDate');
                    console.log(date + " / " + currentdate);
                    let date2 = new Date(currentdate);
                 }
              }
                          );
  });
  
  
 
  </script>



</head>
<jsp:include page="/WEB-INF/views/header/header.jsp"></jsp:include>
<body>
	<div id="main" class="wrapper style1">
		<div class="container">
      	  <header class="major">
				<h2>내 정보 수정</h2>		
		  </header>
   
        <c:set var="member" value="${requestScope.memberdata}"></c:set>
	
		<form name="memberedit" action="MemberEditOk.Lolz" method="post" onsubmit="return validate();"> 
			
         <div class="form-group">
            <label for="id">아이디</label>
            <input type="text" class="form-control" id="id" name="id"
             maxlength="20" value="${member.id}" readonly>
            <h6>아이디는 수정할 수 없습니다.</h6>
         </div>
         <div class="form-group">
            <label for="pwd">패스워드</label>
            <input type="password" maxlength="20" value="${member.pwd}" class="form-control" id="pwd" name="pwd">
         </div>
         <div class="form-group">
            <label for="pwdCheck">패스워드 확인</label>
            <input type="password" maxlength="20" value="${member.pwd}"class="form-control" id="pwdCheck" name="pwdCheck">
         </div>

         <div class="form-group">
            <label for="email">이메일</label>
            <input type="text" class="form-control" id="email" name="email" value="${member.email}">
         </div>
         <div class="form-group">
            <label for="bd">생일</label>
             <input type="text" class="form-control" id="bd" name="bd" value="${member.bd}" readonly>          
            <h6>생일은 수정할 수 없습니다.</h6>
         </div>

  		<div class="form-group">
            <label for="summonerId">소환사 아이디</label>
            <input type="text" class="form-control" id="summonerId" name="summonerId" value="${member.summonerId}">
         </div>
 

         <input type="submit" class="button primary small" value="수정완료" >
         
      	 <button type="button" onclick="myFunction()" class="button small">회원탈퇴</button>
   	
      </form>
		
		</div>
	</div>
</body>
</html>







