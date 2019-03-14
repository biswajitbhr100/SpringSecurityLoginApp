<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Log in with your account</title>

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
	<c:url var="loginUrl" value="/login" />
    <form method="POST" action="${loginUrl}" class="form-signin">
        <h2 class="form-heading">Log in</h2>

        <div class="form-group">
        	<c:if test="${param.logout != null }">
				<div id="logout_div" class="alert alert-success">
					<p>Logged Out Successfully</p>
				</div>
			</c:if>
            <c:if test="${param.error != null}">
            	<div class="alert alert-danger">
                	<p>Invalid username and password.</p>
                </div>
            </c:if>
            <input name="ssoId" id="username" type="text" class="form-control" placeholder="Username"
                   autofocus="true"/>
            <input name="password" id="password" type="password" class="form-control" placeholder="Password"/>
            <label><input type="checkbox" id="rememberme" name="remember-me"> Remember Me</label>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
            <h4 class="text-center"><a href="<c:url value='/register' />">Create an account</a></h4>
        </div>

    </form>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="<c:url value='/static/js/bootstrap.min.js' />"></script>
<script src="<c:url value='/static/js/login.js' />"></script>
</body>
</html>