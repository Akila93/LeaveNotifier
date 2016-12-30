<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.lms.entity.User" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="icon" type="image/ico" href="/resources/images/logo-tab.ico" sizes="16x16">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">


    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.css">

    <!-- Website CSS style -->
    <link rel="stylesheet" type="text/css" href="assets/css/main.css">

    <!-- Website Font style -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

    <!-- Google Fonts -->
    <link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>


    <title>Create an account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<style>

    select {
        /*-webkit-border-radius:4px;*/
        /*-moz-border-radius:4px;*/
        /*border-radius:4px;*/
        /*-webkit-box-shadow: 0 3px 0 #ccc, 0 -1px #fff inset;*/
        /*-moz-box-shadow: 0 3px 0 #ccc, 0 -1px #fff inset;*/
        /*box-shadow: 0 3px 0 #ccc, 0 -1px #fff inset;*/
        background: #FFFFFF;
        color:black;
        outline:none;
        display: inline-block;
        -webkit-appearance:none;
        -moz-appearance:none;
        appearance:none;
        cursor:pointer;
    }

    body, html {
        height: 100%;
        background-repeat: no-repeat;
        background-color: #d3d3d3;
    }

    .main {
        margin-top: 10px;
    }

    h1.title {
        font-size: 50px;
        font-family: 'Passion One', cursive;
        font-weight: 400;
    }

    hr {
        width: 10%;
        color: #fff;
    }

    .form-group {
        margin-bottom: 15px;
    }

    label {
        margin-bottom: 15px;
    }

    input,
    input::-webkit-input-placeholder {
        font-size: 11px;
        padding-top: 3px;
    }

    .main-login {
        background-color: #fff;
        /* shadows and rounded borders */
        -moz-border-radius: 2px;
        -webkit-border-radius: 2px;
        border-radius: 2px;
        -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
        box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);

    }

    .main-center {
        margin-top: 30px;
        margin: 0 auto;
        max-width: 500px;
        padding: 40px 40px;

    }

    .login-button {
        margin-top: 5px;
    }

    table {
        margin: auto;
    }

    .container {
        padding: auto;
        margin-left: 5%;
        margin-right: 5%;
    }

    .role-select {
        margin: auto;

    }
    .error{
        color: #b92c28;
    }
</style>


<script type="application/javascript">
   function changeForm(value){

       console.log("select a item",value);
       let usersOf='${users}';
       let usertem;
       var userList = new Array();
       <c:forEach items="${users}" var="user" varStatus="status">

                 usertem= new Object();
                 usertem.name = '${user.userName}';
                 usertem.email= '${user.email}';
                 usertem.role='${user.role}';
                 usertem.depId='${user.depId}';

       userList.push(usertem);
       </c:forEach>
       for (let i=0;i<userList.length;i++){
           if(userList[i].name===value){
               document.getElementById("email-form").value = userList[i].email;
               document.getElementById("role-form").value = userList[i].role;
               document.getElementById("depId-form").value = userList[i].depId;
               break;
           }
       }

    }

</script>

<body>


    <div id="nav">
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Leave Notifier</a>
                </div>
                <ul class="nav navbar-nav">
                    <li ><a href="../home">Home</a></li>
                    <li ><a href="../leave">Leave</a></li>
                    <li ><a id="alluserleaves">Company Leave Analyzing</a></li>
                    <li class="active"><a href="../registration">User registration</a></li>
                    <li><a id="bulkLeave" href="../bulk-leave">Bulk leave</a></li>

                </ul>
                <script type="text/javascript">
                    let year = new Date().getFullYear();
                    document.getElementById("alluserleaves").href="../users/graph/"+year;
                </script>
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
                        <li><a  id="profileData" style="color: white;text-align: center">${pageContext.request.userPrincipal.name}</a></li>
                        <li>
                            <img id="profilePic" style="border-radius: 50%" src="/resources/images/blankuser.png" alt="what?" width="42" height="42"/>
                        </li>
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
                <h1 class="title">User Registration</h1>
                <hr/>
            </div>
        </div>
        <div class="main-login main-center">

           <form:form method="POST" modelAttribute="userForm" class="from-horizontal">
               <div class="form-group">
                    <label class="cols-sm-2 control-label">User Name</label>
                    <div class="cols-sm-10">
                        <div class="input-group">


                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>

                            <spring:bind path="userName">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="text" path="userName" class="form-control" placeholder="Username" list="namelist"
                                                autofocus="true" onchange="changeForm(this.value)"></form:input>

                                    <datalist id="namelist">
                                        <c:forEach var="user" items="${users}">
                                            <option>${user.userName}</option>
                                        </c:forEach>
                                    </datalist>


                                    <form:errors path="userName"></form:errors>
                                </div>
                            </spring:bind>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="cols-sm-2 control-label">Your Email</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                            <spring:bind path="email">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input id ="email-form" type="email" path="email" class="form-control"
                                                placeholder="Enter User Email"></form:input>

                                    <form:errors path="email"></form:errors>

                                </div>
                            </spring:bind>


                        </div>
                        <label class="error"> ${errorName} </label>

                    </div>
                </div>


                <div class="form-group">

                    <label class="cols-sm-2 control-label">User Role</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-key fa" aria-hidden="true"></i></span>
                            <form:select placeholder="Select Your Role" path="role" style="height: 100%;width: 100%" id="role-form">

                                <form:option value="ROLE_USER"/>
                                <form:option value="ROLE_ADMIN"/>

                            </form:select>

                        </div>
                    </div>

                </div>
               <div class="form-group">

                   <label class="cols-sm-2 control-label">User Department</label>
                   <div class="cols-sm-10">
                       <div class="input-group">
                           <span class="input-group-addon"><i class="fa fa-universal-access fa" aria-hidden="true"></i></span>
                           <form:select placeholder="Select Your department" path="depId" style="height: 100%;width: 100%" id="depId-form">

                               <c:forEach var="department" items="${departments}">
                                   <form:option value='${department.depID}'>${department.depName}</form:option>
                               </c:forEach>

                           </form:select>

                       </div>
                   </div>

               </div>


                <div class="form-group ">
                    <button type="submit" class="btn btn-primary">Update</button>
                </div>

            </form:form>


        </div>
    </div>
</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

<script type="text/javascript" src="assets/js/bootstrap.js"></script>
</body>
</html>

