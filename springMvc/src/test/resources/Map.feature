Feature: Map

 In order to provide accurate information to Searcher the system must position and locate my properties on the map
 As a Landlord
 So that the Searcher get a visual understanding of where a property is located

@Domain
@NotImplemented
Scenario: Generating longitude and latitude of a property
 Given I am a Landlord "John"
 And I have entered the address "60 Lancaster Road"
 And I have entered the postcode "LE1 7HA"
 When I submit the form
 Then the property should be listed on the system
 And with address "60 Lancaster Road"
 And with the postcode "LE1 7HA"
 And should generate the latitude "52.6257172"
 And longitude "1.12760520"

@Controller 
@NotImplemented
Scenario: Adding property to Map
 Given I am a landlord "Ted"
 And I am located on the "addproperty" page
 And I have entered the address "13 Aylestone Rd"
 And I have entered the postcode "LE2 7LG"
 And I have entered the no. bedrooms "3"
 And I have entered the price "700"
 When I submit the form
 Then the property will added to "Ted" property listing
 And should be added to the map
   
@Domain
@NotImplemented 
Scenario Outline: Searching for properties
 Given I am a Searcher "Majid"
 And a Landlord "Ted"
 And a "Ted" adds property <ADDRESS> with postcode <POSTCODE>
 And bedroom <BEDROOMS>  
 And price <PRICE> to the website
 When "Majid" searcher for properties in "Leicester"
 Then "13 Aylestone Rd","171 London Rd","300 Welford Rd" should be displayed on the map
	
	Examples:
	  |ADDRESS          |POSTCODE  |BEDROOMS  |PRICE|
      |"13 Aylestone Rd"|"LE2 7LG" |   "3"    |"700"|
      |"171 London Rd"  |"LE2 3BE" |   "5"    |"950"|
      |"300 Welford Rd" |"LE2 6EG" |   "5"    |"950"|
	  |"10 Fleet Street"|"HP202AJ" |   "7"    |"999"|