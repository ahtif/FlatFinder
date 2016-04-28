Feature: Interact with other users

 As a user
 In order to interact with other users,users must be able to view their Inbox
 So that they can exchange messages and send reports

@Domain
Scenario: Sending a message
 Given I am a Searcher "Bob"
 And a landlord "Ted"
 When "Bob" sends a message "Hello" to "Ted" with Subject "Important"
 Then "Ted" should receive the message "Hello" from "Bob"

@Domain
@NotImplemented
Scenario: Replying to a message
 Given I am a Searcher "Andy"
 And a searcher "Harry"
 And a message "viewing arrangements" from "Andy" with Subject "Arrangements" to "Harry"
 When I reply with "Time and Date?"
 Then the message "Time and Date?" should be stored in the database

@Domain
@NotImplemented
Scenario: Sending a email with no recipient
 Given I am a Searcher "Paul"
 When "Paul" sends a message "Hello" to "" with Subject "Bye"
 Then the message should not be sent

@Domain
@NotImplemented
Scenario: Expressing interest in a property
 Given I am a Searcher "Bob"
 And a landlord "Ted"
 And the user "Ted" is the owner of the property "Ted's House 7"
 When I like a property "Ted's House 7"
 Then user "Ted" receives a notification that user "Bob" liked his property

@Domain
@NotImplemented
Scenario: Broadcasting a message
 Given I am an administrator "James"
 And a searcher "Bobby"
 And a landlord "William"
 When I broadcast a message "Maintenance 4-7AM (GMT)" with subject "Maintenance"
 Then users "Bobby" and "William" should receive the message "Maintenance 4-7AM (GMT)"

@Domain
@NotImplemented
Scenario: Searcher providing feedback for landlord
 Given I am a Searcher "Bob"
 When I have dealt with a landlord "Ted"
 Then I should be able to leave feedback "Excellent property"
 And rate him "4/5" stars

@Domain
@NotImplemented
Scenario: Expressing interest
 Given I am a Searcher "Bob"
 And I am logged in
 When I view a property
 Then I should be able to express interest

@Domain
@NotImplemented
Scenario Outline: Expressed interest notification
 Given I am a Landlord "Ted"
 When a searcher <SearcherName> expresses interest
 Then I should be notified that <SearcherName> has expressed interest

Examples:
	|SearcherName|
	|Bob         |
	|Sarah       |
	|Jacob       |
	|Kim         |

@Domain
@NotImplemented
Scenario: Reporting a landlord as a searcher
 Given I am a Searcher "Bob"
 And a landlord "Ted"
 And "Ted" has a property "21 Fleet Street" with price "1" PCM
 When "Bob" reports "Ted" for inaccurate details
 Then Administrator "James" should be notified

@Domain
@NotImplemented
Scenario: Reporting a searcher as a landlord
 Given a Searcher "Bob"
 And a landlord "Ted"
 And "Bob" has verbally abused me through the messaging system
 When "Ted" reports "Bob" for abuse
 Then Administrator "James" should be notified
