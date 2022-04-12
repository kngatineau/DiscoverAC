<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="http://fonts.googleapis.com/css?family=Corben:bold" rel="stylesheet" type="text/css">
 <link href="http://fonts.googleapis.com/css?family=Nobile" rel="stylesheet" type="text/css">
 <link rel="stylesheet" href="/css/font-awesome.css">
 </head>
<body>


<div>
<footer class="footer">
 
  <br>
  
  <form action="logout?session=${session}" method="post">
  
  <br>
        <p>
            Please come back again soon
            ${user.getFirstName()}!</p>
       
        <br> <input class="button" type="submit" value="Logout"/>
    </form>
  
</footer>
</div>
</body>
</html>