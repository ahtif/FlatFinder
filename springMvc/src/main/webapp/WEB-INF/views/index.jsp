<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" lang="en" http-equiv="Content-Type" content="text/html" />
    <title>Flat Finder</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="">Flat Finder</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <c:url value="/login" var="loginUrl"/>
            <form class="navbar-form navbar-right" action="${loginUrl}" method="post" modelAttribute="user">
                <div class="form-group">
                    <input id="username" name="username" type="text" placeholder="Username" class="form-control" required>
                </div>
                <div class="form-group">
                    <input id="password" name="password" type="password" placeholder="Password" class="form-control" required>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <button type="submit" class="btn btn-success">Log in</button>
                <a href="/register" class="btn btn-success">Register</a>
                <a href="/forgot" class="btn btn-success">Forgot Password?</a>
            </form>
        </div><!--/.navbar-collapse -->
    </div>
</nav>

<div class="container">
    <div class="jumbotron">
        <br />
        <h1>Welcome to Flat Finder&hellip;</h1>
        <p>Register as either a Landlord or a Searcher today to start viewing and posting properties.</p>
    </div>
    <p>
        <c:if test="${error == true}">
    <div class="alert alert-danger" role="alert">
        <strong>Sorry!</strong> ${SPRING_SECURITY_LAST_EXCEPTION}
    </div>
    </c:if>
    <c:if test="${logout == true}">
        <div class="alert alert-success" role="alert">
            <strong>See you again soon!</strong> You have been successfully logged out.
        </div>
    </c:if>
    </p>
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>

        <!-- Wrapper for slides - !!!ensure image width is maximised!!! -->
        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <img src="<c:url value="/resources/images/index/carousel0.png" />" alt="" width="9999" height="640" />
            </div>

            <div class="item">
                <img src="<c:url value="/resources/images/index/carousel1.png" />" alt="" width="9999" height="640" />
            </div>

            <div class="item">
                <img src="<c:url value="/resources/images/index/carousel2.png" />" alt="" width="9999" height="640" />
            </div>
        </div>

        <!-- Left and right controls -->
        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div>

<hr />

<footer class="container">
    <p>&copy; CO2015 - Group 6</p>
</footer>

</body>
</html>