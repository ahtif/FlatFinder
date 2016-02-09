<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" lang="en" http-equiv="Content-Type" content="text/html" />
    <title>Welcome Landlord</title>
</head>
<body>

<h1>LANDLORD VIEW</h1>
<p>You have successfully logged in as an Landlord.</p>

<c:url value="/logout" var="logoutUrl" />
<form action="${logoutUrl}" method="get">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <button type="submit">Log Out</button>
</form>

</body>
</html>