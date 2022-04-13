<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta charset="ISO-8859-1">
<title>DiscoverAC - Add a Discord</title>
<link rel="stylesheet" href="style.css">

<link href="http://fonts.googleapis.com/css?family=Corben:bold" rel="stylesheet" type="text/css">
 <link href="http://fonts.googleapis.com/css?family=Nobile" rel="stylesheet" type="text/css">
 <link rel="stylesheet" href="/css/font-awesome.css">
</head>
<body>
<div class="header">
<h1>DiscoverAC</h1>
</div>

 <c:if test= "${error}">
	 <div class="error">
	 <h3 style="margin:auto; text-align:center; color:red">Please fill out all required fields.</h3>
	 </div>
 </c:if>

<div class="main">
<h2 class="main_title">Add a Discord!</h2>
 <form method="post" action="addPost">
	<table style="margin:auto;">
		<tr>
			<th>Title:</th>
				<td>
					<input type="text" name="discord_title" id="discord_title" required></td>
		</tr>
		<tr>
			<th>Description: </th>
				<td>
					<input type="text" name="discord_desc" id="discord_desc" required></td>
		 </tr>
		 		<tr>
			<th>URL: </th>
				<td>
					<input type="text" name="discord_url" id="discord_url" required></td>
		 </tr>
		 <tr>
			<th> </th>
				<td>
					<input type="hidden" value="${session}" name="SID_post"></td>
		 </tr>
	
		
		 <tr>
		 	<th colspan="2"><input class="button" type="submit" value="Add post" id="add_post"></th>
		 </tr>
	</table>
	</form>	
	<form method='get' action="dashboard?session=${session}">
			<br><input class="button" type="submit" value="Back to Bulletin Board">
		</form>
 </div>
<%@include file="logOutFooter.jsp" %>
</body>
</html>