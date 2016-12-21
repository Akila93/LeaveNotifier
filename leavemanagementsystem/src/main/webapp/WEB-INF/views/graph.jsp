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
    <script type="text/javascript">
        var DOMAIN_NAME="<spring:message code='server.domain'/>";
        var PORT_NUMBER="<spring:message code='server.port'/>";
    </script>
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
        var yearOfCurrent='${leaveYear}';
        google.charts.load('current', {'packages': ['corechart', "calendar"]});
        google.charts.setOnLoadCallback(drawVisualization);
        function drawVisualization() {

            let list = [['Month', 'First Half', 'Second Half', "Full Day"]];
            let item = [];
            let month, firsthalf, secondhalf, fullday;
            <c:forEach items='${leaveListOfYear}' var="entry1">
            item = [];
            month = '${entry1.key}';
            firsthalf = '${entry1.firstHalf}';
            secondhalf = '${entry1.secondHalf}';
            fullday = '${entry1.fullDay}';

            item.push(month);
            item.push(parseInt(firsthalf));

            item.push(parseInt(secondhalf));
            item.push(parseInt(fullday));
            list.push(item);
            </c:forEach>

            var data = google.visualization.arrayToDataTable(list);
            let title = 'Leave of ${leaveUserName} Year ${leaveYear}'
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
        google.charts.setOnLoadCallback(drawChart);


        function drawChart() {
            var dataTable = new google.visualization.DataTable();
            dataTable.addColumn({type: 'date', id: 'Date'});
            dataTable.addColumn({type: 'number', id: 'Color'});
            dataTable.addColumn({'type': 'string', 'role': 'tooltip', 'p': {'html': true}});
            let list = []
            let date, type;
            let colour = 5;
            <c:forEach items='${leavesOfYear}' var="leave">
            date = '${leave.leaveDate}';
            type = '${leave.leaveType}';
            if (type === 'full day') {
                colour = -10;
            } else if (type === 'first half') {
                colour = 10;
            } else if (type === 'second half') {
                colour = 5;
            }
            list.push([new Date(date), colour, createCustomHTMLContent(new Date(date), type)]);
            </c:forEach>

            dataTable.addRows(list);

            var chart = new google.visualization.Calendar(document.getElementById('calendar_basic'));

            var options = {
                title: "Attendance",
                height: 350,
                calendar: {
                    cellSize: 12,
                    cellColor: {
                        stroke: '#76a7fa',
                        strokeOpacity: 0.5,
                        strokeWidth: 1,
                    }
                },
                tooltip: {isHtml: true},
                focusTarget: 'category',


            };

            chart.draw(dataTable, options);
        }
        function createCustomHTMLContent(date, status) {
            let monthNames = ["January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
            ];

            return '<div style="padding:5px 5px 5px 5px;font-size: 127%;font-weight:bold" >' +
                    '<table class="medals_layout">' +
                    '<tr>' + monthNames[date.getMonth()] + ' ' + date.getDate() + ',<br/>' + date.getFullYear() + '</tr><br/>' +
                    '<tr>' + status + '</tr>' +
                    '</table>' + '</div>';
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
                <li id="homeContainer"><a href="../../../home">Home</a></li>
                <li><a href="../../../leave">Leave</a></li>
                <li><a id="alluserleaves">Company Leave Analyzing</a></li>
                <li><a id="registration" href="../../../registration">User registration</a></li>
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
            <script type="text/javascript">
                ///users/1/2016/graph
                let year = new Date().getFullYear();
                let role = '${userRole}';
                document.getElementById("alluserleaves").href = "../../../../users/graph/" + year;
                if (role != "ROLE_ADMIN") {
                    document.getElementById("alluserleaves").style.visibility = "hidden";
                    document.getElementById("registration").style.visibility = "hidden";
                    document.getElementById("bulkLeave").style.visibility = "hidden";
                    document.getElementById("homeContainer").className="active";
                }
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

                    let id = '${userId}';
                    document.getElementById("profileData").href = "../../../../users/" + id + "/" + year + "/graph";
                </script>
            </ul>
        </div>
    </nav>

</div>


<div class="fluid-container">

    <div style="background-color: #FFFFFF;margin: 5%">

        <center>
            <h1 style="align-content: center;padding-top: 5px">${leaveUserName}</h1>
        </center>

        <div class="row" style="padding-bottom:2%;padding-left:2%;padding-right:2%">
            <div class="col-sm-3">
                <img src="/resources/images/blankuser.png" id="pictureOfEmplyee" height="200" width="200"/>
            </div>
            <script type="text/javascript">
                //let email="nuwanthad@hsenidmobile.com";
                let leaveUserEmail="${leaveUserEmail}";
                let urlForLeaveUserPic="http://picasaweb.google.com/data/entry/api/user/"+leaveUserEmail+"?alt=json";
                let xhrOfLeaveUser = new XMLHttpRequest();
                xhrOfLeaveUser.open("GET",urlForLeaveUserPic);
                xhrOfLeaveUser.setRequestHeader('Accept', 'application/json');
                xhrOfLeaveUser.onload = function() {
                    let valOfResponse = JSON.parse(xhrOfLeaveUser.responseText);
                    valOfResponse = valOfResponse["entry"];
                    valOfResponse = valOfResponse["gphoto$thumbnail"];
                    valOfResponse = valOfResponse["$t"];
                    //console.log("received",val);
                    if(valOfResponse!=null){
                        document.getElementById("pictureOfEmplyee").src=valOfResponse;
                    }
                };
                xhrOfLeaveUser.send();
            </script>

            <div class="col-sm-4">
                <h3>Account Type</h3>
                <h3>Email Address</h3>
                <h3>Employee Department</h3>
                <h3>Employee Id</h3>


            </div>
            <div class="col-sm-5">
                <h3>: ${leaveUserAccountType}</h3>
                <h3>: ${leaveUserEmail}</h3>
                <h3>: ${leaveUserDepartment}</h3>
                <h3>: ${leaveUserId}</h3>


            </div>
        </div>
    </div>
    <div style="background-color: #FFFFFF;margin: 5%">
        <div class="row" style="padding:2%">
            <div class="col-sm-8" >
                <div class="row">

                    <a id="pre-year" class="glyphicon glyphicon-arrow-left"></a>
                    <span id="yearSowingSpan"></span>
                    <a id="next-year" class="glyphicon glyphicon-arrow-right"></a>
                    <script type="application/javascript">
                        document.getElementById("yearSowingSpan").innerHTML=yearOfCurrent;
                        yearOfCurrent=parseInt(yearOfCurrent);
                        let pre_url="http://"+DOMAIN_NAME+":"+PORT_NUMBER+"/users/"+'${leaveUserId}'+"/"+(yearOfCurrent-1)+"/graph";
                        let next_url="http://"+DOMAIN_NAME+":"+PORT_NUMBER+"/users/"+'${leaveUserId}'+"/"+(yearOfCurrent+1)+"/graph";
                        document.getElementById("pre-year").href=pre_url;
                        document.getElementById("next-year").href=next_url;
                    </script>
                </div>
                <div id="chart_div" ></div>
                <div id="calendar_basic" style="padding: 1% "></div>
            </div>
            <div class="col-sm-1"></div>
            <div class="col-sm-3">
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
                            <td>${entry.month}</td>
                            <td>${entry.firstHalf} </td>
                            <td> ${entry.secondHalf} </td>
                            <td>${entry.fullDay} </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row">

        </div>
    </div>
</div>
<script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>
</body>
</html>