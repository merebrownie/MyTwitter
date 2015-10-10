<%-- 
    Document   : login.jsp
    Created on : Sep 24, 2015, 6:44:58 PM
    Author     : xl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="styles/main.css" rel="stylesheet" type="text/css"/>
        <script src="includes/main.js" type="text/javascript"></script>
        <title>MyTwitter | Login</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <h1>Log in</h1>
        <div id="message"></div>
        <form name="login" action="membership" method="post" onsubmit="return checkPassword()">
            <input type="hidden" name="action" value="login">
            <input type="email" name="email" value="${user.emailAddress}" placeholder="email or username" required><br>
            <input type="password" name="password" value="${user.password}" placeholder="password" required><br>
            <!--<input type="email" name="email" placeholder="email or username" required><br>
            <input type="password" name="password" placeholder="password" required><br>-->
            <input type="submit" value="Log in">
            <input type="checkbox" name="remember" value="remember"><label>Remember me</label>
            <a href="forgotpassword.jsp">Forgot password?</a>
        </form>
        <section id="message"></section>
        <section id="new">
            <p>New? <a href="signup.jsp">Sign-up now >></a></p>
        </section>
        
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
