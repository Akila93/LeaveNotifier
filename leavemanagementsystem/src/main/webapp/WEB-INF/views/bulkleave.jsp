<%@ page language="java" contentType="text/html; ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>

<!doctype html>
<html lang="en">
<head>
    <link rel="icon" type="image/ico" href="/resources/images/logo-tab.ico" sizes="16x16">
    <link rel="stylesheet" href="/resources/css/bootstrap.css"/>
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css"/>
    <link rel="stylesheet" href="/resources/calander/themes/default.css"/>
    <link rel="stylesheet" href="/resources/calander/themes/default.date.css"/>
    <meta name="google-signin-client_id"
          content="862712159345-ti9la1n9c7vtj95516st4q3nf4kt68rc.apps.googleusercontent.com">

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
    <script src="/resources/calander/picker.js"></script>
    <script src="/resources/calander/picker.date.js"></script>

    <meta charset="UTF-8">
    <title>Add an leave</title>

    <style>
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
            margin-top: 10px;
            margin-left: 10%;
            margin-right: 10%;
            margin-bottom: 10%;
            padding: 40px 40px;

        }

        .login-button {
            margin-top: 5px;
        }

        .login-register {
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

        .error {
            color: #b92c28;
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
                <li><a href="../home">Home</a></li>
                <li ><a href="../leave">Leave</a></li>
                <li><a id="alluserleaves">Company Leave Analyzing</a></li>
                <li><a id="registration" href="../registration">User registration</a></li>
                <li class="active"><a id="bulkLeave" href="../bulk-leave">Bulk leave</a></li>

            </ul>
            <script type="text/javascript">
                let year = new Date().getFullYear();
                let role = '${userRole}';
                if (role.indexOf("ROLE_ADMIN") < 0) {
                    document.getElementById("alluserleaves").style.visibility = "hidden";
                    document.getElementById("registration").style.visibility = "hidden";
                    document.getElementById("bulkLeave").style.visibility = "hidden";
                }
                document.getElementById("alluserleaves").href = "../users/graph/" + year;
                //let email="nuwanthad@hsenidmobile.com";
                let email="${userEmail}";
                let urlForPic="http://picasaweb.google.com/data/entry/api/user/"+email+"?alt=json"
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

                    let id = '${userId}';
                    document.getElementById("profileData").href = "../users/" + id + "/" + year + "/graph";

                </script>

            </ul>
        </div>
    </nav>

</div>

<div class="container">
    <div class="row main">
        <div class="panel-heading">
            <div class="panel-title text-center">
                <h1 class="title">Bulk Leave Form</h1>
            </div>
        </div>
        <div class="main-login main-center">

            <form:form class="form-horizontal" action="/bulk-leave" method="post" commandName="bulkForm" id="bulkleaveform">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-4">
                    </div>
                    <form:button class="btn btn-default col-sm-4" type="submit" style="" onclick="onPushAll()">push to Leave Table</form:button>
                    <div class="col-sm-4">
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-4">
                        <form:input path="usernamesOfFulldayLeave" type="hidden" id="inputFieldFullday"/>
                        <h3>Full day</h3>
                    </div>
                    <div class="col-sm-4">
                        <form:input type="hidden" path="usernamesOfFirstHalfLeave" id="inputFieldFirstHalf"/>
                        <h3>First half</h3>
                    </div>
                    <div class="col-sm-4">
                        <form:input type="hidden" path="usernamesOfSecondHalfLeave" id="inputFieldSecondHalf"/>
                        <h3>Second half</h3>
                    </div>
                </div>
                <div class="row">
                    <script type="application/javascript">
                        let userNameListForSelecting=[];
                        let fulldayStack=[];
                        let firstHalfStack=[];
                        let SecondHalfStack=[];
                    </script>
                    <c:forEach var="user" items="${users}">
                        <script type="application/javascript">
                            userNameListForSelecting.push('${user.userName}');
                        </script>
                    </c:forEach>
                    <datalist id="namelist">
                    </datalist>
                    <script type="application/javascript">
                        let rootNode=document.getElementById("namelist");
                        for(const usernameIndex in userNameListForSelecting){
                            let newChild=document.createElement("option");
                            //console.log(userNameListForSelecting[usernameIndex]);
                            let textNode= document.createTextNode(userNameListForSelecting[usernameIndex]);
                            newChild.appendChild(textNode);
                            rootNode.appendChild(newChild);
                        }
                    </script>

                    <div class="col-sm-4">
                        <div class="input-group">
                            <input id="fullLeave" list="namelist" type="text" class="form-control" placeholder="user name...">
                            <span class="input-group-btn">
                                <button onclick="onFullDaySubmit()" class="btn btn-default" type="button">Add</button>
                            </span>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="input-group">
                            <input id="firstLeave" list="namelist" type="text" class="form-control" placeholder="user name...">
                            <span class="input-group-btn">
                                <button onclick="onFirstHalfSubmit()" class="btn btn-default" type="button">Add</button>
                            </span>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="input-group">
                            <input id="secondLeave" list="namelist" type="text" class="form-control" placeholder="user name...">
                            <span class="input-group-btn">
                                <button onclick="onSecondHalfSubmit()" class="btn btn-default" type="button">Add</button>
                            </span>
                        </div>
                    </div>
                </div>
                <script type="application/javascript">
                    function didRemoveElementFromNameList(name){
                        let rootNode=document.getElementById("namelist");
                        let c = rootNode.childNodes;
                        let returnValue=false;
                        for (i = 0; i < c.length; i++) {
                            let optionValue=c[i].innerText;
                            if(optionValue==name){
                                let nodeToRemove=c[i];
                                nodeToRemove.remove();
                                returnValue = true;
                            }
                        }
                        return returnValue;

                    }
                    function addElementToNameList(name){
                        let rootNode=document.getElementById("namelist");
                        let newChild=document.createElement("option");
                        let textNode= document.createTextNode(name);
                        newChild.appendChild(textNode);
                        rootNode.appendChild(newChild);

                    }
                    function createJsonArrayString(array){
                        let returnStr="";
                        for(let i=0;i<array.length;i++){
                            if(i==array.length-1){
                                returnStr=returnStr+"\'"+array[i]+"\'";
                            }else {
                                returnStr=returnStr+"\'"+array[i]+"\',";
                            }
                        }
                        if(array.length==0){returnStr+="]";}
                        return returnStr;
                    }
                    function onFullDaySubmit(){
                        let name = document.getElementById("fullLeave").value;
                        document.getElementById("fullLeave").value="";
                        if(name=="" || !didRemoveElementFromNameList(name)){
                            return;
                        }
                        let node = document.createElement("tr");
                        let nodetd = document.createElement("td");
                        let nodebtn = document.createElement("span");
                        let textnode = document.createTextNode(name);
                        nodetd.appendChild(textnode);
                        nodebtn.className="fa fa-times";
                        nodebtn.onclick=remove.bind(node,"full");
                        node.appendChild(nodebtn);
                        nodetd.setAttribute("id",("value_"+name+"_fullDayList"));
                        node.appendChild(nodetd);
                        node.setAttribute("id",(name+"_fullDayList"));
                        document.getElementById("fullDayList").appendChild(node);
                        fulldayStack.push(name);
                        document.getElementById("inputFieldFullday").value=createJsonArrayString(fulldayStack);
                        console.log("full:",fulldayStack);
                    }
                    function onFirstHalfSubmit(){
                        let name = document.getElementById("firstLeave").value;
                        document.getElementById("firstLeave").value="";
                        if(name=="" || !didRemoveElementFromNameList(name)){
                            return;
                        }
                        let node = document.createElement("tr");
                        let nodetd = document.createElement("td");
                        let nodebtn = document.createElement("span");
                        let textnode = document.createTextNode(name);
                        nodetd.appendChild(textnode);
                        nodebtn.className="fa fa-times";
                        nodebtn.onclick=remove.bind(node,"first");
                        node.appendChild(nodebtn);
                        nodetd.setAttribute("id",("value_"+name+"_firstHalfList"));
                        node.appendChild(nodetd);
                        node.setAttribute("id",(name+"_firstHalfList"));
                        document.getElementById("firstHalfList").appendChild(node);
                        firstHalfStack.push(name);
                        document.getElementById("inputFieldFirstHalf").value=createJsonArrayString(firstHalfStack);
                        console.log("first:",firstHalfStack);
                    }
                    function onSecondHalfSubmit(){
                        let name = document.getElementById("secondLeave").value;
                        document.getElementById("secondLeave").value="";
                        if(name=="" || !didRemoveElementFromNameList(name)){
                            return;
                        }
                        let node = document.createElement("tr");
                        let nodetd = document.createElement("td");
                        let nodebtn = document.createElement("span");
                        let textnode = document.createTextNode(name);
                        nodetd.appendChild(textnode);
                        nodebtn.className="fa fa-times";
                        nodebtn.onclick=remove.bind(node,"");
                        node.appendChild(nodebtn);
                        nodetd.setAttribute("id",("value_"+name+"_seconedHalfList"));
                        node.appendChild(nodetd);
                        node.setAttribute("id",(name+"_seconedHalfList"));
                        document.getElementById("seconedHalfList").appendChild(node);
                        SecondHalfStack.push(name);
                        document.getElementById("inputFieldSecondHalf").value=createJsonArrayString(SecondHalfStack);
                        console.log("seconed:",SecondHalfStack);
                    }
                    function removeArrayElement(arr, what) {
                        let newList = [];
                        for(let x=0;x<arr.length;x++){
                            if(arr[x].indexOf(what)>=0){
                                continue;
                            }
                            newList.push(arr[x]);
                        }
                        return newList;
                    }
                    function remove(select){
                        let list=[];
                        if(select=="full"){
                            list=fulldayStack;
                        }else if(select="first"){
                            list=firstHalfStack;
                        }else{
                            list=SecondHalfStack;
                        }
                        let removingNode=this;
                        removingNode.getElementsByTagName("td");
                        list = removeArrayElement(list,removingNode.getElementsByTagName("td")[0].innerText);
                        if(select=="full"){
                            fulldayStack=list;
                        }else if(select="first"){
                            firstHalfStack=list;
                        }else{
                            SecondHalfStack=list;
                        }
                        addElementToNameList(removingNode.getElementsByTagName("td")[0].innerText);
                        removingNode.remove();
                    }
                    function onPushAll(){
                        ///send request/clear stacks
                        //let fullDayList =document.getElementById("fullDayList");
                        //let seconedHalfList =document.getElementById("seconedHalfList");
                        //let firstHalfList =document.getElementById("firstHalfList");
                        console.log("full",document.getElementById("inputFieldFullday").value,"first:",document.getElementById("inputFieldFirstHalf").value,"second",
                                document.getElementById("inputFieldSecondHalf").value);

//                        fullDayList.innerHTML = '';
//                        seconedHalfList.innerHTML = '';
//                        firstHalfList.innerHTML = '';
                    }
                </script>
                <div class="row">
                    <div class="col-sm-4">
                        <table>
                        <tbody id="fullDayList">

                        </tbody>
                    </table>
                    </div>
                    <div class="col-sm-4">
                        <table>
                            <tbody id="firstHalfList">

                            </tbody>
                        </table>
                    </div>
                    <div class="col-sm-4">
                        <table>
                            <tbody id="seconedHalfList">

                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
            </form:form>


            <script type="text/javascript">
                function clear() {

                }
            </script>
        </div>
    </div>
</div>

<script type="text/javascript" src="assets/js/bootstrap.js"></script>
<script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>

</body>
</html>
