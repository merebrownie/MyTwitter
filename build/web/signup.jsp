<%-- 
    Document   : signup.jsp
    Created on : Sep 24, 2015, 6:44:47 PM
    Author     : xl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="styles/main.css" rel="stylesheet" type="text/css"/>
        <script src="includes/main.js" type="text/javascript"></script>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <title>MyTwitter | Signup</title>
    </head>
    <body onload="checkCookie()" class="container-fluid">
        <div class="row">
            <div class="navbar navbar-default">
            <jsp:include page="header.jsp"></jsp:include>
        </div>
        </div>
        
        <h1>Sign-up</h1>
        <form name="signup" method="post" onsubmit="return validateForm()" action="membership">
        <c:choose>
            <c:when test="${user.getEmailAddress() == null || user.getEmailAddress() == ''}">
                <input type="hidden" name="action" value="signup">
            </c:when>
            <c:otherwise>
                <input type="hidden" name="action" value="update">
            </c:otherwise>
        </c:choose>
            <label>Full Name:</label>
                <input type="text" name="fullName" value="${user.fullName}" required><br>
            <label>Nickname: </label>
            <c:choose>
                <c:when test="${user.getEmailAddress() == null || user.getEmailAddress() == ''}">
                    <input type="text" name="nickname" value="${user.nickname}" required><br>
                </c:when>
                <c:otherwise>
                    <input type="text" name="nickname" value="${user.nickname}" disabled><br>
                </c:otherwise>
            </c:choose>
            <label>E-mail Address: </label>
            <c:choose>
                <c:when test="${user.getEmailAddress() == null || user.getEmailAddress() == ''}">
                    <input type="email" name="emailAddress" value="${user.emailAddress}" required><br>
                </c:when>
                <c:otherwise>
                    <input type="email" name="emailAddress" value="${user.emailAddress}" disabled><br>
                </c:otherwise>
            </c:choose>
            
                
            <label>Birthdate: </label>
            <select name="birthmonth" required value="${user.birthmonth}">
                <option value="01" selected>1</option>
                <option value="02">2</option>
                <option value="03">3</option>
                <option value="04">4</option>
                <option value="05">5</option>
                <option value="06">6</option>
                <option value="07">7</option>
                <option value="08">8</option>
                <option value="09">9</option>
                <option value="10">10</option>
                <option value="11">11</option>
                <option value="12">12</option>
            </select>
            <select name="birthdate" required value="${user.birthdate}">
                <option value="01">1</option>
                <option value="02">2</option>
                <option value="03">3</option>
                <option value="04">4</option>
                <option value="05">5</option>
                <option value="06">6</option>
                <option value="07">7</option>
                <option value="08">8</option>
                <option value="09">9</option>
                <option value="10">10</option>
                <option value="11">11</option>
                <option value="12">12</option>
                <option value="13">13</option>
                <option value="14">14</option>
                <option value="15">15</option>
                <option value="16">16</option>
                <option value="17">17</option>
                <option value="18">18</option>
                <option value="19">19</option>
                <option value="20">20</option>
                <option value="21">21</option>
                <option value="22">22</option>
                <option value="23">23</option>
                <option value="24">24</option>
                <option value="25">25</option>
                <option value="26">26</option>
                <option value="27">27</option>
                <option value="28">28</option>
                <option value="29">29</option>
                <option value="30">30</option>
                <option value="31">31</option>
            </select>
            <select name="birthyear" required value="${user.birthyear}">
                <option value="1980">1980</option>
                <option value="1981">1981</option>
                <option value="1982">1982</option>
                <option value="1983">1983</option>
                <option value="1984">1984</option>
                <option value="1985">1985</option>
                <option value="1986">1986</option>
                <option value="1987">1987</option>
                <option value="1988">1988</option>
                <option value="1989">1989</option>
                <option value="1990">1990</option>
                <option value="1991">1991</option>
                <option value="1992">1992</option>
                <option value="1993">1993</option>
                <option value="1994">1994</option>
                <option value="1995">1995</option>
                <option value="1996">1996</option>
                <option value="1997">1997</option>
                <option value="1998">1998</option>
                <option value="1999">1999</option>
                <option value="2000">2000</option>
                <option value="2001">2001</option>
                <option value="2002">2002</option>
                <option value="2003">2003</option>
                <option value="2004">2004</option>
                <option value="2005">2005</option>
                <option value="2006">2006</option>
                <option value="2007">2007</option>
                <option value="2008">2008</option>
                <option value="2009">2009</option>
                <option value="2010">2010</option>
                <option value="2011">2011</option>
                <option value="2012">2012</option>
                <option value="2013">2013</option>
                <option value="2014">2014</option>
                <option value="2015">2015</option>
                <option value="2016">2016</option>
            </select><br>
            <label>Password: </label>
                <input type="password" name="password" value="${user.password}" required><br>
            <input type="reset" name="reset" value="Reset">
            <c:choose>
                <c:when test="${user.getEmailAddress() == null || user.getEmailAddress() == ''}">
                    <input type="submit" name="submit" value="Submit" onsubmit="validateForm()">
                </c:when>
                <c:otherwise>
                    <input type="submit" name="update" value="Update" onsubmit="validateForm()">
                </c:otherwise>
            </c:choose>
        </form>
                <!--<c:if test="${message != null}">
                    <p><i>${message}</i></p>
                </c:if>-->
                <p><i>${message}</i></p>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
