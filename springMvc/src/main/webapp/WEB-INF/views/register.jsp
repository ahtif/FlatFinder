<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
</head>
<body>
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
			<td><form:input
                    type="text" path="login" required="required"
                    pattern=".{3,15}" title="Please enter in between 3 and 15 characters." placeholder="Username"></form:input>
            </td>
		</tr>
		<tr>
			<td>Enter Password:</td>
			<td>
                <form:input
                        type="password" path="password" required="required"
                        pattern=".{8,20}" title="Please enter in between 8 and 20 characters." placeholder="Password"></form:input>
            </td>
		</tr>
        <tr>
            <td>Confirm Password:</td>
            <td><input
                    type="password" name="cPassword" required="required"
                    pattern=".{8,20}" title="Please enter in between 8 and 20 characters." placeholder="Confirm Password" />
            </td>
        </tr>
		<tr>
			<td>Choose user type:</td>
			<td><form:select path="role" required="required">
				<form:option value="Searcher" label="Searcher"></form:option>
				<form:option value="Landlord" label="Landlord"></form:option>
			</form:select></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="Create account"/></td>
		</tr>
	</table>
	
</form:form>
</body>
</html>