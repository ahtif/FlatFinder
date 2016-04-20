<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" lang="en" http-equiv="Content-Type" content="text/html" />
    <title>Flat Finder - View Message</title>

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
            <a class="navbar-brand" href="#">Flat Finder - View Message</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="/success-login">Home</a></li>
                <li><a href="/messaging">Inbox</a></li>
            </ul>
            <c:url value="/logout" var="logoutUrl"/>
            <form class="navbar-form navbar-right" action="${logoutUrl}" method="get">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <button class="btn btn-success" type="submit">Log Out</button>
            </form>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="container">

    <div class="jumbotron">
        <br />
        <h1>View Message</h1>
    </div>

    <table>
        <tr>
            <td colspan="2"><h1><c:out value="${message.subject}"/></h1></td>
        </tr>
        <tr>
            <td><h3>From:</h3></td>
            <td><h3><c:out value="${message.senderName}"/></h3></td>
        </tr>
        <tr>
            <td><h3>Date:</h3></td>
            <td><h3><c:out value="${message.messageDate}"/></h3></td>
        </tr>
        <tr>
            <td colspan="2"><p> <c:out value="${message.message}"/> </p></td>
        </tr>
        <tr>
        <tr>
            <td colspan="2"><h3> Reply: </h3></td>
        </tr>
        <td colspan="2">
            <form:form action="/messaging/sendMessage" modelAttribute="messageAttribute" id="replyForm">
            <form:input type="hidden" value="${message.senderName}" path="receiver"/>
            <form:input type="hidden" value="${message.id}" path="parent"/>
            <form:input type="hidden" value="RE: ${message.subject}" path="subject"/>
            <div class="form-group">
                    <form:textarea role="form" rows="5" cols="50" path="message" form="replyForm" class="form-control"/>
        </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Send reply" class="btn btn-success"/>
            </td>
        </tr>
        </form:form>
    </table>

    <hr />

    <footer class="container">
        <p>&copy; CO2015 - Group 6</p>
    </footer>

</div>
</body>
</html>