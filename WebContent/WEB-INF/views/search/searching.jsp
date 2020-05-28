<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>소환사검색</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css">
	<script type="text/javascript">
	
		var jsonData;
		var apiKey = "RGAPI-06ee38fb-bc53-4bf8-b8f0-2cd46295269a";
		var testlist = new Array();
		var datalist = new Object();
		var win = 0;
		var lose = 0;
		var winrate = null;
		var mostone;
		var mosttwo;
		var mostthree;
		var mostoneimg;
		var mosttwoimg;
		var mostthreeimg;
	
	</script>
	<style type="text/css">
		
		* {
			box-sizing: border-box;
		}
		
		.images {
			width: 50px;
		}
		
		#searchbox {
			width: 700px;
			display: inline-block;
		}
		
		#btn, #sname {
			float: left;		
		}
		
		#btn {
			margin: 6px 0 0 10px;
		}
		
		#sname {
			width: 570px;
		}
		
		#searchbox_wrapper {
			text-align: center;
			margin-bottom: 100px;
		}
		
		table th, tr, td {
			text-align: center;
		}
		
		#donutdiv, #donutdiv2 {
			background-color: white;
		}
		
		table {
			padding: 0;
		}
		
		html {
			margin-bottom: 120px;
		}
	
		
		
	</style>
</head>

<jsp:include page="/WEB-INF/views/header/header.jsp"></jsp:include>

<body>
	<div id="main" class="wrapper style1">
		<div class="container">
			<header class="major">
				<h2>소환사 검색</h2>
				<p>랭크 게임을 한 소환사만 검색할 수 있습니다.</p>
			</header>
			<div id="searchbox_wrapper">
				<div id="searchbox"> <!-- <div class="row gtr-150"> -->
					<!-- <div class="col-12 col-12-medium"> -->
					<input type="text" id="sname">
					<input type="button" class="button primary small" value="search" id="btn" onclick="checkSummoner()">
				</div>
			</div> <!-- searchbox_wrapper end -->
		<!-- </div> -->
			<!-- <div class="container"> -->
			<div id="display"></div>
			
				<div class="col-6 col-6-medium" id="donutdiv" style="height: 150px;width: 50%; margin: 0px;float: left;" ></div>
				<div class="col-6 col-6-medium" id="donutdiv2" style="height: 150px;width: 50%; margin: 0px;yellow; float: left;"></div>
			</div>
		<!-- <div class="container"> -->
			<div class="col-6 col-6-medium"  id="gaugeChart" style="height: 150px;width: 50%; margin: 0px;float: left;" ></div>
			<div class="col-6 col-6-medium" id="gaugeChart2" style="height: 150px;width: 50%; margin: 0px;yellow; float: left;"></div>
		<!-- </div> -->
	</div>

<script src="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js"></script>	
<script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.2.0/raphael-min.js"></script>

<!-- 게이지차트 시작 -->
<script src="https://d3js.org/d3.v5.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/billboard.js/1.12.10/billboard.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/billboard.js/1.12.10/billboard.js"></script>
<!-- 게이지차트 끝 -->
 
<script type="text/javascript">

	function checkSummoner(){
		
		var id = document.getElementById("sname").value;
		var sohwan = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/" 
				   + id + "?api_key=" + apiKey;

		$.ajax({
		          url: sohwan,
		          type: "GET",
		          processData: false, 
		          contentType: false,  
		          data: null, 
		          dataType:'json',
		          error: function(error) {
		            alert("없는 소환사");
		          },
		          success: function(data) {
		        	get();
		          }
	          });
	};
	
	
	function get() {
		var id = document.getElementById("sname").value;
		var sohwan = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/" 
				   + id + "?api_key=" + apiKey;
		var queuetype;
		var mostchamp;
		var smostchamp;
		var tmostchamp;
		var champimg;
		var score = [0,0];
		
		$.getJSON(sohwan, function(data, textStatus, req) {
			let summonerid = data.id;
			let table = "<table>"
		 			+"<tr><th>소환사 이름</th><th>큐 타입</th><th>티어</th><th>랭크</th><th>승</th>"
		 			+"<th>패</th><th>승률</th><th>모스트 챔피언</th></tr>";
			var leagueInfo = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/"
			+data.id+"?api_key=" + apiKey;
			var champinfo = "https://kr.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-summoner/"
				+summonerid+"?api_key="+apiKey;
				
				$.getJSON(champinfo, data, function(data, textStatus, req) {
					 mostchamp = data[0].championId;
					 smostchamp = data[1].championId;
					 tmostchamp = data[2].championId;
					
					//console.log(mostchamp, smostchamp, tmostchamp);
					$.getJSON('lolchamp.Search', function(data, textStatus, req) {
							mostone = (data[0])[mostchamp].name;
							mosttwo = (data[0])[smostchamp].name;
							mostthree = (data[0])[tmostchamp].name;	
							mostoneimg = (data[0])[mostchamp].engname;
							mosttwoimg = (data[0])[smostchamp].engname;
							mostthreeimg = (data[0])[tmostchamp].engname;
							//console.log(mostone, mosttwo, mostthree);
							$.getJSON(leagueInfo, function(data, textStatus, req) {
								
								if(data == ""){
									alert('랭크게임을 하지 않은 유저입니다.');
								}
								queuetype = data[0].queueType;
								win = data[0].wins;
					            lose = data[0].losses;
					            winrate = ((data[0].wins/(data[0].wins+data[0].losses))*100).toFixed(1)+"%";
					            
					            if(data[1]==null){
					            	var win1 =null;
					            	var lose1 = null;
					            	
					            }else if(data[1].wins!=null ){
					            	var win1 = data[1].wins
					            	var lose1 = data[1].losses
					            }
					           
					            
								
								//테이블 그리기
								
								$.each(data, function(index, obj){
										//console.log(index); each는 index 0부터 시작!
										if(obj.queueType==="RANKED_SOLO_5x5"){
											queuetype="솔로랭크";
										}else{
											queuetype="자유랭크";
										}
										
										//console.log("queuetype: "+queuetype);
										table += "<tr><td>";
										table += obj.summonerName;								
										table += "</td><td>";
										table += queuetype;
										table += "</td><td>";
										table += obj.tier;
										table += "<img src='images/tier/"+obj.tier+".png' style=\"width:50px; height: 50px\"/>"
										table += "</td><td>";
										table += obj.rank;
										table += "</td><td>";
										table += obj.wins;
										table += "</td><td>";
										table += obj.losses;
										table += "</td><td>";
										table += (((obj.wins/(obj.wins+obj.losses))*100).toFixed(1)+"%");
										table += "</td><td>";
										champimg = mostoneimg;
										var imgsource = "https://ddragon.leagueoflegends.com/cdn/10.9.1/img/champion/"+champimg+"."+"png";
										table += "<div class = 'images' style=\"width:52px; height:52px; border:1px solid green; float:left;\"><img src ='"+imgsource+"' style='width:50px; float:left'></div>";
										champimg = mosttwoimg;
										var simgsource = "https://ddragon.leagueoflegends.com/cdn/10.9.1/img/champion/"+champimg+"."+"png";
										table += "<div class = 'images' style=\"width:52px; height:52px; border:1px solid green; float:left;\"><img src ='"+simgsource+"' style='width:50px; float:left'></div>";
										champimg = mostthreeimg;
										var timgsource = "https://ddragon.leagueoflegends.com/cdn/10.9.1/img/champion/"+champimg+"."+"png";
										table += "<div class = 'images' style=\"width:52px; height:52px; border:1px solid green; float:left;\"><img src ='"+timgsource+"' style='width:50px'></div>";
										/* table += "</td><td>"; */	
								});
								table += "</table>";//테이블 그리기끝
								
								
								//스코어 메기기
								 
								$.each(data, function(index, obj){
									//console.log(score[index]);
									
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
									//console.log(score[index]);
									
									
									try{
										//console.log("이게나오는거: "+obj.rank);
										if(obj.rank=="IV"){	
											score[index] += 0;
											//console.log(score[index]);
										}else if(obj.rank=="III"){
											score[index] +=1 ;	
											//console.log(score[index]);
										}else if(obj.rank=="II"){
											score[index] += 2;		
											//console.log(score[index]);
										}else if(obj.rank=="I"){
											score[index] += 3;	
											//console.log(score[index]);
										}else{
											console.log("rank아무거도 안타지롱");
										}
										
									}catch(e){
										console.log(e);
										console.log("score, rank에러")
									}
									
								});
								console.log("score[0]"+score[0]);
								console.log("score[1]"+score[1]);
								
					            $('#display').empty();
					            $('#display').append(table);
					           /*  $('th').css("text-align", "center"); */
					           
								
								//도넛그리기
					            $('#donutdiv').empty();
								$('#donutdiv2').empty();
					            if(win1!=null){
					            $('#donutdiv2').prepend("승률");
					            $('#donutdiv2').append("<br>");
					            $('#donutdiv2').append("<br>");
					            $('#donutdiv2').append("당신 티어의 <br> 위치");
					        	$('#donutdiv2').append(Morris.Donut({
					        		element: 'donutdiv2',     //그래프가 들어갈 위치의 ID를 적어주세요
					        		data: [                                     //그래프에 들어갈 data를 적어주세요
					        		{label: '승', value: win1},
					        		{label: '패', value: lose1},
					        		],
					        		colors: ["#30a1ec", "#76bdee", "#387bb4", "#c4dafe"], //그래프 color를 지정해줍니다.
					        		formatter: function (y) { return y}  //y값 뒤에 %를 추가해줍니다.
					        		}));
					            }
					            $('#donutdiv').append("승률");
					            $('#donutdiv').append("<br>");
					            $('#donutdiv').append("<br>");
					            $('#donutdiv').append("당신 티어의 <br> 위치");
					        	$('#donutdiv').append( Morris.Donut({
					        		element: 'donutdiv',     //그래프가 들어갈 위치의 ID를 적어주세요
					        		data: [                                     //그래프에 들어갈 data를 적어주세요
					        		{label: '승', value: win, },
					        		{label: '패', value: lose},
					        		],
					        		colors: ["#30a1ec", "#76bdee", "#387bb4", "#c4dafe"], //그래프 color를 지정해줍니다.
					        		formatter: function (y) { return y}  //y값 뒤에 %를 추가해줍니다.
					        		}));
					        	///
					        	$('#gaugeChart').empty();
								$('#gaugeChart2').empty();
					        	var chart = bb.generate({
					        		  data: {
					        		    columns: [
					        			["data", 100]
					        		    ],
					        		    type: "gauge",
					        		    onclick: function(d, i) {
					        			console.log("onclick", d, i);
					        		   },
					        		    onover: function(d, i) {
					        			console.log("onover", d, i);
					        		   },
					        		    onout: function(d, i) {
					        			console.log("onout", d, i);
					        		   }
					        		  },
					        		  gauge: {label:{show:false}},
					        		  color: {
					        		    pattern: [
					        		      "#FF0000",
					        		      "#F97600",
					        		      "#F6C600",
					        		      "#60B044"
					        		    ],
					        		    threshold: {
					        		      values: [
					        		        30,
					        		        60,
					        		        90,
					        		        100
					        		      ]
					        		    }
					        		  },
					        		  size: {
					        		    height: 180
					        		  },
					        		  bindto: "#gaugeChart"
					        		});
									//console.log(score[0]);
					        		setTimeout(function() {
					        			chart.load({
					        				columns: [["data", (score[0]/27)*100]]
					        			});
					        			console.log("gaugeChart score[0] 초기화 전: "+score[0]);
					        			score[0]=0;
					        			console.log("gaugeChart score[0] 초기화 후: "+score[0]);
					        		}, 1000);
					        	
	
	
					        	////
					        	///
					        	if(score[1]!=0){
					        		
					        	var chart2 = bb.generate({
					        		  data: {
					        		    columns: [
					        			["data", 100]
					        		    ],
					        		    type: "gauge",
					        		    onclick: function(d, i) {
					        			console.log("onclick", d, i);
					        		   },
					        		    onover: function(d, i) {
					        			console.log("onover", d, i);
					        		   },
					        		    onout: function(d, i) {
					        			console.log("onout", d, i);
					        		   }
					        		  },
					        		  gauge: {label:{show:false}},
					        		  color: {
					        		    pattern: [
					        		      "#FF0000",
					        		      "#F97600",
					        		      "#F6C600",
					        		      "#60B044"
					        		    ],
					        		    threshold: {
					        		      values: [
					        		        30,
					        		        60,
					        		        90,
					        		        100
					        		      ]
					        		    }
					        		  },
					        		  size: {
					        		    height: 180
					        		  },
					        		  bindto: "#gaugeChart2"
					        		});
	
					        		setTimeout(function() {
					        			chart2.load({
					        				columns: [["data", (score[1]/27)*100]]
					        			});
					        			console.log("gaugeChart score[1] 초기화 전: "+score[1]);
					        			score[1]=0;
					        			console.log("gaugeChart score[1] 초기화 후: "+score[1]);
					        		}, 1000);
					        		
					        	}
	
					        	////
					        	
							});
					});
				});
		}); //json 끝나는곳
	}	
</script>
</body>
</html>