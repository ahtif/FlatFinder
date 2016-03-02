Feature: Add and update property
	In order to advertise my property
	As a landlord I would like to add it to the system and keep it updated
	So relevant searchers can view my property

@controller
Scenario: accessing the add property form
	Given I am logged in as a landlord "Ted"
	When I request the add property form
	Then I should have access to the form

@controller
Scenario: validate post code
	Given I am logged in as a landlord "Ted"
	And I am on the 'Add property' page
	And I have entered a postcode "90001"
	When I submit the form
	Then I should get an error message "Incorrect postcode format"
	And the details should not be stored in the database

@controller
Scenario: validate address
	Given I am logged in as a landlord "Ted"
	And I have entered an address "123456789"
	When I submit the form
	Then I should get an error message "Please enter a valid address format"
	And the details should not be stored in the database

@controller
Scenario: No. Bedrooms validation
	Give I am logged in as a landlord "Ted"
	And I have entered the number of bedrooms "fifty"
	When I submit the form
	Then I should get an error message "Please enter 1 to 28 bedrooms in numerical digits"
	And the details should not be stored in the database

@controller
Scenario: No. Bathrooms validation
	Given I am logged in as a landlord "Ted" 
	And I have entered the number of bedrooms "six"
	When I submit the form
	Then I should get an error message "Please enter 1 to 28 bathrooms in numerical digits"
	And the details should not be stored in the database


@domain
Scenario: Entering price listing
	Given I am on the add property page
	When I enter the price "750"
	Then the price of my property should be 750

@controller
Scenario: Re-entering an already listed property
 	Given I have entered the address "123 Example Street"
	And I have entered the postcode "SW1A 2AA"
	And there is already a record of the "123 Example Street"
	And there is already a record of the postcode "SW1A 2AA"
  	When I submit the form
   	Then an error message should occur: "property already listed - can not relist the same property"
	And the details should not be stored in the database

@domain
Scenario Outline: Upload image of property
	Given I am a landlord "James"
	And I am on the update property page
	When I select property <PROPERTY> to update
	Then I should be able to upload <PropertyImage> to give a further description of my property

	Examples:
	 |PROPERTY	 |PropertyImage|
	 |13 Aylestone Rd|img01.png    |
	 |171 London Rd  |img02.png    |
	 |300 Welford Rd |img03.png    |

@domain
Scenario Outline: Submitting propertydetails
	Given I am a landlord "Ted"
	And I have entered the address <ADDRESS>
	And I have entered the postcode <POSTCODE>
	And I have entered the no. bedrooms <BEDROOMS>
	And I have entered the price <PRICE>
	And I have entered the size <SIZE> sq. metres
	And I have entered the start date <STDATE>
	And I have entered the end date <ENDATE
	And I have entered the local information <LOCALINFO>
	And I have attached the image <IMAGE>
	When I submit the form
	Then the property should be listed on the system
	And with the postcode <POSTCODE>
	And with the no. bedrooms <BEDROOMS>
	And with the price <PRICE>
	And with the size <SIZE> sq. metres
	And with the start date <STDATE>
	And with the end date <ENDATE
	And with the local information <LOCALINFO>

	Examples:
	 |PROPERTY	 |POSTCODE|BEDROOMS|PRICE|SIZE|STDATE    |ENDATE    |LOCALINFO                    |IMAGE        |
	 |13 Aylestone Rd|LE2 7LG |   3    |700  |78  |01/03/2016|01/06/2016|Urban Area- Victorian Housing|AyleStone.png|
	 |171 London Rd  |LE2 3BE |   5    |950  |105 |01/04/2016|01/09/2016|Urban Area- City Centre      |LndRd.png    |
	 |300 Welford Rd |LE2 6EG |   5    |950  | 110|16/04/2016|31/12/2016|Urban Area                   |Welford.png  | 

@domain
Scenario Outline: Updating property
	Given I have a listed property
	And the cost (PCM) is "750"
	When I change the price to "800"
	Then the new price should be set as "800" on the database
