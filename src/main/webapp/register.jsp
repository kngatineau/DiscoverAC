<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta charset="ISO-8859-1">
<title>DiscoverAC - Register</title>
<link rel="stylesheet" href="style.css">

<link href="http://fonts.googleapis.com/css?family=Corben:bold" rel="stylesheet" type="text/css">
 <link href="http://fonts.googleapis.com/css?family=Nobile" rel="stylesheet" type="text/css">
 <link rel="stylesheet" href="/css/font-awesome.css">
</head>
<body>
<div class="header">
<h1>DiscoverAC</h1>
</div>

 <c:if test= "${userNameExists}">
	 <div class="error">
	 <h3 style="margin:auto; text-align:center; color:red">User name already exists. Please try again!</h3>
	 </div>
 </c:if>
  <c:if test= "${userEmailExists}">
	 <div class="error">
	 <h3 style="margin:auto; text-align:center; color:red">An account already exists with that name. Please try again!</h3>
	 </div>
 </c:if>

<div class="main">
<h2 class="main_title">Register Account</h2>
 <form action="register" method="post">
	<table style="margin:auto;">
		<tr>
			<th id="content">First Name:</th>
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
		 	<th colspan="2"><input class="button" type="submit" value="Register" id="register"></th>
		 </tr>
	</table>
	</form>	
	 <form action="login" style="margin:auto; text-align:center;">
    <button class="button" type="submit">Back to Login Page</button>
 </form>
 </div>
 
<%@include file="logOutFooter.jsp" %>
</body>
</html>