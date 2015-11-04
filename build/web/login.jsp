<%-- 
    Document   : login.jsp
    Created on : Sep 24, 2015, 6:44:58 PM
    Author     : nb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <!--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">-->
        <meta http-equiv="X-UA-Compatible" content="IE=edge; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="styles/main.css" rel="stylesheet" type="text/css"/>
        <script src="includes/main.js" type="text/javascript"></script>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <title>MyTwitter | Login</title>
    </head>
    <body>
        <div class="navbar navbar-default">
            <jsp:include page="header.jsp"></jsp:include>
        </div>
        <h1>Log in</h1>
        <div id="message"></div>
        <form name="login" action="membership" method="post" >
            <input type="hidden" name="action" value="login">
            <input type="email" name="emailAddress" value="${user.emailAddress}" placeholder="email or username" required><br>
            <input type="password" name="password" value="${user.password}" placeholder="password" required><br>
            <!--<input type="email" name="email" placeholder="email or username" required><br>
            <input type="password" name="password" placeholder="password" required><br>-->
            <input type="submit" value="Log in" onsubmit="return checkPassword()">
            <input type="checkbox" name="remember" value="remember"><label>Remember me</label>
            <a href="forgotpassword.jsp">Forgot password?</a>
        </form>
        <section id="message">${message}</section>
        <section id="new">
            <p>New? <a href="signup.jsp">Sign-up now >></a></p>
        </section>
        
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
