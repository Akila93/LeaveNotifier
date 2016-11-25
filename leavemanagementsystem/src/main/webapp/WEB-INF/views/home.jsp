<%@ page language="java" contentType="text/html; ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" href="/resources/css/bootstrap.css"/>
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css"/>
    <%--<script src="https://apis.google.com/js/platform.js" async defer></script>--%>
    <meta name="google-signin-client_id" content="862712159345-ti9la1n9c7vtj95516st4q3nf4kt68rc.apps.googleusercontent.com">
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

    <script type="text/javascript">
        google.charts.load('current', {'packages': ['corechart']});
        google.charts.setOnLoadCallback(drawChart);

        let fullDayLeaves = 0, secondHalfLeaves = 0, firstHalfLeaves = 0;
        let temType;
        <c:forEach var="leave" items="${leaveList}">
        temType = '${leave.leaveType}';
        if (temType === 'full day') {
            fullDayLeaves = fullDayLeaves + 1;
        } else if (temType === 'first half') {
            firstHalfLeaves = firstHalfLeaves + 1;
        } else if (temType === 'second half') {
            secondHalfLeaves = secondHalfLeaves + 1;
        }


        </c:forEach>

        function drawChart() {

            let totalEmployee = parseInt('${userCount}');
            console.log(totalEmployee);
            console.log(fullDayLeaves);
            console.log(firstHalfLeaves);
            console.log(secondHalfLeaves);
            var data = google.visualization.arrayToDataTable([
                ['Leave Type', 'Employee Count'],
                ['No Leave', (totalEmployee - (fullDayLeaves + firstHalfLeaves + secondHalfLeaves))],
                ['Full Day', fullDayLeaves],
                ['First Half', firstHalfLeaves],
                ['Second Half', secondHalfLeaves],

            ]);

            var options = {
                title: 'Daily Attendance Analyze'
            };

            var chart = new google.visualization.PieChart(document.getElementById('piechart'));

            chart.draw(data, options);
        }
    </script>


    <meta charset="UTF-8">
    <title>Leave Notifier</title>
</head>
<body>

<div id="nav">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Leave Notifier</a>
            </div>

            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="../leave">Leave</a></li>
                <li><a id="alluserleaves">User Leave Analyzing</a></li>
            </ul>
            <script type="text/javascript">
                let year = '${homeForm.date}';
                year=year.substr(0,4);
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
    </nav>

</div>

<div class="container">
    <div class="raw">

        <%--style="border: solid;overflow: scroll;height: 500px;padding: 1%"--%>
        <div class="col-sm-6">

            <form:form action="../home" method="post" commandName="homeForm">
                <div class="input-group">
                    <span class="input-group-addon" id="sizing-addon1">select a date to view leaves</span>
                    <form:input class="form-control" path="date" placeholder="today" type="date"/>
                    <span class="input-group-btn">
                        <form:button type="submit" class="btn btn-default">Reload</form:button>
                    </span>
                </div>
            </form:form>
            <script type="text/javascript">
                //        check whether list of leaves empty then table should disapper
            </script>

            <table class="table table-bordered">
                <thead>
                <th>UserName</th>
                <th>LeaveType</th>
                <th>Reason</th>
                <th>Comment</th>
                </thead>
                <tbody>
                <script type="text/javascript">
                    let year='';
                    let url='';
                </script>
                <c:forEach var="leave" items="${leaveList}">
                    <tr>
                        <td><a id='${leave.name}'
                            <%--href="../users/${leave.userId}/<%=year%>/graph"--%>
                        >${leave.name}</a></td>
                        <td>${leave.leaveType}</td>
                        <td>${leave.reasonToLeave}</td>
                        <td>${leave.comment}</td>
                    </tr>
                    <script type="text/javascript">
                        year = '${homeForm.getDate()}';
                        year = year.substr(0, 4);
                        url = '../users/${leave.userId}/' + year + '/graph';
                        document.getElementById('${leave.name}').href = url;
                    </script>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <%--style="border: solid;overflow: scroll;height: 500px;padding: 1%"   --%>
        <div class="col-sm-6">
            <div id="piechart" style="width: 100%;height: 500px;"></div>
        </div>
    </div>
</div>
<script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
</body>
</html>