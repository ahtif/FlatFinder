<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" lang="en" http-equiv="Content-Type" content="text/html" />
    <title>Inbox</title>
</head>
<body>

<h1>Your Inbox</h1><br />

<table>
	<tr>
		<th> From </th>
		<th> Subject </th>
		<th> Message </th>
	</tr>
	<c:forEach items ="${messages}" var ="message">
		<tr>
			<td><c:out value="${message.senderName}"/></td>
			<td><c:out value="${message.subject}"/></td>
			<td><c:out value="${message.message}"/></td>
		</tr>
	</c:forEach>
</table>



</body>
</html>