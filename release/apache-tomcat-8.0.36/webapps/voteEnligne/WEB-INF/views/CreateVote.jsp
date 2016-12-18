<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>creation</title>
<link href="<c:url value="/resources/createVote.css" />"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
	<form id="uploadFile" method="POST">
		<textarea name="actsString" cols="100" rows="20"></textarea></br>
		Code : <input type="text" name="code"/></br>
		Code de passe : <input type="password" name="adminCode"/>
	</form>
	<input type="button" value="submit" onclick="submitFile()">
</body>
</html>

<script type="text/javascript">
function submitFile() {
	document.getElementById('uploadFile').submit();
}
	
</script>