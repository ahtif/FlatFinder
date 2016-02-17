<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Searcher Registration</title>
</head>
<body>
<h1>Searcher Registration</h1>
<form:form modelAttribute="Searcher" method="POST" action="addSearcher">
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
        <%-- Broken --%>
        <%--<tr>--%>
            <%--<td><form:radiobutton path="isBuddy" value="true" label="YES"/></td>--%>
            <%--<td><form:radiobutton path="isBuddy" value="false" label="No"/></td>--%>
        <%--</tr>--%>
        <tr>
            <td><button type="submit" value="Submit">Submit</button></td>
        </tr>
</form:form>
</body>
</html>