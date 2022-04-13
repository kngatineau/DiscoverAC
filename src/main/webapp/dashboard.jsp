<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta charset="ISO-8859-1">
<title>User Approved</title>
<link rel="stylesheet" href="style.css">
<link href="http://fonts.googleapis.com/css?family=Corben:bold" rel="stylesheet" type="text/css">
 <link href="http://fonts.googleapis.com/css?family=Nobile" rel="stylesheet" type="text/css">
 <link rel="stylesheet" href="/css/font-awesome.css">
</head>

<body  style="display: table; margin: 0 auto;text-align: left">
<div class="header"style="text-align:center;">
<h3>Welcome to DiscoverAC, ${user.getFirstName()} ${user.getLastName()}!</h3>
</div>

<form method="get" style="margin: auto; text-align: center;">
	<button class="button" type="submit" name="profile">My Profile</button>
</form>

<form method="post" style="margin: auto; text-align: center;">
	<button class="button" type="submit" name="addPost">Add a Discord</button>
</form>
 		
<div>

<c:if test= "${requestScope.bulletin != null}">
	<h4 class="titles">
	${bulletin.getBulletinName()} Bulletin Board:</h4>
	<table style="border-collapse: collapse;">
	<thead>
		<tr>
			<th>Title</th>
			<th>Description</th>
	        <th>URL</th>
	        <th>Post Date</th>
	        <th>Author</th>
	    </tr>
	</thead>
	
	<c:if test= "${requestScope.posts != null}">
	<c:forEach items="${requestScope.posts}" var="post" varStatus="loop">
		<tr id="bulletins">
			<td style="padding-right:20px;"> ${post.getTitle()}</td>
		    <td style="width:300px; padding-right:20px;"> ${post.getDescription()} </td>
		    <td style="padding-right:20px;">
		    	<a href="${post.getUrl()}" > ${post.getUrl()} </a>
		        </td>
		  	<td style="padding-right:20px;"> ${post.getPostDate().toString()} </td>
			<td style="padding-right:20px;"> ${requestScope.authors[post.getPostId().toString()]} </td>
		</tr>
	</c:forEach>
	</c:if>
	</table>
</c:if>

</div>


<%@include file="logOutFooter.jsp" %>

</body>
</html>