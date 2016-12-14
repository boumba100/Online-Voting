<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<link href="${pageContext.request.contextPath}/resources/controle.css"
	rel="stylesheet">
<title>controle</title>
</head>
<body>
	<div id="startInputDiv" class="startInput">
		<input class="codeInput" type="text" id="codeInput" placeholder="code" />
		</br> <input class="passwordInput" type="password" id="passcodeInput"
			placeholder="code de passe" /></br> <input type="text" id="raspberryIpInput"
			placeholder="IP Raspberry" class="raspberryIpInput"/> </br> <input class="button"
			type="button" id="startSessionButton" value="commencer"
			onclick="startPollSession()" /> </br> <span id="erroMessageHeader"></span>
	</div>

	<div id="voteSessionDiv" class="voteSessionDiv" class="invisible">
		<div id="actNamesHolder"></div>
		<input class="invisible" id="nextActButton" type="button"
			value="prochain" onclick="nextAct()">
	</div>
</body>
</html>

<script type="text/javascript">
	var raspberryIp = null;
	var voteSessionDiv = document.getElementById("voteSessionDiv");
	var actNamesHolder = document.getElementById("actNamesHolder");
	var currentActIndex = 0;
	var maxIndex;

	function startPollSession() {
		console.log("sending data...");
		codeValue = document.getElementById('codeInput').value;
		passcodeValue = document.getElementById('passcodeInput').value;
		raspberryIp = "http://" + document.getElementById("raspberryIpInput").value;
		$
				.post(
						"controle",
						{
							type : "startSession",
							code : document.getElementById('codeInput').value,
							passcode : passcodeValue = document.getElementById('passcodeInput').value
						},
						function(data, status) {
							var jsonResult = JSON.parse(data);
							console.log(data);
							if (jsonResult.succes == true) {
								$("#startInputDiv").remove();
								populateVoteSessionDiv(jsonResult.actNames);
								document.getElementById('nextActButton').className = "nextActButton";
							} else {
								document.getElementById('erroMessageHeader').innerHTML = jsonResult.message;
							}
						});
		if(passcodeValue.length > 0) {
			connectToPi();
		}
	}

	function sendCommand(command) {
		$.post("controle", {
			type : "command",
			command : command
		}, function(data, status) {
			alert("Data: " + data + "\nStatus: " + status);
		});
	}

	function populateVoteSessionDiv(actNameList) {
		maxIndex = actNameList.length;
		createActCell(actNameList[0], 'activeActCell', 0)
		for (var i = 1; i < actNameList.length; i++) {
			createActCell(actNameList[i], 'actCell', i)
		}
	}

	function createActCell(actName, className, index) {
		var cell = document.createElement("INPUT");
		cell.setAttribute('type', 'text');
		cell.setAttribute('id', 'actCell-' + index);
		cell.setAttribute('value', actName);
		cell.setAttribute('readonly', true);
		cell.setAttribute('class', className);
		actNamesHolder.appendChild(cell);
	}

	function nextAct() {
		$.post("controle", {
			type : "command",
			command : "nextAct",
			code : codeValue,
			passcode : passcodeValue
		}, function(data) {
			var jsonResult = JSON.parse(data);
			console.log(jsonResult);
			if (jsonResult.success == true) {
				updateActsView(jsonResult.currentActIndex, Math.round(jsonResult.score / jsonResult.voterCount));
				sendScoreToPi(Math.round(jsonResult.score / jsonResult.voterCount));
			}
		});
	}

	function updateActsView(index, score) {
		document.getElementById('actCell-' + (index - 1)).value += " (score : "
				+ score + " )";
		if (index == maxIndex) {
			$("#nextActButton").remove();
			document.getElementById('actCell-' + (index - 1)).className = "actCell";
		} else {
			document.getElementById('actCell-' + (index - 1)).className = "actCell";
			document.getElementById('actCell-' + index).className = "activeActCell";
		}
	}
	
	function connectToPi() {
		piPasscode = prompt("passcode pour le raspberryPi");
		$.post(raspberryIp + "/connect", 
				{passcode:piPasscode
			}, function(data) {
				console.log(data);
			})
	}

	
	function sendScoreToPi(scoreValue) {
		$.post(raspberryIp + "/sendScore", {
			passcode:piPasscode,
			score: scoreValue
			}, function(data) {
				console.log(data);
			})
	}
	function nextLine() {
		voteSessionDiv.appendChild(document.createElement("br"));
	}
</script>

































