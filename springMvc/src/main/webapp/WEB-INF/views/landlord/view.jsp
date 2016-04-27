<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" lang="en" http-equiv="Content-Type" content="text/html" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Flat Finder - Edit Profile</title>

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
            <a class="navbar-brand" href="#">Flat Finder - Edit Profile</a>
        </div>
        <%@ include file="/WEB-INF/fragments/navbar.jspf" %>
    </div>
</nav>

<div class="container">

    <div class="jumbotron">
        <br />
        <h1>Your Profile</h1>
        <p>Edit your Landlord account using the form below.</p>
    </div>

    <form:form method="POST" action="edit" modelAttribute="user">
        <table>
            <tr>
                <td>First Name:</td>
                <td><div class="form-group">
                    <form:input role="form" type="text" path="firstName" class="form-control" value="${landlord.firstName}"></form:input>
                </div></td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td><div class="form-group">
                    <form:input role="form" type="text" path="lastName" class="form-control" value="${landlord.lastName}"></form:input>
                </div></td>
            </tr>
            <tr>
                <td>Email Address:</td>
                <td><div class="form-group">
                    <input role="form" type="text" name="emailAddress" class="form-control" value="${usr.emailAddress}"></input>
                </div></td>
            </tr>

            <tr>
                <td><input type="submit" value="Save" class="btn btn-success"/></td>
            </tr>

        </table>
    </form:form>

</div>
<hr />

<footer class="container">
    <p>&copy; CO2015 - Group 6</p>
</footer>

</body>
</html>