<%-- 
    Document   : home.jsp
    Created on : Sep 24, 2015, 6:47:02 PM
    Author     : mb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <!--<link href="styles/main.css" rel="stylesheet" type="text/css"/>-->
        <meta http-equiv="X-UA-Compatible" content="IE=edge; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <script src="includes/main.js" type="text/javascript"></script>
        <link href="styles/main.css" rel="stylesheet" type="text/css"/>
        <title>MyTwitter | Home</title>
    </head>
    <body onload="changeColor()">
        <div class="container-fluid">
            <div class="row">
                <div class="navbar navbar-default">
                    <jsp:include page="header.jsp"></jsp:include>
                </div>
            </div>
            <div class="row">
                <aside id="userInfo" class="col-md-3 panel panel-primary">
                    ${user.fullName}<br
                    @${user.nickname}<br>
                    Number of Tweets<br>
                    Profile Picture
                </aside>
                        
                <section id="postTweet" class="col-md-6">
                    <form id="postTweet" action="tweet">
                        <input type="hidden" name="action" value="postTweet">
                        <input type="hidden" name="userID" value="${user.userID}">
                        <textarea rows="4" cols="50" maxlength="200" name="text" value="${tweet.text}" required></textarea><br>
                        <input type="submit" value="Tweet">
                    </form>
                </section>
                    
                <aside id="follow"class ="col-md-3">
                    <h3>Who to follow</h3>
                    <c:forEach var="user" items="${users}">
                        <c:out value="${user.fullName}"></c:out>
                        [@<c:out value="${user.nickname}"></c:out>]<br>
                    </c:forEach>
                </aside>
            </div>
            <div class="row">
                <section id="trends" class="col-md-3">
                    <h3>Trends</h3>
                </section>
                <section id="tweets" class="col-md-6">
                    <h3>Tweets</h3>
                    <c:forEach var="tweet" items="${tweets}">
                        <table class="panel panel-primary">
                            <tr>
                                <td><c:out value="${user.fullName}"></c:out></td>
                            </tr>
                            <tr>
                                <td>@<c:out value="${user.nickname}"></c:out>: <c:out value="${tweet.timestamp}"></c:out></td>
                            </tr>
                            <tr>
                                <td class="tweet"><c:out value="${tweet.text}"></c:out></td>
                            </tr>
                        </table>
                    </c:forEach>
                    
                </section>
                <section id="tbd" class="col-md-3"></section>
            </div>

            
            
        </section>
            
        <section id="right" class=".col-md-4">
            
        </section>

        
        <footer class="row">
            <jsp:include page="footer.jsp"></jsp:include>
        </footer>
        </div>
    </body>
</html>
