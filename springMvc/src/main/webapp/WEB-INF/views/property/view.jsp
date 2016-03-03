<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Property</title>
</head>
<body>
<h1>View Property</h1>

<p>Property Number: ${property.number}</p>
<p>Property Street: ${property.street}</p>
<p>Property City: ${property.city}</p>
<p>Property PostCode: ${property.postcode}</p>
<p>Property Type: ${property.type.type}</p>
<p>Property Rooms: ${property.rooms}</p>

<p><a href="/property/viewAll">View All Your Properties</a></p>

<p><a href="/success-login">Home</a></p>

</body>
</html>