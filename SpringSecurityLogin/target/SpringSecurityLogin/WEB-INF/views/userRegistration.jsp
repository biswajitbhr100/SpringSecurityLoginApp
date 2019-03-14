<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Create an account</title>

    <link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/static/css/common.css' />" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container">

    <form:form method="POST" modelAttribute="user" class="form-signin">
        <h2 class="form-signin-heading">Create your account</h2>
        <spring:bind path="name">
        	<form:input type="text" path="name" id="name" class="form-control" placeholder="Name"
                            autofocus="true"></form:input>
            <div class="has-error">
                <form:errors path="name" class="help-inline"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="ssoId">
        	<form:input type="text" path="ssoId" id="ssoId" class="form-control" placeholder="Username"
                            autofocus="true"></form:input>
            <div class="has-error">
                <form:errors path="ssoId" class="help-inline"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="password">
        	<form:input type="password" path="password" id="password" class="form-control" placeholder="Password"
                            autofocus="true"></form:input>
            <div class="has-error">
                <form:errors path="password" class="help-inline"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="email">
        	<form:input type="text" path="email" id="email" class="form-control" placeholder="Email"
                            autofocus="true"></form:input>
            <div class="has-error">
                <form:errors path="email" class="help-inline"></form:errors>
            </div>
        </spring:bind>
        <sec:authorize access="hasRole('ADMIN')">
        	<spring:bind path="role">
        		<form:select path="role" items="${roles}" multiple="true" itemValue="id" itemLabel="type"></form:select>
            	<div class="has-error">
                	<form:errors path="email" class="help-inline"></form:errors>
            	</div>
        	</spring:bind>
        </sec:authorize>
        <sec:authorize access="hasRole('ADMIN')">
        	<input type="submit" value="Register" class="btn btn-lg btn-primary btn-block" />or <a href="<c:url value='/admin' />">Cancel</a>
        </sec:authorize>
        <sec:authorize access="hasRole('USER')">
        	<input type="submit" value="Register" class="btn btn-lg btn-primary btn-block" />or <a href="<c:url value='/user' />">Cancel</a>
        </sec:authorize>
    </form:form>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="<c:url value='/static/js/bootstrap.min.js' />"></script>
</body>
</html>