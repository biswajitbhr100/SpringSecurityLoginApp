<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login page</title>
    <link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/static/css/common.css' />" rel="stylesheet">
</head>
<body>
<form action="#">
	<c:if test="${param.logout != null }">
		<div id="logout_div" class="alert alert-success">
			<p>Logged Out Successfully</p>
		</div>
	</c:if>
	<div class="success">
    	Greeting : ${welcome}<br>
    	<a href="/SpringSecurityLogin/user">User Login</a> |
    	<a href="/SpringSecurityLogin/admin">Admin Login</a> | 
    	<a href="/SpringSecurityLogin/db">DBA Login</a>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="<c:url value='/static/js/bootstrap.min.js' />"></script>
	<script src="<c:url value='/static/js/login.js' />"></script>
</form>
</body>
</html>