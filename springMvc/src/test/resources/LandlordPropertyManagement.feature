Feature: Add and update property
	In order to advertise my property
	As a landlord I would like to add it to the system and keep it updated
	So relevant searchers can view my property

@controller
Scenario Outline: accessing the add property form
	Given I am logged in
	When I request the add property form
	Then I should have access to the form

@controller
Scenario Outline: validate post code
	Given I am on the 'Add property' page
	And I have entered a postcode "90001"
	When I submit the form
	Then I should get an error message "Incorrect postcode format"

@controller
Scenario Outline: validate address
	Given I have entered an address "123456789"
	When I submit the form
	Then I should get an error message "Please enter a valid address format"

@controller
Scenario Outline: No. Bedrooms validation
	Given I have entered the number of bedrooms "fifty"
	When I submit the form
	Then I should get an error message "Please enter 1 to 28 bedrooms in numerical digits"

@controller
Scenario Outline: No. Bathrooms validation
	Given I have entered the number of bedrooms "six"
	When I submit the form
	Then I should get an error message "Please enter 1 to 28 bathrooms in numerical digits"

@controller
Scenario Outline: Requesting 'add property' page for landlords
	Given I am a landlord
	And I am logged in
 	When I request the Searcher registration form
    Then the system shows the Searcher registration page

@domain
Scenario Outline: Entering price listing
	Given I am on the add property page
	When I enter the price "750"
	Then the price of my property should be 750

@controller
Scenario Outline: Re-entering an already listed property
 	Given I have entered the address "123 Example Street"
	And I have entered the postcode "SW1A 2AA"
	And there is already a record of the "123 Example Street"
	And there is already a record of the postcode "SW1A 2AA"
  	When I submit the form
   	Then an error message should occur: "property already listed - can not relist the same property"

@domain
Scenario Outline: Submitting property details
	Given I am a landlord user
	And I have entered the address "10 Downing Street"
	And the postcode "SW1A 2AA"
	And the no. bedrooms "5"
	And the no. bathrooms "2"
	And the price "750"
	When I submit the form
	Then the property should be listed to the system
	And the address "10 Downing Street"
	And the postcode "SW1A 2AA"
	And the no. bedrooms "5"
	And the no. bathrooms "2"
	And the price "750"

@domain
Scenario Outline: Updating property
	Given I have a listed property
	And the cost (PCM) is "750"
	When I update the property
	And I change the price to "800"
	And I submit the new details
	Then the new price should be set as "800" on the database
