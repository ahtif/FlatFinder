<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" lang="en" http-equiv="Content-Type" content="text/html" />
    <title>Login page</title>
    <style>
        .notice {color: red;}
    </style>
</head>
<body>

<h1>Login page</h1>

<p>
    <c:if test="${error == true}">
        <strong class="notice">Invalid login or password.</strong>
    </c:if>
    <c:if test="${logout == true}">
        <strong class="notice">You have been logged out.</strong>
    </c:if>
</p>

<c:url value="/login" var="loginUrl"/>
<form action="${loginUrl}" method="post" modelAttribute="user">
    <p>
        <label for="username">Username</label>
        <input type="text" id="username" name="username" placeholder="Your Username" required="required" />
    </p>
    <p>
        <label for="password">Password</label>
        <input type="password" id="password" name="password" placeholder="Your Password" required="required" />
    </p>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <button type="submit">Log in</button>
</form>
	<a href="/register">Register</a>
</body>
</html>