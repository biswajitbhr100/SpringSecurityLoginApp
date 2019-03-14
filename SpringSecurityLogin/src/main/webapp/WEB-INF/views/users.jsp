<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
	<title>Manage Users</title>
	<link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/static/css/common.css' />" rel="stylesheet">
</head>
<body>
	<div class="container">
		<dir class="success">Hello <strong>${loggedinuser}</strong>,Welcome to UserManagement <a href="<c:url value='/logout' />">Logout</a></dir>
		<div class="panel-heading"><span class="lead">Users</span></div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Name</th>
					<th>Email</th>
					<th>Username</th>
					<th>Roles</th>
					<sec:authorize access="hasRole('ADMIN')">
						<th width=100></th>
					</sec:authorize>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.name}</td>
						<td>${user.mail}</td>
						<td id="username">${user.ssoId}</td>
						<c:forEach items="${user.role}" var="rol">
							<td>${rol.type}</td>
						</c:forEach>
						<td><a href="<c:url value='/editUser/${user.ssoId}'/>" class="btn btn-success custom-width">Edit</a></td>
						<sec:authorize access="hasRole('ADMIN')">
							<c:choose>
								<c:when test="${loggedinuser == user.ssoId}">
									<td><a id="delete" href="<c:url value='/deleteUser/${user.ssoId}'/>" class="btn btn-danger custom-width disabled">Delete</a></td>
								</c:when>
								<c:otherwise>
									<td><a id="delete" href="<c:url value='/deleteUser/${user.ssoId}'/>" class="btn btn-danger custom-width">Delete</a></td>
								</c:otherwise>
							</c:choose>
							
						</sec:authorize>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<sec:authorize access="hasRole('ADMIN')">
		<div class="well">
			<a href="<c:url value='/registration' />">Add New User</a>
		</div>
	</sec:authorize>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="<c:url value='/static/js/bootstrap.min.js' />"></script>
</body>
</html>