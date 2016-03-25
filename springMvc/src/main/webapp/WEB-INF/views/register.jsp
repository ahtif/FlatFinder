<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Registration</h1>
<c:if test="${usernameExists != null}">
	<strong class="notice">This username is not available, please choose another.</strong>
</c:if>
<form:form method="POST" action="/createAccount" modelAttribute="User">
	<table>
		<tr>
			<td>Enter Username:</td>
			<td><form:input type="text" path="login" required="required"></form:input></td>
		</tr>
		<tr>
			<td>Enter Password:</td>
			<td><form:input type="password" path="password" required="required"></form:input></td>
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