<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" lang="en" http-equiv="Content-Type" content="text/html" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Flat Finder - Add Property</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

</head>
<body>
<!-- Fixed navbar -->
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Flat Finder - Add Property</a>
        </div>
        <%@ include file="/WEB-INF/fragments/navbar.jspf" %>
    </div>
</nav>
<div class="container">

    <div class="jumbotron">
        <br />
        <h1>Add Property</h1>
    </div>

    <c:if test="${invalidNumber != null}">
        <div class="alert alert-danger" role="alert">
            <strong>Sorry!</strong> The property number which was entered is invalid.
        </div>
    </c:if>

    <c:if test="${streetInvalid != null}">
        <div class="alert alert-danger" role="alert">
            <strong>Sorry!</strong> Please enter a valid street.
        </div>
    </c:if>

    <c:if test="${cityInvalid != null}">
        <div class="alert alert-danger" role="alert">
            <strong>Sorry!</strong> Please enter a valid city.
        </div>
    </c:if>

    <c:if test="${postcodeInvalid != null}">
        <div class="alert alert-danger" role="alert">
            <strong>Sorry!</strong> Please enter a valid postcode ie. LE18 6UG
        </div>
    </c:if>

    <c:if test="${imagesInvalid != null}">
        <div class="alert alert-danger" role="alert">
            <strong>Sorry!</strong> Please provide at least one property image.
        </div>
    </c:if>

    <c:if test="${ppmInvalid != null}">
        <div class="alert alert-danger" role="alert">
            <strong>Sorry!</strong> Please provide a price per month.
        </div>
    </c:if>

    <c:if test="${validFromInvalid != null}">
        <div class="alert alert-danger" role="alert">
            <strong>Sorry!</strong> Please provide a valid from date.
        </div>
    </c:if>

    <c:if test="${validToInvalid != null}">
        <div class="alert alert-danger" role="alert">
            <strong>Sorry!</strong> Please provide a valid to date.
        </div>
    </c:if>

    <form method="POST" enctype="multipart/form-data" type="" action="/property/addPost">
        <table>
            <tr>
                <td>Property Number:</td>
                <td><div class="form-group">
                    <input role="form" class="form-control" type="text" name="pNumber"
                           pattern="[0-9a-zA-Z]+" placeholder="Property Number" required="required" />
                </div></td>
            </tr>

            <tr>
                <td>Property Street:</td>
                <td><div class="form-group">
                    <input role="form" class="form-control" type="text" name="pStreet"
                           pattern="[a-zA-Z ]+" placeholder="Street Name" required="required" />
                </div></td>
            </tr>

            <tr>
                <td>Property City:</td>
                <td><div class="form-group"><div class="form-group">
                    <input role="form" class="form-control" type="text" name="pCity"
                           pattern="[A-Za-z ]+" placeholder="City" required="required" />
                </div></td>
            </tr>

            <tr>
                <td>Property PostCode:</td>
                <td><div class="form-group">
                    <input role="form" class="form-control" type="text" name="pPostcode"
                           pattern="[a-zA-Z0-9 ]+" placeholder="Postcode ie. LE18 6UG" required="required" />
                </div></td>
            </tr>

            <tr>
                <td>Property Type:</td>
                <td><div class="form-group">
                    <select name="pType" class="form-control" required="required">
                        <c:forEach items="${types}" var="type">
                            <option value="${type.id}">${type.type}</option>
                        </c:forEach>
                    </select>
                </div></td>
            </tr>

            <tr>
                <td>Number of Rooms:</td>
                <td><div class="form-group">
                    <select name="pRooms" class="form-control" required="required">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                    </select>
                </div></td>
            </tr>

            <tr>
                <td>Price Per Month:</td>
                <td><div class="form-group">
                    <select name="pPricePerMonth" class="form-control" required="required">
                        <c:forEach var="i" begin="1" end="9">
                            <c:forEach var="i2" begin="0" end="9">
                                <option value="${i}${i2}0">${i}${i2}0</option>
                            </c:forEach>
                        </c:forEach>
                        <option value="-1">Other</option>
                    </select>
                </div></td>
            </tr>

            <tr>
                <td>Is Rentable From:</td>
                <td><div class="form-group">
                    <input role="form" class="form-control" type="date" name="pValidFrom" required="required" />
                </div></td>
            </tr>

            <tr>
                <td>Is Rentable Until:</td>
                <td><div class="form-group">
                    <input role="form" class="form-control" type="date" name="pValidTo" required="required" />
                </div></td>
            </tr>

            <tr>
                <td>Property Images:</td>
                <td>
                    <div class="form-group">
                        <input type="file" name="images" multiple="multiple"
                               max="6" required="required" />
                    </div>
                </td>
            </tr>

            <tr>
                <td colspan="2"><input type="submit" value="Add Property"/></td>
            </tr>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </table>

    </form>
</div>
<hr />

<footer class="container">
    <p>&copy; CO2015 - Group 6</p>
</footer>


</body>
</html>