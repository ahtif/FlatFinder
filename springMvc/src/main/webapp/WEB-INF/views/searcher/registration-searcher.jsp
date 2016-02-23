<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Searcher Registration</title>
</head>
<body>
<h1>Searcher Registration</h1>
<form:form modelAttribute="Searcher" method="POST" action="/addSearcher">

    <c:if test="${id > 0}">

    </c:if>
    <c:if test="${id == 0}">

    </c:if>

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
            <td><button type="submit" value="Submit">Submit</button></td>
        </tr>
</form:form>
</body>
</html>