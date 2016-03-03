<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Properties</title>
</head>
<body>
<h1>View Properties</h1>

<c:forEach items="${properties}" var="property">
    <p><a href="/property/view/${type.id}">${property.number}, ${property.street}</a></p>
</c:forEach>

</body>
</html>