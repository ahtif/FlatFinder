Feature: Interact with other users
	In order to interact with other users users must be able to view their inbox
	So that they can exchange messages and send reports

@domain
Scenario Outline: Sending a message
	Given I am a registered user "bob"
	When I send a message "Hello" to "ted" and "ted" is a registered user
	Then they should receive the message "Hello"

@domain
Scenario Outline: replying to a message
	Given I am a registered user "bob"
	When I receive a message "viewing arrangements" from a user "ted"
	Then I should be able to reply

@domain
Scenario Outline: Expressing interest in a property
	Given I am a registered user "bob"
	When I like a property "Ted's House 7"
	And the user "ted" is a landlord
	And the user "ted" is the owner of the property
	Then user "ted" should receive a notification that user "bob" liked his property

@domain
Scenario Outline: Broadcasting a message
	Given I am a registered user "James"
	And I am registered as an administrator
	When I broadcast a message "Maintenance 4-7AM (GMT)"
	Then all registered users should receive the message "Maintenance 4-7AM (GMT)"

@domain
Scenario Outline: Searcher providing feedback for landlord
	Given I am a registered searcher "bob"
	When I have dealt with a landlord "Ted"
	Then I should be able to leave feedback: "Excellent property"

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
		|Bob|
		|Sarah|
		|Jacob|
		|Kim|

@domain
Scenario Outline: Reporting a landlord as a searcher
	Given I am a searcher "Bob"
	When a landlord "Ted" makes an inaccurate property listing
	Then I should be able to report them
	And the administrator should be notified

@domain
Scenario Outline: Reporting a searcher as a landlord
	Given I am a landlord "Ted"
	When a searcher "Bob" verbally abuses me over the messaging system
	Then I should be able to report searcher "bob"
