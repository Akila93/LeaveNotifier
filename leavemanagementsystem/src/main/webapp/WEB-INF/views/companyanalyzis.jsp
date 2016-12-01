<%@ page language="java" contentType="text/html; ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!doctype html>
<html lang="en">
<head>

    <link rel="stylesheet" href="/resources/css/bootstrap.css"/>
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css"/>
    <meta name="google-signin-client_id"
          content="862712159345-ti9la1n9c7vtj95516st4q3nf4kt68rc.apps.googleusercontent.com">
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script>
        function onLoad() {
            gapi.load('auth2', function () {
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
    <script type="text/css">
        #nav {
            background-color: green;
            border-color: #E7E7E7;
        }
    </script>
    <script type="text/javascript">

        google.charts.load('current', {'packages': ['corechart', "calendar"]});
        google.charts.setOnLoadCallback(drawVisualization);

        function drawVisualization() {

            let list = [['Month', 'Leave']];
            let item = [];
            let month, value;
            <c:forEach items='${leaveListOfYear}' var="entry1">
            item = [];
            month = '${entry1.key}';
            value = '${entry1.value}';
            item.push(month);
            item.push(parseInt(value));
            list.push(item);
            </c:forEach>
            var data = google.visualization.arrayToDataTable(list);
            let title = 'Leave of Year ${leaveYear}'
            var options = {
                title: title,
                vAxis: {title: 'Number Of Leaves'},
                hAxis: {title: 'Month'},
                seriesType: 'bars',

            };

            var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
            chart.draw(data, options);
        }
    </script>


    <script type="text/javascript">
        google.charts.setOnLoadCallback(drawSteppedAreaChart);
        function drawSteppedAreaChart() {
//            ['Day','user1','user1','user1','user1','user1','user1','user1']
            let list = [];
            let item = [];
            let userItem = ['Day'];
            <%--item.push('Day');--%>

            <c:forEach var='entry' items="${allUsers}">
            console.log('${entry.userName}');
            userItem.push('${entry.userName}');

            </c:forEach>

            list.push(userItem);
            <c:forEach var="entry" items="${monthlyUserLeaveChart}">
            item = [];
            console.log('${entry.date}');
            item.push(parseInt(${entry.date}));
            <c:forEach var="value" items="${entry.arr}">
            item.push(parseInt(${value}));
            </c:forEach>
            list.push(item);
            </c:forEach>

            var data = google.visualization.arrayToDataTable(list);


            var options = {
                title: 'Leaves of month ${leaveMonth}',
                vAxis: {
                    title: 'Number of leaves',

                },
                hAxis: {
                    title: 'Number of Days',
                    ticks: [5,10,15,20]
                },
                isStacked: true
            };
            let listOfDays=[];
            for(let i=1;i<=31;i++){
                listOfDays.push(i);
            }
            options.hAxis.ticks=listOfDays;
            var chart = new google.visualization.SteppedAreaChart(document.getElementById('Stepped_basic'));

            chart.draw(data, options);
        }
    </script>


    <meta charset="UTF-8">
    <title>Leave Analyze</title>

</head>
<body style="background-color: #CDD4D5">

<div id="nav">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Leave Notifier</a>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="../../../home">Home</a></li>
                <li><a href="../../../leave">Leave</a></li>
                <li class="active"><a href="#">Company Leave Analyzing</a></li>
                <li ><a href="../../../registration">User registration</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <form id="logoutForm" method="POST" action="${contextPath}/logout">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                    <li><a  id="profileData" style="color: white;text-align: center">${pageContext.request.userPrincipal.name}</a></li>
                    <li><a onclick="document.forms['logoutForm'].submit()"><span class="glyphicon glyphicon-off"></span>
                        Sign Out</a></li>
                </c:if>
                <script type="text/javascript">
                    let year = '${leaveYear}'
                    let id='${userId}';
                    document.getElementById("profileData").href="../../../users/"+id+"/"+year+"/graph";
                </script>
            </ul>
        </div>
    </nav>

</div>


<div class="fluid-container">
    <div style="background-color: #FFFFFF;margin: 5%">
    <div class="row" style="padding:2%">
        <div class="col-sm-9" style="overflow: auto">
            <div id="chart_div" style="width: 900px; height: 500px"></div>
        </div>
        <div class="col-sm-3" style="overflow: auto">
            <table class="table table-bordered">
                <thead>
                <th>Month</th>
                <th>Number Of Leave</th>
                </thead>
                <tbody>
                <c:forEach var="entry" items="${leaveListOfYear}">
                    <tr>
                        <td><a href="../../../users/graph/${leaveYear}/${entry.month}">${entry.month}</a></td>
                        <td>${entry.value}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row" style="padding:2%">
        <div id="Stepped_basic"></div>
    </div>
        </div>
</div>
<script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
</body>
</html>