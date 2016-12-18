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
			placeholder="code de passe" /></br> <input type="text"
			id="raspberryIpInput" placeholder="IP Raspberry"
			class="raspberryIpInput" /> </br> <input class="controlButton"
			type="button" id="startSessionButton" value="Commencer"
			onclick="startPollSession()" /> <input class="controlButton"
			id="removeSessionButton" type="button" value="Arreter la Session"
			onclick="stopSession()"></br> <span id="erroMessageHeader"></span>
	</div>

	<div id="voteSessionDiv" class="voteSessionDiv" class="invisible">
		<div id="actNamesHolder"></div>
		<input class="invisible" id="nextActButton" type="button"
			value="prochain" onclick="nextAct()"> <input
			class="invisible" id="stopButton" type="button"
			value="arreter la session" onclick="stopSession()">
	</div>
</body>
</html>

<script type="text/javascript">
	var raspberryIp = null;
	var voteSessionDiv = document.getElementById("voteSessionDiv");
	var actNamesHolder = document.getElementById("actNamesHolder");
	var currentActIndex = 0;
	var maxIndex;
	var connectedToPi = false;
	var codeValue = null;
	var passcodeValue = null;

	function startPollSession() {
		codeValue = document.getElementById('codeInput').value;
		passcodeValue = document.getElementById('passcodeInput').value;
		raspberryIp = "http://"
				+ document.getElementById("raspberryIpInput").value;
		$
				.post(
						"controle",
						{
							type : "startSession",
							code : document.getElementById('codeInput').value,
							passcode : passcodeValue = document
									.getElementById('passcodeInput').value
						},
						function(data, status) {
							var jsonResult = JSON.parse(data);
							console.log(data);
							if (jsonResult.succes == true) {
								$("#startInputDiv").remove();
								populateVoteSessionDiv(jsonResult.actNames);
								document.getElementById('nextActButton').className = "controlButton";
								document.getElementById('stopButton').className = "controlButton";
							} else {
								document.getElementById('erroMessageHeader').innerHTML = jsonResult.message;
							}
						});
		if (raspberryIp.length > 7) {
			console.log(raspberryIp.length);
			connectToPi();
			connectedToPi = true;
		}
		window.onbeforeunload = function() {
			  return "Veux tu vraiment quitter la session?";
			};
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
				if (jsonResult.score == 0 || jsonResult.score == null) {
					calculatedScore = 0;
				} else {
					calculatedScore = Math.round(jsonResult.score
							/ jsonResult.voterCount);
				}
				updateActsView(jsonResult.currentActIndex, calculatedScore);
				if (connectedToPi == true) {
					sendScoreToPi(calculatedScore);
				}
			}
		});
	}

	function stopSession() {
		if (confirm("etes-vous sûr de vouloir arrêter la session")) {
			if (codeValue == null || passcodeValue == null) {
				codeValue = document.getElementById('codeInput').value;
				passcodeValue = document.getElementById('passcodeInput').value;
			}
			$.post("controle", {
				type : "command",
				command : "stopSession",
				code : codeValue,
				passcode : passcodeValue
			}, function(data) {
				var jsonResult = JSON.parse(data);
				if (jsonResult.success == true) {
					location.reload();
				} else {
					alert(jsonResult.message);
				}
			});
		}
	}

	function updateActsView(index, score) {
		document.getElementById('actCell-' + (index - 1)).value += "("
				+ score + ")";
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
		$.post(raspberryIp + "/connect", {
			passcode : piPasscode
		}, function(data) {
			console.log(data);
		})
	}

	function sendScoreToPi(scoreValue) {
		$.post(raspberryIp + "/sendScore", {
			passcode : piPasscode,
			score : scoreValue
		}, function(data) {
			console.log(data);
		})
	}
	function nextLine() {
		voteSessionDiv.appendChild(document.createElement("br"));
	}
</script>

































