<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta charset="ISO-8859-1">
<title>DiscoverAC - Register</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
<div class="header">
<h1>DiscoverAC</h1>
</div>
<div class="main">
<h2 class="main_title">Register Account</h2>
 <form action="register" method="post">
	<table style="margin:auto;">
		<tr>
			<th id="title">First Name:</th>
				<td>
					<input type="text" name="first_name" id="first_name"></td>
		</tr>
		<tr>
			<th id="content">Last Name: </th>
				<td>
					<input type="text" name="last_name" id="last_name"></td>
		 </tr>
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
			<th id="content">AC Email: </th>
				<td>
					<input type="text" name="ac_email" id="ac_email" value="@algonquinlive.com"></td>
		 </tr>
		 <tr>
		 	<th colspan="2"><input type="submit" value="Register" id="register"></th>
		 </tr>
	</table>
	</form>	
	 <form action="login" style="margin:auto; text-align:center;">
    <button class="button" type="submit">Back to Login Page</button>
 </form>
 </div>
 
<%@include file="footer.jsp" %>
</body>
</html>