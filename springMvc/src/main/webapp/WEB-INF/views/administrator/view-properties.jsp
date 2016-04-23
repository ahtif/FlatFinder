=<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" lang="en" http-equiv="Content-Type" content="text/html" />
    <title>Flat Finder - Manage Properties</title>

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
            <a class="navbar-brand" href="#">Flat Finder - Administrator Home</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="/success-login">Home</a></li>
                <li><a href="/messaging/inbox">Inbox</a></li>
                <li><a href="/admin/viewProperties">Manage Properties</a></li>
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
        <h1>Manage Properties</h1>
        <p>Here you can view all the properties in the system and choose to edit/delete them.</p>
    </div>
    
    <c:if test="${deleted != null}">
        <div class="alert alert-success" role="alert">
            <strong>Success!</strong> You have successfully deleted the property from the database.
        </div>
    </c:if>
    <c:if test="${edited != null}">
        <div class="alert alert-success" role="alert">
            <strong>Success!</strong> You have successfully edited a property.
        </div>
    </c:if>
    
    <table class="table table-hover">
        <tr>
            <th>ID</th>
            <th>Landlord</th>
            <th>Property Type</th>
            <th>Number</th>
            <th>Street</th>
            <th>City</th>
            <th>Postcode</th>
            <th>Options</th>
        </tr>
        <c:forEach items ="${properties}" var ="property">
            <tr>
                <td>${property.id}</td>
                <td>${property.landlord.firstName} ${property.landlord.lastName}</td>
                <td>${property.type.type}</td>
                <td>${property.number}</td>
                <td>${property.street}</td>
                <td>${property.city}</td>
                <td>${property.postcode}</td>
                <td><a href="/property/view/${property.id}" class="btn btn-default">View</a>
                    <a href="/admin/property/delete/${property.id}" class="btn btn-default">Delete</a></td>
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