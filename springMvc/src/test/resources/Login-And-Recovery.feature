Feature: Login and recovery
	As a user
	I would like to be able to login to the system
	So that I am authorised to access the services provided

	@controller
	Scenario: Access login page
	Given I am a registered user "bob"
	When I request the login page
	Then I should be able to access 
	And I will be prompted to enter my credentials 

	@security
	Scenario Outline: logging in to the system
	Given I am a registered user <USER> with account type <TYPE>
	And I am on the login screen
	When I enter username <USER> and password <PASSWORD>
	Then I should be <IsAuthorised> to use the system

	Examples:
	|  USER	      |  PASSWORD | TYPE            |IsAuthorised  |
	| "bob"	      | "pw123"   | "Searcher"      |"authorised"  |
	| "ted"       | "tlop"    | "Landlord"      |"authorised"  |
	| "key&peele" | "partynd" | "Administrator" |"unauthorised"|
	| "james"     | "kanye"   | "Administrator" |"authorised"  |
	| "bob"	      | "mrwest"  | "Searcher"	    |"unauthorised"|
	| "james"     | "drizzy"  | "Administrator" |"unauthorised"|
	| "ted"       | "yeezy"   | "Landlord"	    |"unauthorised"|	

	
	@security
	Scenario: Password masking
	Given I am on the login screen
	When I enter my password "password"
	Then the password should be masked with asterisk symbols
	And it should be displayed as "********"
	
	
	@controller
	Scenario: Username field length validation
	Given I am on the login screen
	When I enter the username "UsernameIsTooLongToFitHere"
	Then I should receive an error message stating: "Username must be between "3 to 12 characters"

	@controller
	Scenario: Entering non-existing login credentials
	Given I have entered the username "JonJones"
	And I have entered the password "bones"
	And the username "JonJones" does not exist in the database
	When I press login
	Then I receive the error message "Username does not exist"

	@controller
	Scenario: Entering the wrong password
	Given I have a login "bob" in the database
	And with a password "validpassword"
	And I enter "bob" in the username field
	And I enter the password "invalidpassword"
	When I enter press login
	Then I should receive an error message "Incorrect password, please try again."

	@controller
	Scenario: logging out
	Given a searcher "bob" is logged in
	When searcher "bob" presses logout
	Then he should be redirected to the login page

	@security
	Scenario: password recovery
	Given I am a registered user "bob"
	And I have forgotten my password
	And I have requested the 'forgotten password' form
	And I enter the valid and matching email: "bob@gmail.com"
	When I request the 'forgotten password' form
	Then I should receive an email with my password

	@controller
	Scenario: recovering password with unpaired email
	Given I have entered the username "bob"
	And the username "bob is registered with the email address "bob@gmail.com"
	And I request the 'forgotten password' form
	And I enter the email address "incorrect@hotmail.com"
	When I press submit
	Then I should receive an error message "Incorrect email for user 'bob'"
	

