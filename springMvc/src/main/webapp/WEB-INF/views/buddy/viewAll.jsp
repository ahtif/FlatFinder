<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" lang="en" http-equiv="Content-Type" content="text/html" />
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
        <p>Below you can see a list of all your buddies and view their profile.</p>
    </div>

    <c:if test="${requested != null}">
        <div class="alert alert-success" role="alert">
            <strong>Success!</strong> You have successfully requested a buddy up with the user.
        </div>
    </c:if>

    <c:if test="${accepted != null}">
        <div class="alert alert-success" role="alert">
            <strong>Success!</strong> You have successfully accepted a buddy up with the user.
        </div>
    </c:if>

    <c:if test="${rejected != null}">
        <div class="alert alert-success" role="alert">
            <strong>Success!</strong> You have successfully rejected a buddy up with the user.
        </div>
    </c:if>

    <c:if test="${notBuddy != null}">
        <div class="alert alert-danger" role="alert">
            <strong>Warning!</strong> You cannot view that profile as you are not buddies with that user or that user does not exist.
        </div>
    </c:if>

    <h3>Your pending buddy requests</h3>
    <table class="table table-hover">
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Options</th>
        </tr>
        <c:forEach items ="${pending}" var ="request">
            <tr>
                <td>${request.sender.firstName}</td>
                <td>${request.sender.lastName}</td>
                <td>
                    <a href="/buddy/accept/${request.id}" class="btn btn-default">Accept</a>
                    <a href="/buddy/reject/${request.id}" class="btn btn-default">Reject</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <h3>Your buddies</h3>
    <table class="table table-hover">
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Options</th>
        </tr>
        <c:forEach items ="${sentBuddies}" var ="request">
            <tr>
                <td>${request.receiver.firstName}</td>
                <td>${request.receiver.lastName}</td>
                <td>
                    <a href="/buddy/viewBuddy/${request.receiver.id}" class="btn btn-default">View Profile</a>
                    <a href="/messaging/new?contact=${request.receiver.id}" class="btn btn-default">Contact</a>
                </td>
            </tr>
        </c:forEach>
        <c:forEach items ="${acceptedBuddies}" var ="request">
            <tr>
                <td>${request.sender.firstName}</td>
                <td>${request.sender.lastName}</td>
                <td>
                    <a href="/buddy/viewBuddy/${request.sender.id}" class="btn btn-default">View Profile</a>
                    <a href="/messaging/new?contact=${request.sender.id}" class="btn btn-default">Contact</a>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>

<hr />

<footer class="container">
    <p>&copy; CO2015 - Group 6</p>
</footer>

</body>
</html>