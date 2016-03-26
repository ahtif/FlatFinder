<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" lang="en" http-equiv="Content-Type" content="text/html" />
    <title>Your Profile</title>
        
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
          <a class="navbar-brand" href="#">Flat Finder- Landlord View</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li><a href="/success-login">Home</a></li>
            <li><a href="/profile">Profile</a></li>           
            <li><a href="/messaging">Inbox</a></li>
            <li class="dropdown">
              <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Properties <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="/property/add">Add a Property</a></li>
                <li><a href="/property/viewAll">View all properties</a></li>
              </ul>
            </li>
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

<br /><br /><br />

    <div class="jumbotron">
        <h1>Your Profile</h1>
        <p>If you would like to change any aspects of your profile, do so below and press save.</p>
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
                <form:input role="form" type="text" path="emailAddress" class="form-control" value="${landlord.emailAddress}"></form:input>
            </div></td>
        </tr>

            <td><input type="submit" value="Save" class="btn btn-success"/></td>
            <td><div align="right"><a href="/success-login">Go back</a></div></td>
        </tr>
            
        </tr>
    </table>
    
</form:form>

</div>
<hr>

      <footer>
        <p>&copy; CO2015- Group 6</p>
      </footer>

</body>
</html>