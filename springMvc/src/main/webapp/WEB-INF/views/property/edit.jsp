<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" lang="en" http-equiv="Content-Type" content="text/html" />
    <title>Flat Finder - Edit Property</title>

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
            <a class="navbar-brand" href="#">Flat Finder - Edit Property</a>
        </div>
        <sec:authorize access="hasRole('ADMINISTRATOR')">
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/success-login">Home</a></li>
                    <li><a href="/messaging/inbox">Inbox</a></li>
                    <li><a href="/admin/viewProperties">Manage Properties</a></li>
                </ul>
                <c:url value="/logout" var="logoutUrl"/>
                <form class="navbar-form navbar-right" action="${logoutUrl}" method="get">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <button class="btn btn-success" type="submit">Log Out</button>
                </form>
            </div><!--/.nav-collapse -->
        </sec:authorize>
        <sec:authorize access="hasRole('LANDLORD')">
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/success-login">Home</a></li>
                    <li><a href="/profile">Profile</a></li>
                    <li><a href="/messaging">Inbox</a></li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Properties <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/property/add">Add a Property</a></li>
                            <li><a href="/property/viewAll">View all properties</a></li>
                        </ul>
                    </li>
                </ul>
                <c:url value="/logout" var="logoutUrl"/>
                <form class="navbar-form navbar-right" action="${logoutUrl}" method="get">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <button class="btn btn-success" type="submit">Log Out</button>
                </form>
            </div><!--/.nav-collapse -->
        </sec:authorize>
    </div>
</nav>
<div class="container">

    <div class="jumbotron">
        <br />
        <h1>Edit Property</h1>
        <p>Edit the property using the form below.</p>
    </div>

    <c:choose>

    <c:when test="${notFound != null}">

        <p>Property could not be found.</p>

    </c:when>

    <c:when test="${invalid != null}">

        <p>You cannot edit this resource.</p>

    </c:when>

    <c:otherwise>

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

    <form method="POST" enctype="multipart/form-data" type="" action="/property/addPost?edit=${property.getId()}">
        <table>
            <tr>
                <td>Property Number:</td>
                <td><div class="form-group">
                    <input role="form" class="form-control" type="text" name="pNumber" value="${property.getNumber()}"
                           pattern="[0-9a-zA-Z]+" placeholder="Property Number" required="required" />
                </div></td>
            </tr>

            <tr>
                <td>Property Street:</td>
                <td><div class="form-group">
                    <input role="form" class="form-control" type="text" name="pStreet" value="${property.getStreet()}"
                           pattern="[a-zA-Z ]+" placeholder="Street Name" required="required" />
                </div></td>
            </tr>

            <tr>
                <td>Property City:</td>
                <td><div class="form-group"><div class="form-group">
                    <input role="form" class="form-control" type="text" name="pCity" value="${property.getCity()}"
                           pattern="[A-Za-z ]+" placeholder="City"  />
                </div></td>
            </tr>

            <tr>
                <td>Property PostCode:</td>
                <td><div class="form-group">
                    <input role="form" class="form-control" type="text" name="pPostcode" value="${property.getPostcode()}"
                           pattern="[a-zA-Z0-9 ]+" placeholder="Postcode ie. LE18 6UG" required="required" />
                </div></td>
            </tr>

            <tr>
                <td>Property Type:</td>
                <td><div class="form-group">
                    <select name="pType" class="form-control" required="required">
                        <c:forEach items="${types}" var="type">
                            <c:choose>
                                <c:when test="${type.type == property.getType().getType()}">
                                    <option value="${type.id}" selected="selected">${type.type}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${type.id}">${type.type}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div></td>
            </tr>

            <tr>
                <td>Number of Rooms:</td>
                <td><div class="form-group">
                    <select name="pRooms" class="form-control" required="required">
                        <c:choose>
                            <c:when test="${1 == property.getRooms()}">
                                <option value="1" selected="selected">1</option>
                            </c:when>
                            <c:otherwise>
                                <option value="1">1</option>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${2 == property.getRooms()}">
                                <option value="2" selected="selected">2</option>
                            </c:when>
                            <c:otherwise>
                                <option value="2">2</option>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${3 == property.getRooms()}">
                                <option value="3" selected="selected">3</option>
                            </c:when>
                            <c:otherwise>
                                <option value="3">3</option>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${4 == property.getRooms()}">
                                <option value="4" selected="selected">4</option>
                            </c:when>
                            <c:otherwise>
                                <option value="4">4</option>
                            </c:otherwise>
                        </c:choose>
                    </select>
                </div></td>
            </tr>

            <tr>
                <div class="alert alert-danger" role="alert">
                    Please upload all required images of property.
                </div>
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

</c:otherwise>

</c:choose>

<hr />

<footer class="container">
    <p>&copy; CO2015 - Group 6</p>
</footer>


</body>
</html>