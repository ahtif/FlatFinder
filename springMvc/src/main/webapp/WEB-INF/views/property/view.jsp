<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" lang="en" http-equiv="Content-Type" content="text/html" />
    <title>View Property</title>
    
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
          <a class="navbar-brand" href="#">Flat Finder- Landlord View</a>
        </div>
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
      </div>
    </nav>
    <div class="container">

<br /><br /><br />

        <div class="container" style="margin-left: -15px;">
            <br>
            <div id="myCarousel" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                </ol>

                <!-- Wrapper for slides - !!!ensure image width is maximised!!! -->
                <div class="carousel-inner" role="listbox">

                    <c:forEach var="listValue" items="${images}" varStatus="status">

                        <div class="item${status.first ? ' active' : ''}"> <!-- Ensure the image width fills the carousel container -->
                            <img src="<c:url value="/resources/images/properties/${listValue}" />" alt="" width="9999" height="640" />
                        </div>
                    </c:forEach>
                </div>

                <!-- Left and right controls -->
                <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>

    <div class="jumbotron">
        <h2>Property details</h2>
        <p>Property Number: ${property.number}</p>
        <p>Property Street: ${property.street}</p>
        <p>Property City: ${property.city}</p>
        <p>Property PostCode: ${property.postcode}</p>
        <p>Property Type: ${property.type.type}</p>
        <p>Property Rooms: ${property.rooms}</p>
      </div>

<p>To go back to view all of your properties, click <a href="/property/viewAll">here</a>.</p>
</div>
<hr>

      <footer>
        <p>&copy; CO2015- Group 6</p>
      </footer>


</body>
</html>