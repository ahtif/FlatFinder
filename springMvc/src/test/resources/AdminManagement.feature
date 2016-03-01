Feature: Admin management
	In order to manage the system's userbase
	As an administrator I want to oversee communications and receive reports
	So that I can deal with them accordingly by suspending, expiring and deleting users and posts

@domain
Scenario: Delete user
	Given I am logged in as an administrator "james"
	When a user "bob" is reported for malicious behaviour
	Then I should be able to delete the user account "bob"
	And user "bob" is removed from the system

@controller
Scenario: Viewing reports from users
	Given I am logged in as an administrator "james"
	When a searcher "bob" makes a report about a user "ted"
	Then I should be notified
	And I should be able to view their report

@domain
Scenario: temporarily suspend user
	Given I am logged in as an administrator "james"
	When a user "bob" is reported for malicious behaviour
	Then I should be able to suspend them for 30 days
	And user "bob" cannot access the system for 30 days

@controller
Scenario: Searching for inactive users
	Given I am logged in as an administrator "james"
	When I search for inactive users
	Then only users that have been inactive for more than 365 days should show

@controller
Scenario: Search for inactive users
	Given I am logged in as an administrator "james"
	When a searcher "bob" has been inactive for 365 or more
	Then I should be able to expire their account removing them from search results

@controller
Scenario: View confidential statistics
	Given I am an administrator "James"
	When I request the total number of users of the website
	Then I should be able to view them
	And only administrators should be able to view them

@domain
Scenario: Taking down listings
	Given I am an administrator "James"
	When I receive a false listing report of "300 Welford Road" by a landlord "Ted"
	And I review it
	Then I should be able remove the listing "300 Welford Road"
	And I should be able to suspend landlord"Ted"