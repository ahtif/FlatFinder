<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" lang="en" http-equiv="Content-Type" content="text/html" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Flat Finder - Mass Message</title>

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
            <a class="navbar-brand" href="#">Flat Finder - Mass Message</a>
        </div>
        <%@ include file="/WEB-INF/fragments/navbar.jspf" %>
    </div>
</nav>

<div class="container">

    <div class="jumbotron">
        <br />
        <h1>Mass Message</h1>
        <p>Please use the form below to mass message all or a specific subset of users.</p>
    </div>

    <c:if test="${error != null}">
        <div class="alert alert-danger" role="alert">
            <strong>Sorry!</strong> Please enter all values.
        </div>
    </c:if>

    <c:if test="${success != null}">
        <div class="alert alert-success" role="alert">
            <strong>Message Sent!</strong> All messages were successfully sent.
        </div>
    </c:if>

    <form action="/admin/broadcast/send" method="post" id="messageForm">
        <table>
            <tr>
                <td>Subject:</td>
                <td><div class="form-group">
                    <input type="text" name="subject" placeholder="Subject&hellip;" class="form-control" required="required" />
                </div></td>
            </tr>
            <tr>
                <td>Message:</td>
                <td colspan="2"><div class="form-group">
                    <textarea rows="5" cols="50" name="message" form="messageForm" placeholder="Your Message" class="form-control" required="required"></textarea>
                </div></td>
            </tr>
            <tr>
                <td>Send To:</td>
                <td>
                    <select name="sendTo" required="required">
                        <option value="all">All</option>
                        <option value="searchers">Searchers</option>
                        <option value="landlords">Landlords</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Mass Message" class="btn btn-success"/>
                </td>
            </tr>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </table>
    </form>
</div>

<hr />

<footer class="container">
    <p>&copy; CO2015 - Group 6</p>
</footer>

</body>
</html>