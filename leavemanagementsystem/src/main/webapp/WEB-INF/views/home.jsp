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
    <%--<script src="https://apis.google.com/js/platform.js" async defer></script>--%>
    <meta name="google-signin-client_id"
          content="862712159345-ti9la1n9c7vtj95516st4q3nf4kt68rc.apps.googleusercontent.com">
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
<body style="background-color: #d3d3d3 ">
<div id="nav">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Leave Notifier</a>
            </div>

            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="../leave">Leave</a></li>
                <li><a id="alluserleaves">Company Leave Analyzing</a></li>
                <li><a id="registration" href="../registration">User registration</a></li>
                <li><a id="bulkLeave" href="../bulk-leave">Bulk leave</a></li>
            </ul>
            <form:form class="navbar-form navbar-left" method="post" commandName="searchForm" action="../users/search"
                       id="search_form">
                <div class="form-group">

                    <form:select id="year" class="form-control" placeholder="Type a year" name="year" path="year">
                        <script>
                            var myDate = new Date();
                            var year1 = myDate.getFullYear();
                            for (var i = year1; i > 2000; i--) {
                                document.write('<option value="' + i + '">' + i + '</option>');
                            }
                            let xhr = new XMLHttpRequest();
                            //let email="nuwanthad@hsenidmobile.com";
                            let email = "${userEmail}";
                            let urlForPic = "http://picasaweb.google.com/data/entry/api/user/" + email + "?alt=json"
                            xhr.open("GET", urlForPic);
                            xhr.setRequestHeader('Accept', 'application/json');
                            xhr.onload = function () {
                                let val = JSON.parse(xhr.responseText);
                                val = val["entry"];
                                val = val["gphoto$thumbnail"];
                                val = val["$t"];
                                //console.log("received",val);
                                if (val != null) {
                                    document.getElementById("profilePic").src = val;
                                }
                            };
                            xhr.send();
                        </script>
                    </form:select>

                        <%--<form:input type="date" class="form-control" placeholder="Type a year" name="year" path="year"/>--%>
                        <%----%>


                    <form:input type="text" class="form-control" placeholder="Search a name" name="name" path="name"/>
                </div>
                <form:button type="submit" class="btn btn-default">Submit</form:button>
            </form:form>
            <script type="text/javascript">
                let year = '${homeForm.date}';
                let role = '${userRole}';
                //console.log(typeof role,role.indexOf("ROLE_ADMIN"),"ROLE_USER".length);
                year = year.substr(0, 4);
                document.getElementById("alluserleaves").href = "../users/graph/" + year;
                if (role.indexOf("ROLE_ADMIN")) {
                    document.getElementById("search_form").style.visibility = "hidden";
                    document.getElementById("alluserleaves").style.visibility = "hidden";
                    document.getElementById("registration").style.visibility = "hidden";
                    document.getElementById("bulkLeave").style.visibility = "hidden";
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
                            <%--load the image--%>
                        <img id="profilePic" style="border-radius: 50%" src="/resources/images/blankuser.png"
                             alt="what?" width="42" height="42"/>
                    </li>
                    <li><a onclick="document.forms['logoutForm'].submit()"><span class="glyphicon glyphicon-off"></span>
                        Sign Out</a></li>
                </c:if>
                <script type="text/javascript">

                    let id = '${userId}';
                    document.getElementById("profileData").href = "../users/" + id + "/" + year + "/graph";
                </script>

            </ul>
        </div>
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
    </nav>

</div>
<style>
    body {
        font-size: 130%;
    }

    .wraper {
        width: 100%;
        height: 600px;
        margin: auto;
        border-radius: 0;
        overflow: auto;
        background-color: #FFFFFF;
    }

    .tb_container {
        overflow: auto;
    }
</style>
<div class="container">

    <%
        String notification = (String) request.getAttribute("emailNotification");
        if (notification!=null && notification.equals("success")) {
    %>

     <div class="row">


         <div class="col-sm-3"></div>
         <div class="col-sm-6">


             <div class="alert alert-success" role="alert">
                 <strong>Well done!</strong> Leave Summary sent successfully.
             </div>
         </div>
         <div class="col-sm-3"></div>

     </div>

    <%
        }
    %>
    <div class="raw wraper">


        <div class="col-sm-6" style="padding-top: 3%">


            <div>
                <div id="tb_msg_container"></div>
                <table class="table table-bordered">
                    <tbody>
                    <tr>
                        <%
                            String value = (String) request.getAttribute("userRole");
                            if (value.contains("ROLE_ADMIN")) {
                        %>
                        <form:form acti1on="../home" method="post" commandName="homeForm">
                            <td class="input-group-addon" id="sizing-addon1">Select A Date</td>
                            <td><form:input id="dateSelected" class="form-control" path="date" placeholder="today"
                                            type="date"/></td>
                            <td>
                                <form:button type="submit" class="btn btn-default">Reload</form:button>
                            </td>

                        </form:form>
                        <td><a id="sendEmail" href="../leave-reporting" class="btn btn-info" onclick="sendAReport()">Send
                            notification</a></td>
                        <%--<script type="text/javascript">--%>
                        <%--let currentDate=new Date();--%>
                        <%--let currentDay = currentDate.getDate();--%>
                        <%--let currentMonthIndex = currentDate.getMonth();--%>
                        <%--let currentYear = currentDate.getFullYear();--%>
                        <%--let selectedate = document.getElementById("dateSelected").value;--%>
                        <%--console.log("email:",selectedate,currentDate);--%>
                        <%--</script>--%>
                        <%
                            }
                        %>

                    </tr>
                    <tr id="tableHeader">
                        <td>UserName</td>
                        <td>LeaveType</td>
                        <td>Reason</td>
                        <td>Comment</td>
                    </tr>
                    <script type="text/javascript">
                        //let year='';
                        let url = '';
                    </script>
                    <c:forEach var="leave" items="${leaveList}">
                        <tr>
                            <td>
                                <%
                                    String roleofuser = (String) request.getAttribute("userRole");
                                    if (roleofuser.contains("ROLE_ADMIN")) {
                                %>
                                <a id='${leave.name}'>${leave.name}</a>
                                <%
                                } else {
                                %>
                                    ${leave.name}
                                <%
                                    }
                                %>
                            </td>
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


            <script type="text/javascript">
                let leave_List = [];
                let leave = {};
                <c:forEach items="${leaveList}" var="leave" varStatus="status">
                leave = {};
                leave["userId"] = "${leave.userId}";
                leave["name"] = "${leave.name}";
                leave["leaveDate"] = "${leave.leaveDate}";
                leave["leaveType"] = "${leave.leaveType}";
                leave["reasonToLeave"] = "${leave.reasonToLeave}";
                leave["comment"] = "${leave.comment}";
                leave_List.push(leave);
                </c:forEach>
                if (leave_List.length == 0) {
                    document.getElementById("tableHeader").style.visibility = "hidden";
                    document.getElementById("tb_msg_container").innerHTML = "no leaves has been added!";
                }
            </script>
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