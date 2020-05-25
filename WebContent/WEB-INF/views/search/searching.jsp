<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css">
<script type="text/javascript">
var jsonData;
var apiKey = "RGAPI-77affad3-b856-47e8-8f37-628f678b99b5";
var testlist = new Array();
var datalist = new Object();
var win = 0;
var lose = 0;
var winrate = null;
var mostone;
var mosttwo;
var mostthree;
	
	
</script>
</head>
<body>
	<input type="text" id="sname"><input type="button" value="search" onclick="get()">
	<div id="display"></div>
	<div id="donutdiv" style="height: 150px;"></div>
	
</body>
<script src="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js"></script>	
<script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
<script type="text/javascript">

function fuck() {
	console.log(win, lose, winrate);
}
function get() {
	var id = document.getElementById("sname").value;
	var sohwan = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/" 
		+id+"?api_key=" + apiKey;
	var mostchamp;
	var smostchamp;
	var tmostchamp;
	

	$.getJSON(sohwan, function(data, textStatus, req) {
		
		
		let summonerid = data.id;
		//console.log(data);
		//console.log(summonerid);
		 let table = "<table>"
	 			+"<tr><th>소환사 이름</th><th>티어</th><th>랭크</th><th>승</th>"
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
				$.getJSON('lolchamp.json', function(data, textStatus, req) {
						mostone = (data[0])[mostchamp].name;
						mosttwo = (data[0])[smostchamp].name;
						mostthree = (data[0])[tmostchamp].name;	
						//console.log(mostone, mosttwo, mostthree);
				});
			});
		$.getJSON(leagueInfo, function(data, textStatus, req) {
			win = data[0].wins;
            lose = data[0].losses;
            winrate = ((data[0].wins/(data[0].wins+data[0].losses))*100).toFixed(1)+"%";
            //console.log(win, lose, winrate);
			console.log(mostone, mosttwo, mostthree);
			
			$.each(data, function(index, obj){
					//console.log(data);
					table += "<tr><td>";
					table += obj.summonerName;								
					table += "</td><td>";
					table += obj.tier;
					table += "</td><td>";
					table += obj.rank;
					table += "</td><td>";
					table += obj.wins;
					table += "</td><td>";
					table += obj.losses;
					table += "</td><td>";
					table += (((obj.wins/(obj.wins+obj.losses))*100).toFixed(1)+"%");
					table += "</td><td>";
					table += mostone+", "+mosttwo+", "+mostthree;
					table += "</td><td>";
					
				
			});

	
			table += "</table>";
            $('#display').empty();
            $('#display').append(table);
            
            /*
			datalist.nickname = data[0].summonerName;
			datalist.win = data[0].wins;
			datalist.lose = data[0].losses;
			datalist.winrate = (data[0].wins/(data[0].wins+data[0].losses))*100+"%";
			datalist.tierinfo = data[0].tier;
			testlist.push(datalist);
			jsonData = JSON.stringify(testlist);
			console.log(jsonData);
			*/
		});
	
		
	});
	
	Morris.Donut({
		element: 'donutdiv',     //그래프가 들어갈 위치의 ID를 적어주세요
		data: [                                     //그래프에 들어갈 data를 적어주세요
		{label: '승', value: win },
		{label: '패', value: lose },
		],
		colors: ["#30a1ec", "#76bdee", "#387bb4", "#c4dafe"], //그래프 color를 지정해줍니다.
		formatter: function (y) { return y}  //y값 뒤에 %를 추가해줍니다.
		});
	
	
	
}	



</script>
</html>