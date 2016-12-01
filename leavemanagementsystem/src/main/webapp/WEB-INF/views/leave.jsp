<%@ page language="java" contentType="text/html; ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>

<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" href="/resources/css/bootstrap.css"/>
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css"/>
    <link rel="stylesheet" href="/resources/calander/themes/default.css"/>
    <link rel="stylesheet" href="/resources/calander/themes/default.date.css"/>
    <meta name="google-signin-client_id" content="862712159345-ti9la1n9c7vtj95516st4q3nf4kt68rc.apps.googleusercontent.com">

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.css">

    <!-- Website CSS style -->
    <link rel="stylesheet" type="text/css" href="assets/css/main.css">

    <!-- Website Font style -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

    <!-- Google Fonts -->
    <link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>


    <script>
        function onLoad() {
            gapi.load('auth2', function() {
                gapi.auth2.init();
            });
        }
        function signOut() {
            var auth2 = gapi.auth2.getAuthInstance();
            auth2.signOut().then(function () {
                console.log('User signed out.');
            });
        }
    </script>
    <script src="/resources/calander/picker.js"></script>
    <script src="/resources/calander/picker.date.js"></script>

    <meta charset="UTF-8">
    <title>Add an leave</title>
    <style>
        body, html{
            height: 100%;
            background-repeat: no-repeat;
            background-color: #d3d3d3;
            font-family: 'Oxygen', sans-serif;
        }

        .main{
            margin-top: 10px;
        }

        h1.title {
            font-size: 50px;
            font-family: 'Passion One', cursive;
            font-weight: 400;
        }

        hr{
            width: 10%;
            color: #fff;
        }

        .form-group{
            margin-bottom: 15px;
        }

        label{
            margin-bottom: 15px;
        }

        input,
        input::-webkit-input-placeholder {
            font-size: 11px;
            padding-top: 3px;
        }

        .main-login{
            background-color: #fff;
            /* shadows and rounded borders */
            -moz-border-radius: 2px;
            -webkit-border-radius: 2px;
            border-radius: 2px;
            -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
            -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
            box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);

        }

        .main-center{
            margin-top: 10px;
            margin: 0 auto;
            max-width: 500px;
            padding: 40px 40px;

        }

        .login-button{
            margin-top: 5px;
        }

        .login-register{
            font-size: 11px;
            text-align: center;
        }



        td {
            padding: 5%;
        }

        .container {
            padding: auto;
            margin-left: 5%;
            margin-right: 5%;
        }
    </style>
</head>
<body>

<div id="nav">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Leave Notifier</a>
            </div>
            <ul class="nav navbar-nav">
                <li ><a href="../home">Home</a></li>
                <li class="active"><a href="#">Leave</a></li>
                <li ><a id="alluserleaves">Company Leave Analyzing</a></li>
                <li ><a href="../registration">User registration</a></li>
            </ul>
            <script type="text/javascript">
                let year = new Date().getFullYear();
                document.getElementById("alluserleaves").href="../users/graph/"+year;
            </script>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <form id="logoutForm" method="POST" action="${contextPath}/logout">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                    <li><a  id="profileData" style="color: white;text-align: center">${pageContext.request.userPrincipal.name}</a></li>
                    <li><a  onclick="document.forms['logoutForm'].submit()"><span class="glyphicon glyphicon-off"></span> Sign Out</a></li>
                </c:if>
                <script type="text/javascript">

                    let id='${userId}';
                    document.getElementById("profileData").href="../users/"+id+"/"+year+"/graph";
                </script>

            </ul>
        </div>
    </nav>

</div>

<div class="container">
    <div class="row main">
        <div class="panel-heading">
            <div class="panel-title text-center">
                <h1 class="title">Leave Form</h1>
            </div>
        </div>
        <div class="main-login main-center">

            <form:form class="form-horizontal" action="/leave/update" method="post"  commandName="leave" id="userform">

                <div class="form-group">
                    <label for="name" class="cols-sm-2 control-label">User Name</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <from:input  class="form-control" name="name"  list="namelist"  path="name"  placeholder="Enter your Name"/>
                            <datalist id="namelist">
                                <c:forEach var="user" items="${users}">
                                    <option>${user.userName}</option>
                                </c:forEach>
                            </datalist>

                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label  class="cols-sm-2 control-label">Leave Type</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-flash fa" aria-hidden="true"></i></span>

                            <form:input list="leaveTypeList" path="leaveType" class="form-control" placeholder="Enter your Leaveype"/>
                            <datalist id="leaveTypeList">
                                <c:forEach var="leaveType" items="${leaveTypes}">
                                    <option>${leaveType}</option>
                                </c:forEach>
                            </datalist>

                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label  class="cols-sm-2 control-label">Reason To Leave</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-asterisk fa" aria-hidden="true"></i></span>
                            <form:input path="reasonToLeave" list="listOfReasonsToLeaveList"
                                        placeholder="select reasonToLeave"  class="form-control"/>
                            <datalist id="listOfReasonsToLeaveList">
                                <c:forEach var="reasonToLeave" items="${listOfReasonsToLeave}">
                                    <option>${reasonToLeave}</option>
                                </c:forEach>
                            </datalist>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label  class="cols-sm-2 control-label">Leave Date</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-time fa-lg" aria-hidden="true"></i></span>
                            <form:input path="leaveDate" class="form-control" type="date" placeholder="date of leave"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label  class="cols-sm-2 control-label">Comment</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-book fa-lg" aria-hidden="true"></i></span>
                            <form:textarea path="comment" class="form-control" placeholder="any comments"></form:textarea>
                        </div>
                    </div>
                </div>

                <div class="form-group ">
                    <form:button class="btn btn-success" type="submit">submit</form:button>
                    <form:button class="btn" type="clear">clear</form:button>

                </div>

            </form:form>
        </div>
    </div>
</div>

<script type="text/javascript" src="assets/js/bootstrap.js"></script>
<script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>

</body>
</html>
