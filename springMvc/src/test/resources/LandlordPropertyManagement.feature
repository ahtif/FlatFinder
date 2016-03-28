#NB: ALL URL NEED TO BE CHANGED BASED ON IMPLEMENTATION
Feature: Add and update property

 In order to advertise my property
 As a landlord I would like to add it to the system and keep it updated
 So relevant searchers can view my property

@Controller
@NotImplemented
Scenario: Accessing the add property page
 Given I am logged in as a landlord "Ted"
 When I request the add property page "/add-property"
 Then I should have access to the page "add-property"

@Controller
@NotImplemented
Scenario: Validate post code
 Given I am logged in as a landlord "Ted"
 And I am on the "Add property" page
 And I have entered a postcode "90001"
 When I submit the form
 Then I should get an error message "Incorrect postcode format"
 And the details should not be stored in the database

@Controller
@NotImplemented
Scenario: Validate address
 Given I am logged in as a landlord "Ted"
 And I have entered an address "123456789"
 When I submit the form
 Then I should get an error message "Please enter a valid address format"
 And the details should not be stored in the database

@Controller
@NotImplemented
Scenario: No. Bedrooms validation
 Give I am logged in as a landlord "Ted"
 And I have entered the number of bedrooms "fifty"
 When I submit the form
 Then I should get an error message "Please enter 1 to 28 bedrooms in numerical digits"
 And the details should not be stored in the database

@Domain
@NotImplemented
Scenario: Entering price listing
 Given I am on the add property page
 When I enter the price "750"
 Then the price of my property should be "750"

@Controller
@NotImplemented
Scenario: Re-entering an already listed property
 Given I have entered the address "123 Example Street"
 And I have entered the postcode "SW1A 2AA"
 And there is already a record of the "123 Example Street" with postcode "SW1A 2AA"
 When I submit the form
 Then an error message should occur "property already listed - can not relist the same property"
 And the details should not be stored in the database

# # Cause build to fail !
#
# # @Domain
# # @NotImplemented
# # Scenario: Upload image of property
# #  Given I am a landlord "James"
# #  And I am on the update property page
# #  When I select property <PROPERTYNAME> to update
# #  Then I should be able to upload <IMAGE> to give a further description of my property
# #
# #      Examples:
# #      | PROPERTYNAME        | IMAGE    |
# #      | "7 FleetStreet"     | "img1"   |
# #      | "32 Amber Park"     | "img2"   |
# #      | "2 Helloworld Lane" | "img3"   |
# #
#
# @Domain
# @NotImplemented
# Scenario: Submitting property details
#  Given I am a landlord "Ted"
#  And I have entered the address <ADDRESS>
#  And I have entered the postcode <POSTCODE>
#  And I have entered the no. bedrooms <BEDROOMS>
#  And I have entered the price <PRICE>
#  And I have entered the size <SIZE> sq. metres
#  And I have entered the start date <STDATE>
#  And I have entered the end date <ENDATE>
#  And I have entered the local information <LOCALINFO>
#  And I have attached the image <IMAGE>
#  When I submit the form
#  Then the property should be listed on the system
#  And with the postcode <POSTCODE>
#  And with the no. bedrooms <BEDROOMS>
#  And with the price <PRICE>
#  And with the size <SIZE> sq. metres
#  And with the start date <STDATE>
#  And with the end date <ENDATE>
#  And with the local information <LOCALINFO>
#
#  	Examples:
#      |ADDRESS          |POSTCODE  |BEDROOMS  |PRICE|SIZE  |STDATE      |ENDATE      |LOCALINFO                     |IMAGE          |
#      |"13 Aylestone Rd"|"LE2 7LG" |   "3"    |"700"|"78"  |"01/03/2016"|"01/06/2016"|"Urban Area-Victorian Housing"|"AyleStone.png"|
#      |"171 London Rd"  |"LE2 3BE" |   "5"    |"950"|"105" |"01/04/2016"|"01/09/2016"|"Urban Area-City-Centre"      |"LndRd.png"    |
#      |"300 Welford Rd" |"LE2 6EG" |   "5"    |"950"|"110" |"16/04/2016"|"31/12/2016"|"Urban Area"                  |"Welford.png"  |

@Domain
@NotImplemented
Scenario: Updating property
 Given a property "123 Example Street" with postcode "SW1A 2AA"
 And the PCM cost is "750"
 When I change the price to "800"
 Then the new price should be set as "800" on the database

@Controller
@NotImplemented
Scenario: Updating property with invalid data
 Given a property "123 Example Street" with postcode "SW1A 2AA"
 And the PCM cost is "750"
 And I have located the property "123 Example Street" on the "update-property" page
 When I change the price to "foo"
 Then I should receive a error message saying "prices need to be numerical values"

@Domain
@NotImplemented
Scenario: Removing a property
 Given a property "123 Example Street" with postcode "SW1A 2AA"
 And prices "300" pcm
 When I remove the property "123 Example Street"
 Then the property "123 Example Street" should be removed from the database.

@Controller
@NotImplemented
Scenario: Removing a property
 Given a property "123 Example Street" with postcode "SW1A 2AA"
 And prices "300" pcm
 When located the property "123 Example Street" on the "manage-property" page
 And click "remove"
 Then I should redirected to "/remove-property"
