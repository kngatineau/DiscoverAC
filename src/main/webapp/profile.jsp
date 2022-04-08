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
<h2 >My Profile</h2>

<h3>${message}</h3>

<c:choose>
	<%-- Prints if the 'Edit' link is clicked --%>
	<c:when test="${(param.name!=null)&&(param.name=='edit')}">
		<div style="margin: auto; text-align: center;">
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
				<a href='profile?session=${param.session}&name=changePass'>Change Password</a>
				<br>
				<br>
				<input type="submit" value="Submit">
			</form>
			<form action='profile?session=${param.session}'>
				<input type="submit" value="Back to Profile">
			</form>
		</div> 
	</c:when>
	<%-- Prints if the 'Change password' link is clicked --%>
	<c:when test="${(param.name!=null)&&(param.name=='changePass')}">
		<div style="margin: auto; text-align: center;">
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
				<input type="submit" value="Submit">
				</form>
				<form action='profile?session=${param.session}'>
					<input type="submit" value="Back to Profile">
			</form>
		</div>
	</c:when>
	<%-- Prints profile info otherwise --%>	
	<c:otherwise>
		<div style="margin: auto; width: 300px; text-align: center">
		${user.getFirstName()} ${user.getLastName()}
		<br>${user.getUserName()}
		<br>${user.getEmail()}
		<br><a href='profile?session=${param.session}&name=edit'>Edit</a>
		<form method='get' action="dashboard?session=${param.session}">
			<br><input type="submit" value="Back to Bulletin Board">
		</form>
		</div>
	</c:otherwise>
	

</c:choose>


</div>



<%@include file="footer.jsp" %>
</body>
</html>