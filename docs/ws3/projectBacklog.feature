Feature: Registration
As a user, I want to be able to register as either a Landlord or a Searcher so that I can rent my property or find a property.

Scenario: Register as a Searcher
Given I am a user
When I register as a Searcher
Then I should be registered as a Searcher

Scenario: Register as a Searcher and become a buddy
Given I am a user
When I register as a Searcher
And I choose to be a buddy
Then I should be registered as a Searcher
And I should be subscribed to the buddy system

Scenario: Register as a Landlord
Given I am a user
When I register as a Landlord
Then I should be registered as a Landlord

Feature: Login
As a registered user, I should be able to login to the website as a Landlord or Searcher so that I can have access to my account and use the services.

Scenario: Login as Searcher
Given I have registered as a Searcher
When I login with my username and password
Then I should be able to access the services for Searchers
	
Scenario: Login as Landlord
Given I have registered as a Landlord
When I login with my username and password
Then I should be able to access the services for Landlords

Scenario: Login as an Administrator
Given I have registered as a Administrator
When I login with my username and password
Then I should be able to access the services for Administrators

Feature: Account recovery 
As a registered user, I should be able to reset my password for my account as either a Landlord or Searcher so that I can regain access to my account.

Scenario: Recovering an account
Given I am a user
When I indicate that I have forgotten my account credentials
And have entered my email address associated to the account
Then I should receive an account recovery email which could contain a unique link for me to reset my password

Scenario: Resetting an user account password 
Given I have been sent a unique password reset link 
And the link hasn't been used before 
And wasn't generated more than 24 hours ago
When I access the link 
And enter a password and confirmed password which match
Then my password will be changed to the new password.

Feature: Searching for properties
As a Searcher, I should be able to use the system to search for properties near me and based on my preferences 
So that I am to find the properties that match my preference. 

Scenario: Searching for properties by postcode and price
Given I am logged in as a Searcher
When I search for properties
And I adjust the postcode filter and price filter
Then I should see a map of properties filtered by the entered postcode and price

Scenario: Searching for properties by room type and tenants preferences
Given I am logged in as a Searcher
When I search for properties near me
And I select en suite rooms, non-smoking and undergraduates tenants and apply the filter
Then I should see a map of properties near me that match my filters

Scenario: Buddying up
Given I am logged in as a Searcher
And I am looking at a property
When I select the buddy up option
Then I should be matched to a buddy

Scenario: Registering interest
Given I am logged in as a Searcher
And I am looking at a property
When I click the express interest button
Then the landlord should be alerted that I am interested

Scenario: GPS tracking
Given I am logged in as a Searcher
When I want to find a property using the GPS system
Then the GPS should take me to the property

Feature: Managing Properties
As a Landlord I should be able to add, remove or update my properties so that I can manage my properties.

Scenario: Adding a property
Given I am logged in as a Landlord
When I add a new property 
And I provide all the relevant details
And upload images of the property
Then the system should store my property details
And be visible to Searchers
And be located on the correct position on the map


Scenario: Removing a property
Given I am logged in as a Landlord
When I want to remove a property
Then the property should be removed from the system

Scenario: Update a property
Given I am logged in as a Landlord
When I update my property details
Then the property should be updated in the system

Feature: Reporting
As a user of the system, I should be able to report properties as a Searcher
Or I should be able to report Searchers as a Landlord so that I can protect myself and other users.

Scenario: Reporting a property
Given I am logged in as a Searcher
And I am looking at a property
When I report the property
Then an Administrator should be notified

Scenario: Reporting a Searcher
Given I am logged in as a Landlord
And I am looking at a Searcher profile
When I report the Searcher
Then an Administrator should be notified

Feature: Administrator abilities
As an administrator, I can receive report notifications and suspend or delete accounts so that I can protect users of the website

Scenario: Receiving reports
Given I am logged in as an Administrator
When I receive a report notification
Then I can view the report

Scenario: Deleting a user account
Given I am logged in as an Administrator
And I am viewing a report
When I delete a user account
Then the user should be removed from the system

Scenario: Suspending a user account
Given I am logged in as an Administrator
And I am viewing a report
When I suspend a user account
Then the user should be not be able to use their account for a specified amount of time

Scenario: Expiring user accounts
Given I am logged in as an Administrator
When I set an expiry date for user accounts
Then the account should expire after the given date

Feature: Messaging and user notifications
As a user or administrator, I should be able to message other users so that I can notify and communicate to other users.

Scenario: Administrator messaging
Given I am logged in as an Administrator
When I message a user
Then the user should be notified that they received a message

Scenario: Landlord messaging
Given I am logged in as an Landlord
When I message a Searcher
Then the Searcher should be notified that they received a message

Scenario: Searchers messaging
Given I am logged in as an Searcher
When I message a user
Then the user should be notified that they received a message

Scenario: Receiving notifications
Given I am logged in as a user
When I receive a notification
Then I should be able to view the received notification

Feature: Viewing user and administrator statistics
As a user I can view my account statistics
Or as a administrator I can view confidential statistics of all users.

Scenario: Searcher statistics
Given I am logged in as a Searcher
When I go to my profile
Then I should be able to view my Profile
And I should be able to view property history
And profile views.

Scenario: Landlord statistics
Given I am logged in as a Landlord
When I go to my profile
Then I should be able to view my profile view
And property views
And number of express interested tenants

Scenario: Administrator statistics
Given I am logged in as a Administrator
When I go to the statistics page
Then I should be able to view inactive members
And number of members 
And active members