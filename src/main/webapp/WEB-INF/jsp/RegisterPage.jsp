<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register here</title>
</head>
<body>
 <input type="email"  value="" placeholder="Username" id="username" autocomplete="off">
  <input type="password" value="" placeholder="Passcode" id="passcode" autocomplete="off">
  
  <button type="button" onclick="confirm();"> Confirm Registration </button> 

</body>
<script type="text/javascript">

function confirm(){
	
	var username=document.etElementById("username").value;
	var passcode=document.getElementById("passcode");
	
}

</script>
</html>