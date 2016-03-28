#NB: URLS NEED TO BE CHANGED BASED ON IMPLEMENTATION
Feature: Administrator management

 In order to manage the systems userbase
 As an administrator I want to oversee communications and receive reports
 So that I can deal with them accordingly by suspending, expiring and deleting users and posts

# @Domain
# @NotImplemented
# Scenario: Deleting a user
#  Given a user "Bob"
#  And "Bob" is reported for malicious behaviour
#  When I delete the user account "Bob"
#  Then user "Bob" is removed from the system
#
# @Controller
# @NotImplemented
# Scenario: Deleting a user using controller
#  Given a user "Bob"
#  And "Bob" is reported for malicious behaviour
#  When I located the user "Bob" on the delete user page
#  And click "delete account"
#  Then I should be redirect to "/delete-users"

# @Domain
# @NotImplemented
# Scenario: Temporarily suspend user
#  Given a user "Bob"
#  And "Bob" is reported for malicious behaviour
#  When I suspend "Bob" for 30 days
#  Then "Bob" is suspended for 30 days
#  And account status is changed to "suspended"

# @Controller
# @NotImplemented
# Scenario: Temporarily suspend user using controller
#  Given a user "Bob"
#  And "Bob" is reported for malicious behaviour
#  When I located the user "Bob" on the suspend user page
#  And enter 30 days
#  And click "suspend account"
#  Then I should be redirect to "/suspend-users"
#
# @Controller
# @NotImplemented
# Scenario: Suspending a user that is already temporarily suspended
#  Given a user "Bob"
#  And "Bob" is already suspended for 30 days
#  And "Bob" is reported for malicious behaviour
#  When I located the user "Bob" on the suspend user page
#  Then I should be able to delete the user "Bob"
#  And should be redirect to "/suspend-users"

# @Controller
# @NotImplemented
# Scenario: Viewing reports from users
#  Given a user "Bob"
#  When a searcher "Bob" makes a report about a user "Ted"
#  Then I should be notified
#  And I should be able to view their report
#
# @Domain
# @NotImplemented
# Scenario: Searching for inactive users
#  Given a user "Bob" who is active for 365 days
#  When I search for inactive users
#  Then "Bob" should be displayed in the list of inactive users
#
# @Domain
# @NotImplemented
# Scenario: Suspending inactive users
#  Given a user "Bob" who is inactive for 365 days
#  When I suspend the user "Bob"
#  Then the user account status is "suspend"
#
# @Controller
# @NotImplemented
# Scenario: Search for inactive users
#  Given a user "Bob" who is inactive for 365 days
#  When I located the user "Bob" on the inactive user page
#  And click "suspend account"
#  Then I should be redirect to "/inactive-users"
#
# @Controller
# @NotImplemented
# Scenario: Viewing confidential statistics
#  Given I am an administrator "James"
#  When I request the total number of users of the website
#  Then I should be able to view them
#  And only administrators should be able to view them
#
# @Domain
# @NotImplemented
# Scenario: Taking down listings
#  Given I am an administrator "James"
#  And a listing "300 Welford Road" by a Landlord "Ted"
#  And I receive a false listing report
#  When I remove the listing "300 Welford Road"
#  And I suspend landlord "Ted"
#  Then the property "300 Welford Road" should be removed
#  And "Ted" account status should be suspend
#
# @Controller
# @NotImplemented
# Scenario: Taking down listing which is removed by landlord
#  Given I am an administrator "James"
#  And a listing "300 Welford Road" by a Landlord "Ted"
#  And I receive a false listing report
#  When "Ted" remove the property "300 Welford Road"
#  Then the report should be closed
