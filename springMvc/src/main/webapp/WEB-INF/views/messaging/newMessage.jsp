<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" lang="en" http-equiv="Content-Type" content="text/html" />
    <title>Send a Message</title>
</head>
<body>

	<h1>New Message</h1><br />
	
	<form:form action="/messaging/sendMessage" method="post" modelAttribute="message" id="messageForm">
		To: <form:input type="text" path="receiver"/><br />
		Subject: <form:input type="text" path="subject"/><br />
		<form:textarea rows="5" cols="50" path="message" form="messageForm"/><br />
		<input type="submit" value="Send"/>
	</form:form>
	
	    <p><a href="/messaging">Go back</a></p>
	

</body>
</html>