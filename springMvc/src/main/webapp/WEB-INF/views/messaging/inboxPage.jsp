<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" lang="en" http-equiv="Content-Type" content="text/html" />
    <title>Flat Finder - Message Inbox</title>

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
            <a class="navbar-brand" href="#">Flat Finder - Message Inbox</a>
        </div>
        <%@ include file="/WEB-INF/fragments/navbar.jspf" %>
    </div>
</nav>
<div class="container">

    <div class="jumbotron">
        <br />
        <h1>Message Inbox</h1>
        <p>This is your message inbox where you can send and receive email.</p>
    </div>

    <table class="table table-striped">
        <tr>
            <th> From </th>
            <th> Subject </th>
            <th> Message </th>
        </tr>
        <c:forEach items ="${messages}" var ="message">
            <tr>
                <td><a href="/messaging/view?id=${message.id}"><c:out value="${message.senderName}"/></a></td>
                <td><a href="/messaging/view?id=${message.id}"><c:out value="${message.subject}"/></a></td>
                <td><a href="/messaging/view?id=${message.id}"><c:out value="${message.message}"/></a></td>

            </tr>
        </c:forEach>
    </table>
    <p><a href="/messaging/new" class="btn btn-success">Compose</a></p>
</div>

<hr />

<footer class="container">
    <p>&copy; CO2015 - Group 6</p>
</footer>

</body>
</html>