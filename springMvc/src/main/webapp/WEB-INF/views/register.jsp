<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
    
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
ss-login">Home</a></p>
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
<h1>Registration</h1>
<c:if test="${usernameLength != null}">
    <p><strong class="notice">Your username is not between 3 and 15 characters.</strong></p>
</c:if>
<c:if test="${usernameExists != null}">
    <p><strong class="notice">Your username is not available, please choose another.</strong></p>
</c:if>
<c:if test="${passwordLength != null}">
    <p><strong class="notice">Your password is not between 8 and 20 characters.</strong></p>
</c:if>
<c:if test="${passwordMismatch != null}">
	<p><strong class="notice">Your passwords did not match.</strong></p>
</c:if>
<form:form method="POST" action="/createAccount" modelAttribute="User">
	<table>
		<tr>
			<td>Enter Username:</td>
			<td><div class="form-group">
			    <form:input role="form" type="text" path="login" class="form-control" 
			     pattern=".{3,15}" title="Please enter in between 3 and 15 characters." 
			     placeholder="Username"></form:input>
			</div></td>
		</tr>
		<tr>
			<td>Enter Password:</td>
			<td><div class="form-group">
			    <form:input role="form" type="password" path="password" class="form-control" 
			     pattern=".{8,20}" title="Please enter in between 8 and 20 characters." 
			     placeholder="Password"></form:input>
			</div></td>
		</tr>
        <tr>
            <td>Confirm Password:</td>
            <td><div class="form-group">
                <input role="form" type="password" class="form-control" name="cPassword"
                 pattern=".{8,20}" title="Please enter in between 8 and 20 characters." 
                 placeholder="Confirm Password"/>
            </div></td>
          
        </tr>
		<tr>
			<td>Choose user type:</td>
			<div class="form-group">
			<td><form:select class="form-control" path="role" label="">
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

<hr>

      <footer>
        <p>&copy; CO2015- Group 6</p>
      </footer>


</body>
</html>