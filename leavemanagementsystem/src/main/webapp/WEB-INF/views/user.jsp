<%@ page import="com.lms.entity.User" %>
<%@ page language="java" contentType="text/html; ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!doctype html>
<html lang="en">
    <head>
        <link rel="stylesheet" href="/resources/css/bootstrap.css"/>
        <link rel="stylesheet" href="/resources/css/bootstrap-theme.css"/>
        <meta charset="UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <div class="container">
            <form action="/update" method="post" cssClass="form">
               <div class="form-group row">
                   <div>name</div>
                   <input list="namelist" placeholder="enter name"/>
                   <datalist id="namelist">
                       <c:forEach var="user" items="${users}">
                           <option>${user.userName}</option>
                       </c:forEach>
                   </datalist>
               </div>
                <div class="form-group row">
                    <div>status</div>
                    <select>
                        <option>half day</option>
                        <option>full day</option>
                        <option>fdsf</option>

                    </select>
                </div>
                <button type="reset">clear</button>
                <button type="submit">submit</button>
            </form>

        </div>
    </body>
</html>