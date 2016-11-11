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
			onclick='goTo(VOTE_PAGE)'>

		<form method="POST" id="changePageForm">
			<input class="button" id="chosenPage" name="chosenPage" type="hidden" /></br>
			<input class="codeInput" type="text" placeholder="code" name="code" />
		</form>
	</div>
</body>
</html>

<script type="text/javascript">
	var VOTE_PAGE = "pageVote";
	function goTo(page) {
		document.getElementById('chosenPage').value = VOTE_PAGE;
		document.getElementById("changePageForm").submit();
	}
</script>
