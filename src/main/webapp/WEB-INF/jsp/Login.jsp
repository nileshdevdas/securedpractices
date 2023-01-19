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

	<input type="email" onkeypress="ValidLogin(event);"
		class="form-control" value="" placeholder="Username" id="Username"
		autocomplete="off">
	
		<br> <br>

	<input type="password" onkeypress="ValidLogin(event);"
		class="form-control" value="" placeholder="Password" id="UserPassword"
		autocomplete="off">
		
		<br> <br>	
	<button type="button" class="btn btn-primary block full-width m-b" id ="btnlogin" >Login</button>
	
	<br><br><br>
	
	<a href ="https://www.amazon.in/"> <h1>  Click here to Collect amount </h1> </a> 
</body>
</html>