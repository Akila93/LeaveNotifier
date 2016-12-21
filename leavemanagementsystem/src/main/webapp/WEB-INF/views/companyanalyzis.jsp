<%@ page language="java" contentType="text/html; ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!doctype html>
<html lang="en">
<head>
    <link rel="icon" type="image/ico" href="/resources/images/logo-tab.ico" sizes="16x16">
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
        body{

        }
    </script>
    <script type="text/javascript">

        google.charts.load('current', {'packages': ['corechart', "calendar"]});
        google.charts.setOnLoadCallback(drawVisualization);

        function drawVisualization() {

            let list = [['Month', 'Fist Half','Second Half','Full Day']];
            let item = [];
            let month,firsthalf,secondhalf,fullday;
            <c:forEach items='${leaveListOfYear}' var="entry1">
            item = [];
            month = '${entry1.key}';
            firsthalf = '${entry1.firstHalf}';
            secondhalf='${entry1.secondHalf}';
            fullday=${entry1.fullDay}
            item.push(month);
            item.push(parseInt(firsthalf));
            item.push(parseInt(secondhalf));
            item.push(parseInt(fullday));
            list.push(item);
            </c:forEach>
            var data = google.visualization.arrayToDataTable(list);
            let title = 'Leave of Year ${leaveYear}'
            var options = {
                title: title,
                vAxis: {title: 'Number Of Leaves'},
                hAxis: {title: 'Month'},
                seriesType: 'bars',
                series: {3: {type: 'line'}}


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
                    ticks: [5, 10, 15, 20]
                },
                connectSteps: false,
                isStacked: true
            };
            let listOfDays = [];
            for (let i = 1; i <= 31; i++) {
                listOfDays.push(i);
            }
            options.hAxis.ticks = listOfDays;
            var chart = new google.visualization.SteppedAreaChart(document.getElementById('Stepped_basic'));

            chart.draw(data, options);
        }
    </script>


    <meta charset="UTF-8">
    <title>Leave Analyze</title>

</head>
<body style="background-color: #d3d3d3">

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
                <li><a href="../../../registration">User registration</a></li>
                <li><a id="bulkLeave" href="../../../bulk-leave">Bulk leave</a></li>
            </ul>
            <script type="text/javascript">
                //let email="nuwanthad@hsenidmobile.com";
                let email="${userEmail}";
                let urlForPic="http://picasaweb.google.com/data/entry/api/user/"+email+"?alt=json";
                let xhr = new XMLHttpRequest();
                xhr.open("GET",urlForPic);
                xhr.setRequestHeader('Accept', 'application/json');
                xhr.onload = function() {
                    let val = JSON.parse(xhr.responseText);
                    val = val["entry"];
                    val = val["gphoto$thumbnail"];
                    val = val["$t"];
                    //console.log("received",val);
                    if(val!=null){
                        document.getElementById("profilePic").src=val;
                    }
                };
                xhr.send();
            </script>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <form id="logoutForm" method="POST" action="${contextPath}/logout">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                    <li><a id="profileData"
                           style="color: white;text-align: center">${pageContext.request.userPrincipal.name}</a></li>
                    <li>
                        <img id="profilePic" style="border-radius: 50%" src="/resources/images/blankuser.png" alt="what?" width="42" height="42"/>
                    </li>
                    <li><a onclick="document.forms['logoutForm'].submit()"><span class="glyphicon glyphicon-off"></span>
                        Sign Out</a></li>
                </c:if>
                <script type="text/javascript">
                    let year = '${leaveYear}';
                    year=parseInt(year);
                    let id = '${userId}';
                    document.getElementById("profileData").href = "../../../users/" + id + "/" + year + "/graph";
                </script>
            </ul>
        </div>
    </nav>

</div>


<div class="fluid-container">
    <div style="background-color: #FFFFFF;margin: 5%">


        <div style="padding:2%">  Year :  <input value='${year}' type="number" name="yearSelect" id="yearSelect"/>
            <button class="button" onclick="OnSet()">Set</button>
            <script type="application/javascript">
                function OnSet() {
                    let value_for_year = document.getElementById("yearSelect").value;
                    console.log(value_for_year, "value_for_year");
                    if (value_for_year.length != 4) {
                        return;
                    }
                    //value_for_year=(String)value_for_year;
                    var DOMAIN_NAME="<spring:message code='server.domain'/>";
                    var PORT_NUMBER="<spring:message code='server.port'/>";
                    let onSetUrl = "http://"+DOMAIN_NAME+":"+PORT_NUMBER+"/users/graph/" + value_for_year;
                    window.location.assign(onSetUrl);
                }

            </script>
        </div>


        <div class="row" style="padding:2%">
            <div class="col-sm-7" >
                <div id="chart_div"></div>
                <div id="Stepped_basic" style="height: 300px"></div>
            </div>
            <div class="col-sm-2">

            </div>
            <div class="col-sm-3" style="overflow: auto">
                <table class="table table-bordered">
                    <thead>

                        <th>Month</th>
                        <th>First Half</th>
                        <th>Second Half</th>
                        <th>Full Day</th>

                    </thead>
                    <tbody>
                    <c:forEach var="entry" items="${leaveListOfYear}">
                        <tr>
                            <td><a href="../../../users/graph/${leaveYear}/${entry.month}">
                            ${entry.month}</a>
                            </td>
                            <td>${entry.firstHalf}</td>
                            <td>${entry.secondHalf}</td>
                            <td>${entry.fullDay}</td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <%--<div class="row" style="padding:2%;overflow: auto">--%>

        <%--</div>--%>
    </div>
</div>
<script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
</body>
</html>