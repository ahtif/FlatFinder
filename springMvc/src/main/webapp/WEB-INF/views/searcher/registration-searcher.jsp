<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
            <td><form:input path="firstName" required="required" placeholder="First Name" /></td>
        </tr>

        <tr>
            <td>Last Name:</td>
            <td><form:input path="lastName" required="required" placeholder="Last Name" /></td>
        </tr>

        <tr>
            <td>Email:</td>
            <td><form:input type="email" path="emailAddress" required="required" placeholder="Email" /></td>
        </tr>

		<tr>
            <td>Would you like to be a buddy?</td>
            <td><form:radiobutton path="buddyPref" value="true" label="Yes"/>
				<form:radiobutton path="buddyPref" value="false" label="No"/></td>
        </tr>

        <tr>
            <td><button type="submit" value="Submit">Submit</button></td>
        </tr>
   
</form:form>
</body>
</html>