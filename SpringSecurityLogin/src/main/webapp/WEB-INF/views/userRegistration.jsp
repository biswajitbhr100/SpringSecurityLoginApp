<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Admin User Add</title>

    <link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/static/css/common.css' />" rel="stylesheet">
</head>

<body>

<div class="container">

    <form:form method="POST" modelAttribute="user" class="form-signin">
 		<h2 class="form-signin-heading">Add User</h2>
 		<spring:bind path="id">
 			<form:input type="hidden" path="id" id="id"/>
 		</spring:bind>
        <spring:bind path="name">
            <div class="form-group">
                <form:input type="text" path="name" class="form-control" placeholder="Full Name"
                            autofocus="true"></form:input>
                <div class="has-error">
                	<form:errors path="name" class="help-inline"/>
                </div>
            </div>
        </spring:bind>
        <spring:bind path="ssoId">
            <div class="form-group">
                <form:input type="text" path="ssoId" class="form-control" placeholder="Username"
                            autofocus="true"></form:input>
                <div class="has-error">
                	<form:errors path="ssoId" class="help-inline"/>
                </div>
            </div>
        </spring:bind>
 
        <spring:bind path="password">
            <div class="form-group">
                <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                <div class="has-error">
                	<form:errors path="password" class="help-inline"/>
                </div>
            </div>
        </spring:bind>

        <spring:bind path="mail">
            <div class="form-group">
                <form:input type="text" path="mail" class="form-control" placeholder="Email"
                            autofocus="true"></form:input>
                <div class="has-error">
                	<form:errors path="mail" class="help-inline"/>
                </div>
            </div>
        </spring:bind>
        <sec:authorize access="hasRole('ADMIN')">
		 <spring:bind path="role">
            <div class="form-group">
                <form:select path="role" items="${roles}" multiple="true" itemValue="id" itemLabel="type" class="form-control input-sm"/>
                <div class="has-error">
                	<form:errors path="role" class="help-inline"/>
                </div>
            </div>
        </spring:bind>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
        <c:choose>
        	<c:when test="${edit}">
        		<button class="btn btn-lg btn-primary btn-block" type="submit">Update</button>
        		<a href="<c:url value='/users' />" class="btn btn-lg btn-danger btn-block">Back</a>
        	</c:when>
        	<c:otherwise>
        		<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
        		<a href="<c:url value='/admin' />" class="btn btn-lg btn-danger btn-block">Cancel</a>
        	</c:otherwise>
        </c:choose>
    	</sec:authorize>
    	<sec:authorize access="hasRole('ROLE_ANONYMOUS')">
    		<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
        	<a href="<c:url value='/user' />" class="btn btn-lg btn-danger btn-block">Cancel</a>
    	</sec:authorize>
    </form:form>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="<c:url value='/static/js/bootstrap.min.js' />"></script>
</body>
</html>