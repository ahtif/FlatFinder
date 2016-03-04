Feature: Viewing and updating profile
 As a searcher or a landlord
 I want have a profile and be able to view other user's profiles
 So that I may find a suitable 'buddy-up' partner or tenant

@domain
Scenario: Changing preferences
	Given the user "bob"
	And I am opted in for the buddy-up system
	And my preference is set to "Smoking"
	And my preference is set to "Undergraduate"
	And my preference is set to "Lively Atmosphere"
	When I update my profile and set my preference to "Non-smoking"
	Then my profile should show my preferences as "Non-smoking"
	And "Post-graduate"
	And "Quiet"

@controller
Scenario Outline: Viewing property views
	Given I am logged in as a landlord "James"
	When I access my profile page
	Then I should be able to view my property views for <PROPERTY>

	Examples:
	 |PROPERTY	 |
	 |13 Aylestone Rd|
	 |171 London Rd  |
	 |300 Welford Rd |

@domain
Scenario Outline: Tracking property views
	Given I am logged in as a landlord "James"
	And I own properties <PROPERTIES>
	And I have a 999 property views on each property
	When a searcher "Bob" views my property "13 Aylestone Rd"
	Then my property views for "13 Aylestone Rd" should be "1000"
	And my property views for "300 Welford Rd" should be "999"
	And my property views for "171 London Rd" should be "999"
	
	Examples:
	 |PROPERTIES	 |
	 |13 Aylestone Rd|
	 |171 London Rd  |
	 |300 Welford Rd |

@domain
Scenario: Tracking recent property views
	Given I am logged in as a searcher "Bob" 
	And I have been recently viewed two properties, "Aylestone Road, LE2 7LG" and "Welford Road, LE2 6EG"
	When I access my profile page
	Then I should be able to see "Aylestone Road, LE2 7LG" and "Welford Road, LE2 6EG" on my recently viewed properties
