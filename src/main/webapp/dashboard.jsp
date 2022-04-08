<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta charset="ISO-8859-1">
<title>User Approved</title>
<link rel="stylesheet" href="style.css">
</head>

<body  style="display: table; margin: 0 auto;text-align: left">
<div class="header"style="text-align:center;">
<h3 style="color:green">Welcome to DiscoverAC, ${user.getFirstName()} ${user.getLastName()}!</h3>
</div>

<form method="get" style="margin: auto; text-align: center;">
	<button type="submit" name="profile">My Profile</button>
</form>
 		
<div>

<c:if test= "${requestScope.bulletin != null}">
	<h4 style="text-align:center; font-size: 25px;">
	${bulletin.getBulletinName()} Bulletin Board:</h4>
	<table style='border-collapse: collapse;'>
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
		<tr style="height:100px; background-color:#ccedc5; border-bottom: 10px solid white;">
			<td style="padding-right:20px;"> ${post.getTitle()}()</td>
		    <td style="width:300px; padding-right:20px;"> ${post.getDescription()} </td>
		    <td style="padding-right:20px;">
		    	<a href="${post.getUrl()}" > ${post.getUrl()} </a>
		        </td>
		  	<td style="padding-right:20px;"> ${post.getPostDate().toString()} </td>
			<td style="padding-right:20px;"> ${requestScope.authors[post.getPostId().toString()]} </td>
		</tr>
	</c:forEach>
	</c:if>
</c:if>

</div>


<div class="footer">
  <p>Where Algonquin Discords are discovered</p>
</div>

</body>
</html>