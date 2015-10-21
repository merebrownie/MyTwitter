<%-- 
    Document   : header.jsp
    Created on : Sep 24, 2015, 6:47:09 PM
    Author     : xl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header | MyTwitter</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${user.emailAddress == ''}">
                <p>Oops</p>
            </c:when>
            <c:when test="${user.emailAddress != ''}">
                <label>Name: </label>
                <c:out value="${user.fullName}"></c:out>
                <label>E-mail: </label>
                <c:out value="${user.emailAddress}"></c:out>
            </c:when>
        </c:choose>
        <c:if test="${user.emailAddress == ''}">
            <c:out value="${user.fullName}"/>
            <c:out value="${user.emailAddress}"/>
        </c:if>
    </body>
</html>
