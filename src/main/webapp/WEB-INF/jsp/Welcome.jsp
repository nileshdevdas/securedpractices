<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</head>
<body>
<h1> Hello </h1>

<button type="button" class="btn btn-primary block full-width m-b" id ="btnlogout" onclick="logout();">Logout </button>



</body>
<script >

	
function logout(){

	window.location.href="login";
	
}



</script>
</html>