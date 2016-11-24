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
			id="button4" value="4" onclick="submitVote(4)" />
	</div>

</body>
</html>

<script type="text/javascript">
	var actNames = null;
	var currentIndex = 0;
	startSession()
	function startSession() {
		getActs();
		setInterval(function() {
			checkForUpdate();
		}, 1000);
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
				alert("probleme avec le serveur");
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
			console.log(jsonResult);
			if (jsonResult.update == true) {
				currentIndex = jsonResult.currentIndex;
				updateVoteScreen();

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
			for (var i = 1; i <= 4; i++) {
				document.getElementById("button" + i).className = "button";
			}
		} else {
			voteFinishScreen();
		}
	}

	function waitScreen() {
		document.getElementById("currentActLabel").innerHTML = "ATTEND POUR LA PROCHAINE PRÉSENTATION";
		for (var i = 1; i <= 4; i++) {
			document.getElementById("button" + i).className = "invisible";
		}
	}
	
	function voteFinishScreen() {
		document.getElementById("currentActLabel").innerHTML = "LA FIN";
		for (var i = 1; i <= 4; i++) {
			document.getElementById("button" + i).className = "invisible";
		}
	}
</script>










