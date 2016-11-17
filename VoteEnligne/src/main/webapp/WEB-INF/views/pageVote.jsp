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
getActs();
function getActs() {
	$.post("voteSession", {
		request : "getActNames"
	}, function(data, status) {
		var jsonResult = JSON.parse(data);
		console.log(data);
	});
	
}
</script>