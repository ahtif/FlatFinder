<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" lang="en" http-equiv="Content-Type" content="text/html" />
    <title>Flat Finder - Searcher Home</title>

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
            <a class="navbar-brand" href="#">Flat Finder - Searcher Home</a>
        </div>
        <%@ include file="/WEB-INF/fragments/navbar.jspf" %>
    </div>
</nav>

<div class="container">

    <div class="jumbotron">
        <br />
        <h1>Property Search</h1>
        <p>As a Searcher you can actively search for listed properties using the form below.</p>
    </div>

    <form action="/searchProperties" method="get">

        <table>
            <tr>
                <td>Property Keyword:</td>
                <td><div class="form-group">
                    <input role="form" class="form-control" type="text" name="pKeyword"
                           pattern="[0-9a-zA-Z]+" placeholder="Enter a street, city or postcode" required="required" />
                </div></td>
            </tr>

            <tr>
                <td>Property Type:</td>
                <td><div class="form-group">
                    <select name="pType" class="form-control" required="required">
                        <c:forEach items="${types}" var="type">
                            <option value="${type.id}">${type.type}</option>
                        </c:forEach>
                    </select>
                </div></td>
            </tr>

            <tr>
                <td>Number of Rooms:</td>
                <td><div class="form-group">
                    <select name="pRooms" class="form-control" required="required">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                    </select>
                </div></td>
            </tr>

            <tr>
                <td colspan="2"><input type="submit" value="Search Properties" /></td>
            </tr>

        </table>
    </form>

</div>
<hr />

<footer class="container">
    <p>&copy; CO2015 - Group 6</p>
</footer>

</body>
</html>