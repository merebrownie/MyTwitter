<%-- 
    Document   : header.jsp
    Created on : Sep 24, 2015, 6:47:09 PM
    Author     : mb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="styles/main.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <title>Header | MyTwitter</title>
    </head>
    <body>
        <a href="home.jsp">Home</a> | 
        Notifications | 
        <a href="signup.jsp">Profile</a>
        <span id="logout" onclick="logout()">Logout</span>
    </body>
</html>
