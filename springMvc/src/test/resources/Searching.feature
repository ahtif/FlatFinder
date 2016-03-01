Feature: Searching for property
	In order to find property
	As a searcher I would like to filter by search criterion
	So that I can find property suited to me

@controller
Scenario: Search by location
	Given I am logged in as searcher "bob"
	When I enter a postcode "LE2 3AD"
	And I specify a distance of "2" mile radius
	Then Only properties within 2 miles should show

@controller
Scenario: Search by listing price
	Given I am logged in as searcher "Bob"
	When I filter by the price "750" or lower (PCM)
	Then I should only be able to see properties that cost 750 or less

@controller
Scenario: Search by bedroom
	Given I am logged in as a searcher "Bob"
	When I filter by "4" bedrooms
	Then only property with 4 Bedrooms should be displayed

@controller
Scenario: Filter by multple criteria
	Given I am logged in as searcher "Bob"
	When I filter by "3" bedrooms
	And I filter by postcode "LE1 7RH"
	And I filter by radius (miles) "2"
	Then only properties within 2 miles of "LE1 7RH" should show

@controller
Scenario: Search by start date
	Given I am logged in as a searcher "Bob"
	When I filter listings by start date "01/03/2016/"
	And I filter by end date "01/04/2016"
	Then I should be able to only see properties available from "01/03/2016" to "01/04/2016"
	
