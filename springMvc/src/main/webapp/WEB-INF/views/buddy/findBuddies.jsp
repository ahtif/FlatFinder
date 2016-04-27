<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" lang="en" http-equiv="Content-Type" content="text/html" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Flat Finder - My Buddies</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

</head>
<body>

    <!-- Fixed navbar -->
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Flat Finder - My Buddies</a>
            </div>
            <%@ include file="/WEB-INF/fragments/navbar.jspf" %>
        </div>
    </nav>
    <div class="container">
        
        <div class="jumbotron">
            <br />
            <h1>Buddies</h1>
            <p>Below you can see a list of all available buddies and request to be buddies with them.</p>
        </div>
        
        <c:if test="${notBuddy}">
            <h4> You must first opt in to be a buddy before you can find other's to buddy up with. <br />
                Click <a href="/profile">here</a> to go to your profile and change your buddy preference.    
            </h4>
        </c:if>
        
        <c:if test="${!notBuddy}">
            <table class="table table-hover">
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Options</th>
                </tr>
                <c:forEach items ="${buddies}" var ="buddy">
                    <tr>
                        <td>${buddy.firstName}</td>
                        <td>${buddy.lastName}</td>
                        <td> <a href="/buddy/request/${buddy.id}" class="btn btn-default">Request to be a Buddy</a> </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        
        

</div>

<hr />

<footer class="container">
    <p>&copy; CO2015 - Group 6</p>
</footer>

</body>
</html>