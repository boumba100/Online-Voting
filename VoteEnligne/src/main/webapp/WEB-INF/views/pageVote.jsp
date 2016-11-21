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
		<div id="actNamesHolder"></div>
	</div>

</body>
</html>

<script type="text/javascript">
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
				console.log(data);
			} else {
				alert("erreur avec le serveur");
				window.location = "";
			}
		});
	}

	function checkForUpdate() {
		$.post("voteSession", {
			request : "update",
			clientIndex : currentIndex,
		}, function(data, status) {
			var jsonResult = JSON.parse(data);
			if(jsonResult.update == true) {
				currentIndex = jsonResult.currentIndex;
				console.log(jsonResult);
			}
		});
	}
</script>










