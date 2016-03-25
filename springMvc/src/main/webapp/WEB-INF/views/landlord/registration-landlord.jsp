<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Landlord Registration</title>
</head>
<body>
<h1>Landlord Registration</h1>
<form:form modelAttribute="Landlord" method="POST" action="/addLandlord">
    <table>
        <tr>
            <td>First Name:</td>
            <td><form:input path="firstName" required="required" placeholder="First Name" /></td>
        </tr>
        <tr>
            <td>Last Name:</td>
            <td><form:input path="lastName" required="required" placeholder="Last Name" /></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><form:input path="emailAddress" type="email" required="required" placeholder="Email" /></td>
        </tr>
        <tr>
            <td><button type="submit" value="Submit">Submit</button></td>
        </tr>
</form:form>
</body>
</html>