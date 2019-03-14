<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<style type="text/css">
	.err{
		color : red;
	}
</style>
</head>
<body>
	<form action="/SpringSecurityLogin/login" method="post">
		
		<c:if test="${param.error != null }">
			<p class="error">Invalid Username/Password</p>
		</c:if>
		<label for="username" >USERNAME : </label>
		<input type="text" id="username" name="ssoId" placeholder="Enter Username" required>
		<label for="password" >PASSWORD : </label>
		<input type="password" id="password" name="password" placeholder="Enter Password" required>
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
		<input type="submit" value="Login"> 
	</form>
</body>
</html>