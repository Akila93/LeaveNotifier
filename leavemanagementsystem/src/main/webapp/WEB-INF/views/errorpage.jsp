<%@ page language="java" contentType="text/html; ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!doctype html>
<html lang="en">
<head>
    <link rel="icon" type="image/ico" href="/resources/images/logo-tab.ico" sizes="16x16">
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
            </ul>
        </div>
    </nav>

</div>

<h1 style="text-align: center;color: red">Exception : '${errorName}'</h1>
<h3 style="text-align: center;color: red"> Error Status  '${errorCode}' </h3>
<h3 style="text-align: center;color: #36a6ff"> '${errorDetail}'</h3>

<div class="row">
    <div class="col-sm-4"></div>
    <img class="col-sm-4" src="http://www.polyvore.com/cgi/img-thing?.out=jpg&size=l&tid=141000776"/>
    <div class="col-sm-4"></div>
</div>


<div class="row" style="margin-top: 1%">
    <div class="col-sm-4"></div>
    <div class="btn btn-info col-sm-4" onclick="gotoLoginPage()">Return to login</div>
    <div class="col-sm-4"></div>
</div>

<script>
    function gotoLoginPage(){
        window.location.assign("http://localhost:9099/login");
    }
</script>
</body>
<script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
</html>