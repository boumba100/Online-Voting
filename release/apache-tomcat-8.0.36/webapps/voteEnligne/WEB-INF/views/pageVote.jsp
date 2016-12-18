<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Vote</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<link href="${pageContext.request.contextPath}/resources/votePage.css"
	rel="stylesheet">
</head>
<body>

	<div id="voteSessionDiv" class="voteSessionDiv">
		<h1 id="currentActLabel"></h1>
		<input class="invisible" type="button" id="button1" value="1"
			onclick="submitVote(1)" /> <input class="invisible" type="button"
			id="button2" value="2" onclick="submitVote(2)" /> <input
			class="invisible" type="button" id="button3" value="3"
			onclick="submitVote(3)" /> <input class="invisible" type="button"
			id="button4" value="4" onclick="submitVote(4)" /> <input
			class="invisible" type="button" id="button5" value="5"
			onclick="submitVote(5)" /> <input class="invisible" type="button"
			id="button6" value="6" onclick="submitVote(6)" /> <input
			class="invisible" type="button" id="button7" value="7"
			onclick="submitVote(7)" /> <input class="invisible" type="button"
			id="button8" value="8" onclick="submitVote(8)" /> <input
			class="invisible" type="button" id="button9" value="9"
			onclick="submitVote(9)" /> <input class="invisible" type="button"
			id="button10" value="10" onclick="submitVote(10)" />
	</div>

</body>
</html>

<script type="text/javascript">
	var actNames = null;
	var currentIndex = 0;
	var getInterval = null;
	startSession();
	
	function startSession() {
		getActs();
		forceUpdate();
		getInterval = setInterval(function() {
			checkForUpdate();
		}, 2000);
	}

	function getActs() {
		$.post("voteSession", {
			request : "getActNames"
		}, function(data, status) {
			var jsonResult = JSON.parse(data);
			if (jsonResult.success == true) {
				actNames = jsonResult.actNames;
				updateVoteScreen();
			} else {
				window.history.back();
			}
		});
	}

	function checkForUpdate() {
		$.post("voteSession", {
			request : "update",
			clientIndex : currentIndex,
		}, function(data, status) {
			var jsonResult = JSON.parse(data);
			if (jsonResult.update == true) {
				currentIndex = jsonResult.currentIndex;
				updateVoteScreen();
			} else {
				if (jsonResult.type == 0) {
					window.history.back();
				}
			}
		});
	}
	
	function forceUpdate() {
		$.post("voteSession", {
			request : "forceUpdate",
		}, function(data, status) {
			var jsonResult = JSON.parse(data);
			if (jsonResult.update == true) {
				currentIndex = jsonResult.currentIndex;
				if(jsonResult.didVote == true) {
					waitScreen();
				} else {
					updateVoteScreen();
				}

			}
		});
	}

	function submitVote(score) {
		$.post("voteSession", {
			request : "submit",
			score : score
		}, function() {
			waitScreen();
		});
	}

	function updateVoteScreen() {
		if (currentIndex < actNames.length) {
			document.getElementById("currentActLabel").innerHTML = actNames[currentIndex];
			for (var i = 1; i <= 10; i++) {
				document.getElementById("button" + i).className = "button";
			}
		} else {
			voteFinishScreen();
			clearInterval(getInterval);
		}
	}

	function waitScreen() {
		document.getElementById("currentActLabel").innerHTML = "ATTENDS POUR LA PROCHAINE PRÉSENTATION";
		for (var i = 1; i <= 10; i++) {
			document.getElementById("button" + i).className = "invisible";
		}
	}
	
	function voteFinishScreen() {
		document.getElementById("currentActLabel").innerHTML = "LA FIN";
		for (var i = 1; i <= 10; i++) {
			document.getElementById("button" + i).className = "invisible";
		}
		setTimeout(function(){ window.history.back(); }, 3000);
	}
</script>










