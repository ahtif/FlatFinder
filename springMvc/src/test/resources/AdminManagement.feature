#NB: URLS NEED TO BE CHANGED BASED ON IMPLEMENTATION
Feature: Administrator management

 In order to manage the systems userbase
 As an administrator I want to oversee communications and receive reports
 So that I can deal with them accordingly by suspending, expiring and deleting users and posts

@Domain

Scenario: Deleting a user
 Given a user "Bob"
 And "Bob" is reported for malicious behaviour
 When I delete the user account "Bob"
 Then user "Bob" is removed from the system

@Controller

Scenario: Deleting a user using controller
 Given a user "Bob"
 And "Bob" is reported for malicious behaviour
 When I located the user "Bob" on the delete user page
 Then I should be redirect to "/admin/viewUsers"

@Domain
@NotImplemented
Scenario: Temporarily suspend user
 Given a user "Bob"
 And "Bob" is reported for malicious behaviour
 When I suspend "Bob"
 Then "Bob" is suspended
 And account status is changed to "suspended"


@Domain
@NotImplemented
Scenario: Searching for inactive users
 Given a user "Bob" who is active for 365 days
 When I search for inactive users
 Then "Bob" should be displayed in the list of inactive users

@Domain
@NotImplemented
Scenario: Suspending inactive users
 Given a user "Bob" who is inactive for 365 days
 When I suspend the user "Bob"
 Then the user account status is "suspend"

@Controller
@NotImplemented
Scenario: Search for inactive users
 Given a user "Bob" who is inactive for 365 days
 When I located the user "Bob" on the inactive user page
 And click "suspend account"
 Then I should be redirect to "/inactive-users"

@Controller
@NotImplemented
Scenario: Viewing confidential statistics
 Given I am an administrator "James"
 When I request the total number of users of the website
 Then I should be able to view them
 And only administrators should be able to view them

@Domain
@NotImplemented
Scenario: Taking down listings
 Given I am an administrator "James"
 And a listing with number "300" and road "Welford Road" by landlord "Ted"
 And I receive a false listing report
 When I remove the listing "300 Welford Road"
 And I suspend landlord "Ted"
 Then the property "300 Welford Road" should be removed
 And "Ted" account status should be suspend

@Controller

Scenario: Taking down listing which is removed by an admin
 Given I am an admin "James"
 And a listing with number "300" and road "Welford Road" by landlord "Ted"
 When "James" remove the property number "300" and street "Welford Road"
 Then the property should be removed
