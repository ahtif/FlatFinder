Feature: Admin management
	In order to manage the system's userbase
	As an administrator I want to oversee communications and receive reports
	So that I can deal with them accordingly by suspending, expiring and deleting users and posts

@domain
Scenario Outline: delete user
	Given I am an administrator
	When a user "bob" is reported for malicious behaviour
	Then I should be able to delete the user account "bob"

@domain
Scenario Outline: temporarily suspend user
	Given I am an administrator
	When a user "bob" is reported for malicious behaviour
	Then I should be able to suspend them for 30 days

@controller
Scenario Outline: viewing reports from users
	Given I am an administrator
	When a user makes a report
	Then I should be able to view it

@controller
Scenario Outline: searching for inactive users
	Given I am an administrator
	When I search for inactive users
	Then only users that have been inactive for more than 365 days should show

@controller
Scenario Outline: Search for inactive users
	Given I am an administrator
	When a user has been inactive for 365 or more
	Then I should be able to expire their account removing them from search results

@controller
Scenario Outline: View confidential statistics
	Given I am an administrator "James"
	When I request the total number of users of the website
	Then I should be able to view them
	And only administrators should be able to view them

@domain
Scenario Outline: Taking down listings
	Given I am an administrator "James"
	When I receive a false listing report by a landlord "Ted"
	And I review it
	Then I should be able to suspend them
	And I should be able to take down a listing
