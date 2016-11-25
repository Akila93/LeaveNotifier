<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
    <meta name="author" content="">
    <meta name="google-signin-client_id" content="862712159345-ti9la1n9c7vtj95516st4q3nf4kt68rc.apps.googleusercontent.com">

    <title>Log in with your account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<style>
    .has-background-img  {
        background-image: url("http://193.87.95.148/openwrt/rpi_cross/rootfs/usr/share/images/desktop-base/login-background.svg");
        background-color: #cccccc;
        padding: 0%;
    }
    .has-background-img-form  {
        background-image: url("https://moltin.com/addons/shared_addons/themes/moltinTwo/img/bg6.svg");
        background-color: #cccccc;
        border-color: #3c3c3c;
        color: white;
        border-radius: 2%;
    }
</style>

<body class="has-background-img">



<div class="container-fluid" >

    <div id="nav" style="width: 100%">
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Leave Notifier</a>
                </div>
                <ul class="nav navbar-nav navbar-right">


                </ul>
            </div>
        </nav>

    </div>
    <%--<form method="POST" action="${contextPath}/login" class="form-signin">--%>
        <%--<h2 class="form-heading">Log in</h2>--%>

        <%--<div class="form-group ${error != null ? 'has-error' : ''}">--%>

            <%--<div class="row">--%>
            <%--<div class="col-sm-3"></div>--%>
            <%--<div class="col-sm-6 well has-background-img-form" style="padding: 2%">--%>
                <%--<div class="row" style="padding: 2%">--%>
                    <%--<span>${message}</span>--%>
                <%--</div>--%>
                <%--<div class="row" style="padding: 2%">--%>
                    <%--<input id="username" name="username" type="text" class="form-control" placeholder="Username"--%>
                                        <%--autofocus="true"/>--%>
                <%--</div>--%>
                <%--<div class="row" style="padding: 2%">--%>
                    <%--<input id="password" name="password" type="password" class="form-control" placeholder="Password"/>--%>
                <%--</div>--%>
                <%--<div class="row" style="padding: 2%">--%>
                    <%--<span>${error}</span>--%>
                <%--</div>--%>
                <%--<div class="row" style="padding: 2%">--%>
                    <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
                <%--</div>--%>
                <%--<div class="row" style="padding: 2%">--%>
                    <%--<button id="btnSubmit" class="btn btn-lg btn-primary btn-block" style="margin-left:25%;margin-right: 25%;width: 50%" type="submit">Log In</button>--%>
                <%--</div>--%>
                <%--<div class="row" style="padding: 2%">--%>
                    <%--<h4 class="text-center"><a href="${contextPath}/registration">Create an account</a></h4>--%>
                <%--</div>--%>

                <%--</div>--%>
            <%--<div class="col-sm-3"></div>--%>
            <%--</div>--%>



        <%--</div>--%>

    <%--</form>--%>
    <%--<script type="text/javascript">--%>
        <%--let userName="";--%>
        <%--let password="";--%>
        <%--userName='${username}';--%>
        <%--password='${password}';--%>
        <%--//console.log("user",userName);--%>
        <%--//console.log("password",password);--%>
        <%--if(userName != ""&& password!="") {--%>
            <%--document.getElementById("username").value = userName;--%>
            <%--document.getElementById("password").value = password;--%>
            <%--$('#btnSubmit').trigger('click');--%>
        <%--}--%>
    <%--</script>--%>
    <style type="text/css">
        #my-signin2{
            margin-left: 45%;
            margin-right: 45%;
        }
        .center {
            margin:auto;!important;
        }
    </style>
    <div class="row">
        <div class="center">
            <div id="my-signin2"></div>
        </div>

        <script>
            function onSuccess(googleUser) {
                let id_token = googleUser.getAuthResponse().id_token;
                let xhr = new XMLHttpRequest();
                xhr.open('POST', 'http://localhost:9099/google-login');
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                xhr.onload = function() {
                    console.log('Signed in as: ' + xhr.responseText+"\nLocation:"+xhr.getResponseHeader("Location"));

                    window.location.assign(xhr.getResponseHeader("Location"));
                };
                xhr.send('idtoken=' + id_token);

            }
            function onFailure(error) {
                console.log(error);
            }
            function renderButton() {
                gapi.signin2.render('my-signin2', {
                    'scope': 'profile email',
                    'width': 240,
                    'height': 50,
                    'longtitle': true,
                    'theme': 'dark',
                    'onsuccess': onSuccess,
                    'onfailure': onFailure
                });
            }
        </script>


    </div>


</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
