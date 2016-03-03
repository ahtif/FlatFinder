Feature: Registration
  As a User
  I want to be able to register as Searcher or Landlord
  So that I can search for properties or add a property

@controller
Scenario: Creating User and redirected to registration page for Searcher
  Given I am a user with username "an001"
  And a password "password123"
  And a user type "SEARCHER"
  When I press create account
  Then the system should redirect me to "/searcher/registration"

@domain
Scenario: Creating User Account of type Searcher
  Given a username "an001"
  And a password "password123"
  And a user type "SEARCHER"
  When I press create account
  Then the system stores the User with username "an001"
  And passwords "password123"
  And user type "SEARCHER"

@controller
Scenario: Creating User and redirected to registration page for Landlord
  Given I am a user with username "mh453"
  And a password "password"
  And a user type "LANDLORD"
  When I press create account
  Then the system should redirect me to "/landlord/registration"

@domain
Scenario: Creating User Account of type Landlord
  Given a username "mh453"
  And a password "password"
  And a user type "LANDLORD"
  When a Landlord press create account
  Then the system stores the User with username "mh453"
  And passwords "password"
  And user type "LANDLORD"

@controller
Scenario: Landlord registering details (controller)
  Given a Landlord with firstname "Majid"
  And a Landlord with lastname "Hussain"
  And a Landlord with email "mh453@example.com"
  When a Landlord submit the form
  Then the user is redirect to the login page

@domain
Scenario: Landlord submitting details
  Given a Landlord with firstname "Majid"
  And a Landlord with lastname "Hussain"
  And a Landlord with email "mh453@example.com"
  When a Landlord submit the form
  Then the system stores the Landlord with firstname "Majid"
  And a Landlord with lastname "Hussain"
  And a Landlord with email "mh453@example.com"

@controller
Scenario: Searcher registering details (controller)
  Given a Searcher with firstname "Andy"
  And a Searcher with lastname "Johnson"
  And a Searcher with email "an001@example.com"
  And buddyUp is selected
  When a Searcher submit the form
  Then the user is redirect to the login page

@domain
Scenario: Searcher submitting details
  Given a Searcher with firstname "Andy"
  And a Searcher with lastname "Johnson"
  And a Searcher with email "an001@example.com"
  And buddyUp is selected
  When a Searcher submit the form
  Then the system stores the Searcher with firstname "Andy"
  And a Searcher with "Johnson"
  And a Searcher with email "an001@example.com"
  And buddyUp should be true

#Features for Sprint 2
#@controller
#Scenario: Email validation
#    Given a Landlord with firstname "Majid"
#    And a surname "Hussain"
#    And I have entered a email "@mh453.com"
#    When a landlord request for a user account
#    Then I should get an error message "Invalid email address entered"
#
#@controller
#Scenario: Existing Username
#    Given a User with username "mh453" which is an existing username in the User Repository
#    And a password "password"
#    And a confirmed password "password"
#    And a user type "SEARCHER"
#    When I request for a user account
#    Then an error message should occur: "username already exist"
#
#@controller
#Scenario: Entering non matching passwords
#    Given a User with username "mh453"
#    And a password "foo"
#    And a confirmed password "boo"
#    And a user type "SEARCHER"
#    When I request for a user account
#    Then I should get the error message "Passwords dont match"
#
#@controller
#Scenario: Password validation
#    Given a User with username "mh453"
#    And I have entered a password which consists of 8 to 12 characters "password1"
#    And I have entered the confirmed password "password1"
#    When I request for a user account
#    Then the user is redirect to the login page

