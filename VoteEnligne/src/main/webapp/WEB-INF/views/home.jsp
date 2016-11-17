<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<link href="<c:url value="/resources/home.css" />" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
	<div class="wrapper">
		<h1>Bonjour!</h1>
		<input class="buttonHolder" type="button" value="VOTEZ ICI!"
			onclick='enterVoteSession()'>

		<form method="POST" id="changePageForm">
			<input class="button" id="chosenPage" name="chosenPage" type="hidden" /></br>
			<input class="codeInput" type="text" placeholder="code"
				id="sessionCodeInput" name="code" /></br>
				<span id="errorMessage"></span>
		</form>
	</div>
</body>
</html>

<script type="text/javascript">
	function enterVoteSession() {
		$.post("", {
			sessionCode : document.getElementById('sessionCodeInput').value
		}, function(data, status) {
			var result = JSON.parse(data);
			if (result.success == true) {
				goToVotePage();
			} else {
				console.log("test " + result.message);
				document.getElementById('errorMessage').textContent = result.message;
			}

		});

		function goToVotePage() {
			window.location = "pageVote";
		}
	}
</script>
