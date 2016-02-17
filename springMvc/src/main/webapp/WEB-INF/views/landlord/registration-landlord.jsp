<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Landlord Registration</title>
</head>
<body>
<h1>Landlord Registration</h1>
<form:form modelAttribute="Landlord" method="POST" action="addLandlord">
    <table>
        <tr>
            <td>First Name:</td>
            <td><form:input path="firstName" value="" /></td>
        </tr>

        <tr>
            <td>Last Name:</td>
            <td><form:input path="lastName" value="" /></td>
        </tr>

        <tr>
            <td>Address:</td>
            <td><form:input path="address" value="" /></td>
        </tr>

        <tr>
            <td>City:</td>
            <td><form:input path="city" value="" /></td>
        </tr>

        <tr>
            <td>County:</td>
            <td><form:input path="county" value="" /></td>
        </tr>

        <tr>

            <td>Mobile/Telephone:</td>
            <td><form:input path="contactNo" value="" /></td>
        </tr>

        <tr>
            <td>Email:</td>
            <td><form:input path="emailAddress" value="" /></td>
        </tr>

        <tr>
            <td>Password:</td>
            <td><form:input path="password" value="" type="password" /></td>
        </tr>
        <tr>
            <td>Confirm Password:</td>
            <td><form:input path="confirmPassword" value="" type="password" /></td>
        </tr>
        <tr>
            <td><button type="submit" value="Submit">Submit</button></td>
        </tr>
</form:form>
</body>
</html>