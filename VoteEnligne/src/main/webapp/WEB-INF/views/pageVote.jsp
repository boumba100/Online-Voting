<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Vote</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
<button id="button">test</button>
</body>
</html>

<script type="text/javascript">
var currentAct = null;
getCurrentAct();
function getCurrentAct() {
    $.get("getAct", function(data){
    	currentAct = data;
    	console.log(data);
    }, 'json');
}
</script>