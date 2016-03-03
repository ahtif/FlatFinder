<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" lang="en" http-equiv="Content-Type" content="text/html" />
    <title>Your Profile</title>
</head>
<body>

<h1>Your Profile</h1>

<form:form method="POST" action="edit" modelAttribute="user">
    <table>
        <tr>
            <td>First Name:</td>
            <td><form:input type="text" path="firstName" value="${searcher.firstName}"></form:input></td>
        </tr>
        <tr>
            <td>Last Name:</td>
            <td><form:input type="text" path="lastName" value="${searcher.lastName}"></form:input></td>
        </tr>
        <tr>
            <td>Email Address:</td>
            <td><form:input type="text" path="emailAddress" value="${searcher.emailAddress}"></form:input></td>
        </tr>
       
        <tr>
            <td>Buddy Preference</td>
	            <td><form:select path="buddyPref">
	            <form:option value="true">I want to be a buddy</form:option>
                <form:option value="false">I do not want to be a buddy</form:option>
	            
	            
	            </form:select></td>
        <tr>
            <td colspan="2"><input type="submit" value="Save"/></td>
        </tr>
            
        </tr>
    </table>
     
     <p><a href="/success-login">Go back</a></p>
    
    
</form:form>

</body>
</html>