<%@ page language="java" contentType="text/html; ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" href="/resources/css/bootstrap.css"/>
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css"/>
    <meta name="google-signin-client_id" content="862712159345-ti9la1n9c7vtj95516st4q3nf4kt68rc.apps.googleusercontent.com">
    <meta charset="UTF-8">
    <title>Leave Notifier</title>
</head>
<body>
<div id="nav">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Leave Notifier <small><spring:message code="home.page.title"/></small></a>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li><a  style="color: white;text-align: center">${pageContext.request.userPrincipal.name}</a></li>
                <li><a href="../../"><span class="glyphicon glyphicon-off"></span> Sign Out</a></li>
            </ul>
        </div>
    </nav>

</div>

<form id="gForm" method="POST" action="${contextPath}/login" class="form-signin">
    <h2 class="form-heading">Log in</h2>
    <div class="form-group ${error != null ? 'has-error' : ''}">
        <div class="row">
            <div class="col-sm-3"></div>
            <div class="col-sm-6 well has-background-img-form" style="padding: 2%">
                <div class="row" style="padding: 2%">
                    <span>${message}</span>
                </div>
                <div class="row" style="padding: 2%">
                    <input id="username" name="username" type="text" class="form-control" placeholder="Username"
                           autofocus="true"/>
                </div>
                <div class="row" style="padding: 2%">
                    <input id="password" name="password" type="password" class="form-control" placeholder="Password"/>
                </div>
                <div class="row" style="padding: 2%">
                    <span>${error}</span>
                </div>
                <div class="row" style="padding: 2%">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </div>
                <div class="row" style="padding: 2%">
                    <button id="btnSubmit" class="btn btn-lg btn-primary btn-block" style="margin-left:25%;margin-right: 25%;width: 50%" type="submit">Log In</button>
                </div>
                <div class="row" style="padding: 2%">
                    <h4 class="text-center"><a href="${contextPath}/registration">Create an account</a></h4>
                </div>

            </div>
            <div class="col-sm-3"></div>
        </div>
    </div>
</form>

<script type="text/javascript">
    let userName = '';
    let password ='';
    userName = '${userName}';
    password ='${password}';
    if(userName!=""){
        document.getElementById("username").value = '${userName}';
        document.getElementById("password").value = '${password}';
    }

    //$('#btnSubmit').trigger('click');
</script>
<h1>wellcome to the google-login user: '${userName}', pass: '${password}' </h1>
</div>
</body>

</html>