<jsp:include page="/WEB-INF/views/header/header.jsp"></jsp:include>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입</title>
	
	<link rel="stylesheet" href="assets/css/main.css" />
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
   	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> 
   	<meta name="viewport" content="width=device-width, initial-scale=1"> 
   	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
   
   <style>
	
		#memberadd {
			text-align: center;
			margin: 0 300px;
		}
		
		#btnbox {
			display: inline-block;
			margin-top: 90px;
		}
		
		label {
			text-align: left;
			margin-top: 10px;
		}
		
		#btn_wrapper {
			text-align: center;
		}
		
		body {
			margin-top: 80px;
		}
		
		#id, #summonerId {
			width: 486px;
			position: absolute;
		}
		
		#message, #check {
			position: relative;
			left: 248px;
			margin-top: 5px;
			margin-bottom: 10px;
		}
			
		
   	</style>
	<script type="text/javascript">
    var apiKey = "RGAPI-ee45696f-b798-454c-a44a-f085dcfb0791";
	var score = [0,0];
	function checkSummoner(){
		 if($('#summonerId').val() == ""){
	         alert("소환사 아이디를 입력하세요");
	         $('#summonerId').focus();
	      }else{
				var id = document.getElementById("summonerId").value;
				var sohwan = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/" 
					+id+"?api_key=" + apiKey;
				
				  $.ajax({
			          url: sohwan,
			          type: "GET",
			          processData: false, 
			          contentType: false,  
			          data: null, 
			          dataType:'json',
			          error : function(error) {
			            alert("존재하지 않는 소환사 아이디 입니다.");
			          },
			          success: function (data) {
			        	  alert("존재하는 소환사 아이디 입니다.");
			          }
			        })
			        
	      }
							};
 function myFunction(){
		 location.href="SummonerIdCheck.Lolz";
 }
  function validate(){
	  var re = /^[a-zA-Z0-9]{4,12}$/;
	  var re2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	  var id = document.getElementById("id");
      var pw = document.getElementById("pwd");
      var email = document.getElementById("email");
      var checkpw = document.getElementById("pwdCheck");
      
      if(!check(re,id,"아이디는 4~12자의 영문 대소문자와 숫자로만 입력")) {
          return false;
      }
      if(!check(re,pw,"패스워드는 4~12자의 영문 대소문자와 숫자로만 입력")) {
          return false;
      }

      if(memberadd.pw.value != checkpw.value) {
          alert("비밀번호가 다릅니다. 다시 확인해 주세요.");
          checkpw.value = "";
          checkpw.focus();
          return false;
      }
      if(memberadd.id.value=="") {
          alert("아이디를 입력해 주세요");
          memberadd.id.focus();
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
	 
	  $('#message').click(function(){
          if($('#id').val() == ""){
             alert("아이디를 입력하세요");
             $('#id').focus();
          }else{
          $.ajax(
                {   
                   url:"IdCheck.Lolz",  
                   data:{id:$('#id').val()},
                   dataType:"html",
                   success:function(responsedata){
                      console.log(">" + responsedata + "<");
                      if(responsedata == "true"){
                         alert("사용 가능한 아이디 입니다.");
                         $('#pwd').focus();
                      }else{
                         alert("이미 존재하는 아이디 입니다.");
                         $('#id').val("");
                         $('#id').focus();
                      }
                   },
                   error:function(){
                      console.log("errrrrrrr");
                   }
                }      
             );
          };
    });  
	 
	  
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
                    //currentdate >> Sun May 03 2020 00:00:00 GMT+0900 (대한민국 표준시)
                    let date2 = new Date(currentdate);
                    //alert(date2.getFullYear() + "/" + date2.getDate());
                 }
              }
                          );
        
  });
  
 
  </script>


</head>

<body>
	<div id="main" class="wrapper style1">
		<div class="container">
	     	<header class="major">
				<h2>회원가입</h2>		
			</header>

			<form name="memberadd" id="memberadd" action="MemberAddOk.Lolz" method="post" onsubmit="return validate();"> 
			 
				<div class="form-group">
					<label for="id">ID</label>
			        <input type="text" class="form-control" id="id" name="id" maxlength="20">
		            <input type="button" class="button primary small" value="중복 확인" id="message">  
				</div>

				<div class="form-group">
		            <label for="pwd">비밀번호</label>
		            <input type="password" maxlength="20" class="form-control" id="pwd" name="pwd">
				</div>
				
				<div class="form-group">
		            <label for="pwdCheck">비밀번호 확인</label>
		            <input type="password" maxlength="20" class="form-control" id="pwdCheck" name="pwdCheck">
				</div>

				<div class="form-group">
		            <label for="email">이메일</label>
		            <input type="text" class="form-control" id="email" name="email">
				</div>
				
		        <div class="form-group">
		            <label for="bd">생일</label>
		            <input type="text" class="form-control" id="bd" name="bd">      
				</div>
		             
				<div class="form-group">
		            <label for="summonerId">소환사ID</label>
		            <input type="text" class="form-control" id="summonerId" name="summonerId">
			       	<input type="button" class="button primary small" value="유무 확인" id="check" onclick="checkSummoner()">  
				</div>
				<div id="btn_wrapper">		 
					<div id="btnbox">
				        <input type="submit" class="button primary small" value="가입 완료" >
				        <input type="reset" class="button small" value="입력 취소">
			        </div> 
		        </div>
			</form>
		</div>
	</div>
</body>
</html>