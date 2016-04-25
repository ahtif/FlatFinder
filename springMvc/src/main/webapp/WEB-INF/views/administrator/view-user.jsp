<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" lang="en" http-equiv="Content-Type" content="text/html" />
    <title>Flat Finder - Manage Users</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

    <script>
        function toggleBuddy(){
            var btn = document.getElementsByName("buddyPref");
            if(btn[0].value == "true"){
                btn[0].value = "false";
                document.getElementById("buddy").innerHTML = "I am not a buddy";
            } else if(btn[0].value == "false"){
                btn[0].value = "true";
                document.getElementById("buddy").innerHTML = "I am a buddy";
            }
        }
    </script>

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
            <a class="navbar-brand" href="#">Flat Finder - Administrator</a>
        </div>
        <%@ include file="/WEB-INF/fragments/navbar.jspf" %>
    </div>
</nav>

<div class="container">
    <div class="jumbotron">
        <h1>Manage Users</h1>
        <p>Here you can view all the users in the system and choose to edit/suspend them.</p>
    </div>
    
    
    <form:form method="POST" action="/admin/user/edit" modelAttribute="user">
        <input type="hidden" name="id" value="${usr.id}" />
        <table>
            <tr>
                <td>First Name:</td>
                <td><div class="form-group">
                    <input role="form" type="text" name="firstName" class="form-control" value="${searcher != null ? searcher.firstName : landlord.firstName}"></input>
                </div></td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td><div class="form-group">
                    <input role="form" type="text" name="lastName" class="form-control" value="${searcher != null ? searcher.lastName : landlord.lastName}"></input>
                </div></td>
            </tr>
            <tr>
                <td>Email Address:</td>
                <td><div class="form-group">
                    <input role="form" type="text" name="emailAddress" class="form-control" value="${usr.emailAddress}"></input>
                </div></td>
            </tr>
            <c:if test = "${searcher != null}">
                <tr>
                    <td>Buddy Preference:</td>
                    <td><div class="form-group">
                        <c:if test="${searcher.buddyPref}">
                            <button type="button" id="buddy" onclick="toggleBuddy()">I am a buddy</button>
                            <input role="form" type="hidden" name="buddyPref" class="form-control" value="true"/>
                        </c:if>
                        <c:if test="${!searcher.buddyPref}">
                            <button type="button" id="buddy" onclick="toggleBuddy()">I am not a buddy</button>
                            <input role="form" type="hidden" name="buddyPref" class="form-control" value="false"/>
                        </c:if>
                    </div></td>
                </tr>
               
           </c:if>
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