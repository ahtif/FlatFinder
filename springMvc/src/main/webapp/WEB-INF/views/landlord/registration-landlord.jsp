<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Landlord Registration</title>
    
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
          <a class="navbar-brand" href="#">Flat Finder</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="/success-login">Home</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    <div class="container">
    <div class="jumbotron">
        <h1>Registration</h1>
        <p>Please fill in your details below to create your account, all fields are mandatory.</p>
      </div>
      </div>
<div class="container">
<h1>Landlord Registration</h1>
<form:form modelAttribute="Landlord" method="POST" action="/addLandlord">
    <table>
        <tr>
            <td>First Name:</td>
            <td><div class="form-group">
			    <form:input role="form" type="text" path="firstName" class="form-control" placeholder="First Name"></form:input>
			</div></td>
        </tr>

        <tr>
            <td>Last Name:</td>
            <td><div class="form-group">
			    <form:input role="form" type="text" path="lastName" class="form-control" placeholder="Last Name"></form:input>
			</div></td>
        </tr>

        <tr>
            <td>Email:</td>
            <td><div class="form-group">
			    <form:input role="form" type="text" path="emailAddress" class="form-control" placeholder="Email Address"></form:input>
			</div></td>
        </tr>
        
        <tr>
            <td><button type="submit" value="Submit" class="btn btn-success">Submit</button></td>
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