Feature: Interact with other users
	In order to interact with other users users must be able to view their inbox
	So that they can exchange messages and send reports

@domain
Scenario: Sending a message
	Given I am logged in as a searcher "bob"
	When I send a message "Hello" to a landlord "ted"
	Then they should receive the message "Hello"

@domain
Scenario: replying to a message
	Given I am a logged in as a searcher "bob"
	And I am on my inbox page
	When I receive a message "viewing arrangements" from a user "ted"
	Then I should be able to reply with "time and date?" from my inbox

@domain
Scenario: Expressing interest in a property
	Given I am logged in as a searcher "bob"
	And the user "ted" is a landlord
	And the user "ted" is the owner of the property
	When I like a property "Ted's House 7"
	Then user "ted" receives a notification that user "bob" liked his property

@domain
Scenario: Broadcasting a message
	Given I am logged in as an administator "James"
	When I broadcast a message "Maintenance 4-7AM (GMT)"
	Then users "Bob" and "Ted" should receive the message "Maintenance 4-7AM (GMT)"

@domain
Scenario: Searcher providing feedback for landlord
	Given I am a registered searcher "bob"
	When I have dealt with a landlord "Ted"
	Then I should be able to leave feedback: "Excellent property"
	And rate him 4/5 stars

@domain
Scenario Outline: expressing interest
	Given I am a registered user "bob"
	And I am logged in
	And I am a searcher
	When I view a property
	Then I should be able to express interest

@domain
Scenario Outline: expressed interest notification
	Given I am a landlord "Ted"
	And I am logged in
	When a searcher <SearcherName> expresses interest
	Then I should be notified

	Examples:
		|SearcherName|
		|Bob         |
		|Sarah       |
		|Jacob       |
		|Kim         |

@domain
Scenario: Reporting a landlord as a searcher
	Given I am a searcher "Bob"
	When a landlord "Ted" makes an inaccurate property listing
	Then I should be able to report them
	And the administrator "James" should be notified

@domain
Scenario Outline: Reporting a searcher as a landlord
	Given I am a landlord "Ted"
	When a searcher "Bob" verbally abuses me over the messaging system
	Then I should be able to report searcher "bob"