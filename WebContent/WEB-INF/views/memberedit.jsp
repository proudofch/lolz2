<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<link rel="stylesheet" href="assets/css/main.css" />
	<title>내 정보 수정</title>
	<script type="text/javascript">
 var apiKey = "RGAPI-ae90f648-5797-4f10-9476-b493a22796b0";
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
		        $.getJSON(sohwan, function(data, textStatus, req) {
				let summonerid = data.id;
				var leagueInfo = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/"
				+data.id+"?api_key=" + apiKey;
	
				$.getJSON(leagueInfo, function(data, textStatus, req) {
				
						if(data == ""){
							alert('랭크게임을 하지 않은 유저입니다.');
						}
						queuetype = data[0].queueType;
			
						//스코어 메기기
						 
						$.each(data, function(index, obj){
							if(obj.tier=="IRON"){
								score[index]+=0;
							}else if(obj.tier=="BRONZE"){
								score[index]+=4;									
							}else if(obj.tier=="SILVER"){
								score[index]+=8;									
							}else if(obj.tier=="GOLD"){
								score[index]+=12;									
							}else if(obj.tier=="PLATINUM"){
								score[index]+=16;									
							}else if(obj.tier=="DIAMOND"){
								score[index]+=20;									
							}else if(obj.tier=="MASTER"){
								score[index]+=24;									
							}else if(obj.tier=="GRANDMASTER"){
								score[index]+=25;								
							}else if(obj.tier=="CHALLENGER"){
								score[index]+=26;									
							}
							try{
								console.log("이게나오는거: "+obj.rank);
								if(obj.rank=="IV"){	
									score[index] += 0;
								}else if(obj.rank=="III"){
									score[index] +=1 ;	
								}else if(obj.rank=="II"){
									score[index] += 2;		
								}else if(obj.rank=="I"){
									score[index] += 3;	
								}else{
									console.log("rank아무거도 안타지롱");
								}
							}catch(e){
								console.log(e);
								console.log("score, rank에러")
							}
						});
						/* summonerScore=score[0];
						$('#summonerScore').val(summonerScore); */
						$('#summonerScore').val(score[0]);
						console.log("콜솔"+score[0]);
					});
				
			}); //json 끝나는곳
		        
      }
	 
	 
	 
	 
	 
	 
	 
	 $.each(data, function(index, obj){
			if(obj.tier=="IRON"){
				score[index]+=0;
			}else if(obj.tier=="BRONZE"){
				score[index]+=4;									
			}else if(obj.tier=="SILVER"){
				score[index]+=8;									
			}else if(obj.tier=="GOLD"){
				score[index]+=12;									
			}else if(obj.tier=="PLATINUM"){
				score[index]+=16;									
			}else if(obj.tier=="DIAMOND"){
				score[index]+=20;									
			}else if(obj.tier=="MASTER"){
				score[index]+=24;									
			}else if(obj.tier=="GRANDMASTER"){
				score[index]+=25;								
			}else if(obj.tier=="CHALLENGER"){
				score[index]+=26;									
			}
			try{
				console.log("이게나오는거: "+obj.rank);
				if(obj.rank=="IV"){	
					score[index] += 0;
				}else if(obj.rank=="III"){
					score[index] +=1 ;	
				}else if(obj.rank=="II"){
					score[index] += 2;		
				}else if(obj.rank=="I"){
					score[index] += 3;	
				}else{
					console.log("rank아무거도 안타지롱");
				}
			}catch(e){
				console.log(e);
				console.log("score, rank에러")
			}
		});
		console.log("유저의 스코어는 "+score[0]);
						};
 
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

      if(!check(re,pw,"패스워드는 4~12자의 영문 대소문자와 숫자로만 입력해주세요")) {
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
  
/*   $(document).ready(function(){
	 
  });
   */
  
 
	</script>
	<style type="text/css">
	
		#memberedit {
			text-align: center;
			margin: 0 300px;
		}
		
		label, h6 {
			text-align: left;
			margin-bottom: 0;
		}
		
		.form-group {
			margin: 10px 0;
		}
		
		#btns {
			clear: both;
			display: inline-block;
			margin-top: 90px;
		}
		
		#summonerId {
			width: 480px;
		}
		
		#summonerId, #btn {
			float: left;
		}
		
		#btn {
			margin-left: 11px;
			margin-top: 4px;
		}
		
		#btn_wrapper {
			text-align: center;
		}
	
	</style>
</head>

<jsp:include page="/WEB-INF/views/header/header.jsp"></jsp:include>

<body>
	<div id="main" class="wrapper style1">
		<div class="container">
			<header class="major">
				<h2>내 정보 수정</h2>		
			</header>
   
	        <c:set var="member" value="${requestScope.memberdata}"></c:set>
		
			<form name="memberedit" id="memberedit" action="MemberEditOk.Lolz" method="post" onsubmit="return validate();"> 
				<div class="form-group">
					<label for="id">ID</label>
					<input type="text" class="form-control" id="id" name="id" maxlength="20" value="${member.id}" readonly>
					<h6>ID는 수정할 수 없습니다.</h6>
				</div>
				
				<div class="form-group">
					<label for="pwd">비밀번호</label>
					<input type="password" maxlength="20" value="${member.pwd}" class="form-control" id="pwd" name="pwd">
				</div>
				
				<div class="form-group">
					<label for="pwdCheck">비밀번호 확인</label>
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
				
					<div class="form-group_spc">
						<label for="summonerId">소환사 ID</label>
						<input type="text" class="form-control" id="summonerId" name="summonerId" value="${member.summonerId}">
						<input type="button" class="button primary small" id="btn" value="유무 확인" onclick="checkSummoner()">
					</div>
				
				<input type="hidden" class="form-control" id="summonerScore" name="summonerScore">
						
				<div id="btn_wrapper">
					<div id="btns">
						<input type="submit" class="button primary small" value="수정 완료">
						<button type="button" onclick="myFunction()" class="button small">회원 탈퇴</button>
		   			</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>







