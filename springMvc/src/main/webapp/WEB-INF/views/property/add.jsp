<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Property</title>
</head>
<body>
<h1>Add Property</h1>
<form method="POST" action="/property/addPost">
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
            <td><input type="text" name="pPostcode" pattern="[a-zA-Z0-9 ]+" required="required" /></td>
        </tr>

        <tr>
            <td>Property Type:</td>
            <td>
                <select name="pType" required="required">
                    <c:forEach items="${types}" var="type">
                        <option value="${type.id}">${type.type}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>

        <tr>
            <td>Number of Rooms:</td>
            <td>
                <select name="pRooms" required="required">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                </select>
            </td>
        </tr>

		<tr>
			<td colspan="2"><input type="submit" value="Add Property"/></td>
		</tr>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</table>

</form>
</body>
</html>