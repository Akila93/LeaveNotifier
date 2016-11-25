<%@ page language="java" contentType="text/html; ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" href="/resources/css/bootstrap.css"/>
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css"/>
    <link rel="stylesheet" href="/resources/calander/themes/default.css"/>
    <link rel="stylesheet" href="/resources/calander/themes/default.date.css"/>
    <meta name="google-signin-client_id" content="862712159345-ti9la1n9c7vtj95516st4q3nf4kt68rc.apps.googleusercontent.com">
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
                <li ><a id="alluserleaves">User Leave Analyzing</a></li>
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
                    <li><h6>${pageContext.request.userPrincipal.name}</h6></li>
                    <li><a  onclick="document.forms['logoutForm'].submit()"><span class="glyphicon glyphicon-off"></span> Sign Out</a></li>
                </c:if>

            </ul>
        </div>
    </nav>

</div>

<div class="container">
    <form:form action="/leave/update" method="post" cssClass="form" commandName="leave" id="userform" class="well">
        <!-- form element for user name-->
        <table>
            <tbody>
            <tr>
                <td>
                    <h6> Name : </h6>
                </td>
                <td>
                    <form:input list="namelist" placeholder="enter name" path="name"/>
                    <datalist id="namelist">
                        <c:forEach var="user" items="${users}">
                            <option>${user.userName}</option>
                        </c:forEach>
                    </datalist>
                </td>
            </tr>

            <!-- form element for leave type-->
            <!-- comment this and submit will work-->
            <tr>
                <td>
                    <h6> Status : </h6>
                </td>
                <td>
                    <form:input list="leaveTypeList" placeholder="enter leaveType" path="leaveType"/>
                    <datalist id="leaveTypeList">
                        <c:forEach var="leaveType" items="${leaveTypes}">
                            <option>${leaveType}</option>
                        </c:forEach>
                    </datalist>

                </td>
            </tr>

            <tr>
                <td>
                    <h6>Reason To Leave : </h6>
                </td>
                <td>
                    <form:input path="reasonToLeave" list="listOfReasonsToLeaveList"
                                placeholder="select reasonToLeave"/>
                    <datalist id="listOfReasonsToLeaveList">
                        <c:forEach var="reasonToLeave" items="${listOfReasonsToLeave}">
                            <option>${reasonToLeave}</option>
                        </c:forEach>
                    </datalist>
                </td>
            </tr>
            <tr>
                <td>
                    <h6>Leave Date : </h6>
                </td>
                <td>
                    <form:input path="leaveDate" type="date" placeholder="date of leave"/>
                </td>
            </tr>

            <tr>
                <td>
                    <h6>Comment : </h6>
                </td>
                <td>
                    <form:textarea path="comment" placeholder="any comments"></form:textarea>
                </td>
            </tr>

            <!-- form:select value does not map to leave form check???-->
            <tr>
                <td><form:button class="btn" type="reset">clear</form:button></td>
                <td>
                    <form:button class="btn" type="submit">submit</form:button>
                </td>
            </tr>

            </tbody>
        </table>


    </form:form>

</div>
<script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
</body>
</html>