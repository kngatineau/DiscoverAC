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

<div>
<h2 class="titles">My Profile</h2>

<h3>${message}</h3>

<c:choose>
	<%-- Prints if the 'Edit' link is clicked --%>
	<c:when test="${(param.name!=null)&&(param.name=='edit')}">
		<div class="profile_text">
			<form method="get">
				<table style="margin: auto; text-align: center;">
				<tr>
				<td><label for="fname">First Name: </label></td>
				<td><input type="text" id="fname" name="fname" value="${user.getFirstName()}"></td>
				</tr>
				<tr>
				<td><label for="fname">Last Name: </label></td>
				<td><input type="text" id="lname" name="lname" value="${user.getLastName()}"></td>
				</tr>
				<tr>
				<td><label for="uname">Username: </label></td>
				<td><input type="text" id="uname" name="uname" value="${user.getUserName()}"></td>
				</tr>
				</table>
				${user.getEmail()}
				<br>
				<a href='profile?session=${session}&name=changePass'>Change Password</a>
				<br>
				<br>
				<input class="button" type="submit" value="Submit">
			</form>
			<form action='profile?session=${session}'>
				<input class="button" type="submit" value="Back to Profile">
			</form>
			<br>
		</div> 
	</c:when>
	<%-- Prints if the 'Change password' link is clicked --%>
	<c:when test="${(param.name!=null)&&(param.name=='changePass')}">
		<div class="profile_text">
			<form method="post">
				<table style="margin: auto; text-align: center;">
				<tr>
				<td><label for="currentPass">Current Password: </label></td>
				<td><input type="text" id="currentPass" name="currentPass"><td>
				</tr>
				<tr>
				<td><label for="newPass">New Password: </label></td>
				<td><input type="text" id="newPass" name="newPass"><td>
				</tr>
				<tr>
				<td><label for="confirmPass">Confirm Password: </label></td>
				<td><input type="text" id="confirmPass" name="confirmPass"><td>
				</tr>
				</table>
				<br>
				<input class="button" type="submit" value="Submit">
				</form>
				<form action='profile?session=${session}'>
					<input class="button" type="submit" value="Back to Profile">
			</form>
		</div>
	</c:when>
	<%-- Prints profile info otherwise --%>	
	<c:otherwise>
		<div class="profile_text">
		${user.getFirstName()} ${user.getLastName()}
		<br>${user.getUserName()}
		<br>${user.getEmail()}
		<br><a href='profile?session=${session}&name=edit'>Edit</a>
		<form method='get' action="dashboard?session=${session}">
			<br><input class="button" type="submit" value="Back to Bulletin Board">
		</form>
		</div>
	</c:otherwise>
	

</c:choose>


</div>



<%@include file="logOutFooter.jsp" %>
</body>
</html>