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

<h1><c:out value="${message.subject}"/></h1>

<h3>From: <c:out value="${message.senderName}"/></h3>
<h3>Date: <c:out value="${message.messageDate}"/></h3>
<br />

<p> <c:out value="${message.message}"/> </p>
<br />
<h3> Reply: </h3>
<form:form action="/messaging/sendMessage" modelAttribute="messageAttribute" id="replyForm">
<form:input type="hidden" value="${message.senderName}" path="receiver"/>
<form:input type="hidden" value="${message.id}" path="parent"/>
<form:input type="hidden" value="RE: ${message.subject}" path="subject"/>
<form:textarea form="replyForm" rows="5" cols="50" path="message"/><br />
<input type="submit" value="Reply"/>
</form:form>


</body>
</html>