<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Flat Finder - Register</title>

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
            <a class="navbar-brand">Flat Finder</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/">Home</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="container">

    <div class="container">
        <div class="jumbotron">
            <br />
            <h1>Register With Flat Finder</h1>
            <p>Please fill in your details using the form below.</p>
        </div>
    </div>

    <c:if test="${usernameLength != null}">
        <div class="alert alert-danger" role="alert">
            <strong>Sorry!</strong> Your username is not between 3 and 15 characters.
        </div>
    </c:if>
    <c:if test="${emailTaken != null}">
        <div class="alert alert-danger" role="alert">
            <strong>Sorry!</strong> The email address is not available.
        </div>
    </c:if>
    <c:if test="${usernameExists != null}">
        <div class="alert alert-danger" role="alert">
            <strong>Sorry!</strong> Your username is not available, please choose another.
        </div>
    </c:if>
    <c:if test="${passwordLength != null}">
        <div class="alert alert-danger" role="alert">
            <strong>Sorry!</strong> Your password is not between 8 and 20 characters.
        </div>
    </c:if>
    <c:if test="${passwordMismatch != null}">
        <div class="alert alert-danger" role="alert">
            <strong>Sorry!</strong> Your passwords did not match.
        </div>
    </c:if>
    <c:if test="${emailFormat != null}">
        <div class="alert alert-danger" role="alert">
            <strong>Sorry!</strong> Please enter a valid email.
        </div>
    </c:if>

    <form:form method="POST" action="/createAccount" modelAttribute="User">
        <table>
            <tr>
                <td>Enter Username:</td>
                <td><div class="form-group">
                    <form:input role="form" type="text" path="login" class="form-control"
                                pattern=".{3,15}" title="Please enter in between 3 and 15 characters."
                                placeholder="Username" required="required"></form:input>
                </div></td>
            </tr>
            <tr>
                <td>Enter Password:</td>
                <td><div class="form-group">
                    <form:input role="form" type="password" path="password" class="form-control"
                                pattern=".{8,20}" title="Please enter in between 8 and 20 characters."
                                placeholder="Password" required="required"></form:input>
                </div></td>
            </tr>
            <tr>
                <td>Confirm Password:</td>
                <td><div class="form-group">
                    <input role="form" type="password" class="form-control" name="cPassword"
                           pattern=".{8,20}" title="Please enter in between 8 and 20 characters."
                           placeholder="Confirm Password" required="required" />
                </div></td>

            </tr>
            <tr>
                <td>Email:</td>
                <td><div class="form-group">
                    <form:input role="form" type="email" path="emailAddress" class="form-control"
                                placeholder="Email Address" required="required"></form:input>
                </div></td>
            </tr>
            <tr>
                <td>Choose user type:</td>
                <div class="form-group">
                    <td><form:select class="form-control" path="role" required="required">
                            <form:option class="form-control" value="Searcher" label="Searcher"></form:option>
                            <form:option class="form-control" value="Landlord" label="Landlord"></form:option>
                    </form:select></div></td>
            </tr>
            <tr><td><br /></td></tr>
            <tr>
                <td colspan="2"><input type="submit" value="Create account" class="btn btn-success"/></td>
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