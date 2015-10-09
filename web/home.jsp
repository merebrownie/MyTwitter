<%-- 
    Document   : home.jsp
    Created on : Sep 24, 2015, 6:47:02 PM
    Author     : xl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="styles/main.css" rel="stylesheet" type="text/css"/>
        <script src="includes/main.js" type="text/javascript"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <%
            
        %>
        <jsp:include page="header.jsp"></jsp:include>
        <section>
            Dear ${user.fullName}: 
            <h1>Welcome!</h1>
        </section>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
