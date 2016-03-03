<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Property</title>
</head>
<body>
<h1>Add Property</h1>
<form:form method="POST" action="/property/add">
	<table>
		<tr>
			<td>Property Number:</td>
			<td><input type="text" name="pNumber" pattern="[0-9a-z ]+" required="required" /></td>
		</tr>

		<tr>
            <td>Property Street:</td>
            <td><input type="text" name="pStreet" pattern="[A-Za-z ]+" required="required" /></td>
		</tr>

        <tr>
            <td>Property City:</td>
            <td><input type="text" name="pCity" pattern="[A-Za-z ]+" required="required" /></td>
        </tr>

        <tr>
            <td>Property PostCode:</td>
            <td><input type="text" name="pPostcode" pattern="[A-Z0-9 ]+" required="required" /></td>
        </tr>

        <tr>
            <td>Number of Rooms:</td>
        </tr>

        <tr>
            <td>Property Type:</td>
        </tr>

		<tr>
			<td colspan="2"><input type="submit" value="Add Property"/></td>
		</tr>
	</table>
	
</form:form>
</body>
</html>