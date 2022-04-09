<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DiscoverAC - Login</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<div class="header">
<h1>DiscoverAC</h1>
 </div>
 
 <div class="main">
 <form action="login" method="post">
	<table style="margin:auto;">

		 <tr>
			<th id="content">Username: </th>
				<td>
					<input type="text" name="username" id="username"></td>
		 </tr>
		 		 		<tr>
			<th id="content">Password: </th>
				<td>
					<input type="text" name="password" id="password"></td>
		 </tr>
		 		 		
		 <tr>
		 	<th colspan="2"><input class="button" type="submit" value="Login" id="login"></th>
		 </tr>
		 <tr>
		 </tr>
	</table>
 </form>	
 
 <form action="register" style="margin:auto; text-align:center;">
 
    <button class="button" type="submit" style="margin:auto; text-align:center;">Register</button>
    
 </form>
 </div>
 <br>
 
<%@include file="footer.jsp" %>
</body>
</html>