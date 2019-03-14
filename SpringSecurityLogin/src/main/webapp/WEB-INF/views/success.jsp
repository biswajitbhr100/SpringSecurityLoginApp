<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Registration Successful</title>
    <link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/static/css/common.css' />" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="<c:url value='/static/js/bootstrap.min.js' />"></script>
    <script src="<c:url value='/static/js/login.js' />"></script>
</head>
<body>
	<div class="success">
    	Confirmation : ${success}<br>
    	<sec:authorize access="hasRole('ADMIN')">
        	Add Another User : <a href="<c:url value='/registration' />">Add More Users</a> | 
        	<a href="<c:url value='/admin' />">Admin Page</a> | <a href="<c:url value="/logout" />">Logout</a>
        </sec:authorize>
        <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
        	<a href="<c:url value='/user' />">Login</a>
        </sec:authorize>
    </div>
</body>
</html>