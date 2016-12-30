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
        .thick {
            font-weight: bold;
            border: solid;
        }
        .buttonAlignRight{
            position: absolute;
            right: 0px;
        }
        .hide { display: none; }
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
    <datalist id="namelist">
    </datalist>
    <div class="row main">
        <div class="panel-heading">
            <div class="panel-title text-center">
                <h1 class="title">Project Group Form</h1>
            </div>
        </div>
        <div class="main-login main-center">
            <script type="text/javascript">
                let users="${users}";
                let groupCount="${groupCount}";
                let groupName="no name";
                let groupsJson=[];
                let usersJson=[];
                let groupList=[];//["group1","group2"];

                let groupOfJson={};
                <c:forEach items="${groups}" var="group" varStatus="status">
                    groupOfJson={};
                    groupOfJson.groupId='${group.groupId}';
                    groupOfJson.groupName='${group.groupName}';
                    groupOfJson.groupLederId='${group.groupLederId}';
                    groupList.push(groupOfJson.groupName);
                    groupsJson.push(groupOfJson);
                </c:forEach>

                console.log("groups:",groupsJson);


            </script>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-3" style="border-right: solid">
                        <h4>Type new group name</h4>
                        <div class="input-group">
                            <input type="text" id='groupNameInput' class="form-control" placeholder="group name"/>
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="button" onclick="onCreate()">Create</button>
                            </span>
                        </div>
                        <h4>Existing groups</h4>
                        <div class="container" id="group-container">
                        </div>


                    </div>
                    <div class="col-sm-9">
                        <div class="container-fluid">
                            <div class="hide" id="creatingWindow" style="margin-top: 10px;padding: 2px">
                                <h4 id="groupNameHeader">no name</h4>
                                <div class="input-group">
                                    <span class="input-group-addon">Select users</span>
                                    <input id="typedName" type="text" class="form-control" list="namelist" placeholder="userName"/>
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="button" onclick="addUsersToTheGroup()">Add</button>
                                </span>
                                </div>
                                <div class="container" id="userOfTheGroup">

                                </div>
                                <div class="container-fluid" style="margin-top: 10px;padding-right: 0px">
                                    <div class="buttonAlignRight">
                                        <button class="btn btn-default" onclick="removeGroup()">Remove</button>
                                        <button class="btn btn-default" onclick="submitOrUpdate()">Save</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <script type="application/javascript">
                function onGroupSelect(){
                    let valueOfInput=this.innerHTML;
                    let url="http://localhost:9099/users/groups/"+valueOfInput;
                    window.location=url;
                }
                function clearAllSelects(){
                    let parent=document.getElementById("group-container");
                    let children =parent.children;
                    for(let i=0;i<children.length;i++){
                        let child=children[i];
                        child.style.color="black";
                    }
                }
                function removeUserFromGroup(){
                    let value=this.innerHTML;
                    let listOfUsers = document.getElementById("userOfTheGroup");
                    let children=listOfUsers.children;
                    for(let i=0;i<children.length;i++){
                        let child=children[i];
                        let spanSelected=child.getElementsByTagName("Span")[0];
                        if(spanSelected.innerHTML==value){
                            child.remove();
                        }
                    }
                }
                function loadGroup(){
                    clearAllUserFromGroup();
                    <c:forEach items="${usersOfGroup}" var="userOfGroup" varStatus="status">
                    userOfJson={};
                    userOfJson.userId='${userOfGroup.userId}';
                    userOfJson.userName='${userOfGroup.userName}';
                    userOfJson.email='${userOfGroup.email}';
                    userOfJson.role='${userOfGroup.role}';
                    userOfJson.depId='${userOfGroup.depId}';
                    usersJson.push(userOfJson);
                    </c:forEach>
                    let valueForName="";
                    for(let i=0;i<usersJson.length;i++){
                        valueForName=usersJson[i].userName;
                        addUser(valueForName);
                    }

                }
                function clearAllUserFromGroup(){
                    document.getElementById("userOfTheGroup").innerHTML="";
                }
                let parent_node=document.getElementById("group-container");
                for(let j=0;j<groupList.length;j++){
                    let value_of_child=groupList[j];
                    let node=document.createElement("div");
                    let textNode= document.createTextNode(value_of_child);
                    node.className="row";
                    node.appendChild(textNode);
                    node.onclick=onGroupSelect.bind(node);
                    parent_node.appendChild(node);
                }
                function groupAllreadyExist(name){
                    for(let i=0;i<groupList.length;i++){
                        if(groupList[i]==name){
                            return true;
                        }
                    }
                    return false;
                }
                function submitOrUpdate(){


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
                function selectNode(groupName){
                    let children=document.getElementById("group-container").children;
                    for(let i =0;i<children.length;i++){
                        let child=children[i];
                        let val=child.innerHTML;
                        if(val==groupName){
                            return child;
                        }
                    }
                    return null;
                }
                function addUser(valueForName){
                    let nodeRow=document.createElement("div");
                    let listOfUsers = document.getElementById("userOfTheGroup");
                    let textNode=document.createTextNode(valueForName);
                    let node=document.createElement("span");
                    node.appendChild(textNode);
                    node.onclick=removeUserFromGroup.bind(node);
                    nodeRow.appendChild(node);
                    listOfUsers.appendChild(nodeRow);
                    node.className="fa fa-times";
                    nodeRow.className="row";
                }
                function addUsersToTheGroup(){
                    let valueForName=document.getElementById("typedName").value;
                    document.getElementById("typedName").value="";
                    addUser(valueForName);
                }
                function removeGroup(){
                    clearAllSelects();
                    let node=selectNode(groupName);
                    document.getElementById('creatingWindow').className = 'hide';
                    groupName="no name";
                    node.remove();
                }
                function pushToGroupStack(){
                    let parent_node=document.getElementById("group-container");
                    groupList.push(groupName);
                    let node=document.createElement("div");
                    let textNode= document.createTextNode(groupName);
                    node.className="row";
                    node.appendChild(textNode);
                    node.onclick=onGroupSelect.bind(node);
                    parent_node.appendChild(node);
                    clearAllSelects();
                    node.style.color='blue';
                }
                function onCreate(){
                    let valueOfInput=document.getElementById('groupNameInput').value;
                    let url="http://localhost:9099/users/groups/create/"+valueOfInput;
                    console.log("url",url);
                    window.location=url;
//                    let valueOfInput=document.getElementById('groupNameInput').value;
//                    document.getElementById('groupNameInput').value="";
//                    //console.log("good",groupAllreadyExist(valueOfInput))
//                    if(valueOfInput!="" && !groupAllreadyExist(valueOfInput)){
//                        groupName=valueOfInput;
//                        groupList.push(valueOfInput);
//                        pushToGroupStack();
//                        document.getElementById('groupNameHeader').innerHTML = groupName;
//                        document.getElementById('creatingWindow').className = 'container-fluid';
//                        loadGroup();
//                    }
                }
                if("${groupName}"!=null){
                    groupName="${groupName}";
                    clearAllSelects();
                    let parent_node=document.getElementById("group-container");
                    let children=parent_node.children;
                    let node=null;
                    for(let i=0;i<children.length;i++){
                        let child=children[i];
                        if(child.innerHTML==groupName){
                            child.style.color = "blue";
                        }
                    }
                    document.getElementById('groupNameHeader').innerHTML = groupName;
                    document.getElementById('creatingWindow').className = 'container-fluid';
                    loadGroup();
                    if("${flag}"=="created"){
                        let url="http://localhost:9099/users/groups/"+groupName;
                        window.location=url;
                    }
                }
            </script>
        </div>
    </div>
</div>

<script type="text/javascript" src="assets/js/bootstrap.js"></script>
<script src="https://apis.google.com/js/platform.js?onload=onLoad" async defer></script>

</body>
</html>
