Feature: Registration
  As a User
  I want to be able to register as Searcher or Landlord
  So that I can search for properties or add a property

@domain
Scenario Outline: Creating User Account
    Given a username "mh453"
    And a password "password"
    And a user type "LANDLORD"
    When I press create account
    Then the system stores the User with username "mh453"
    And passwords "password"
    And user type "LANDLORD"

@controller
Scenario Outline: Creating User and redirected to registration page for Searcher
    Given I am a user with username "an001"
    And password "password123"
    And confirmed password "password123"
    And a user type "SEARCHER"
    When I press create account
    Then the system should redirect me to "/searcher/registration"

@controller
Scenario Outline: Creating User and redirected to registration page for Landlord
    Given I am a user with username "mh453"
    And a password "password"
    And a confirmed password "password"
    And a user type "LANDLORD"
    When I press create account
    Then the system should redirect me to "/landlord/registration"

@controller
Scenario Outline: Email validation
    Given a Landlord with firstname "Majid"
    And a surname "Hussain"
    And I have entered a email "@mh453.com"
    When a landlord request for a user account
    Then I should get an error message "Invalid email address entered"

@controller
Scenario Outline: Existing Username
    Given a User with username "mh453" which is an existing username in the User Repository
    And a password "password"
    And a confirmed password "password"
    And a user type "SEARCHER"
    When I request for a user account
    Then an error message should occur: "username already exist"

@controller
Scenario Outline: Entering non matching passwords
    Given a User with username "mh453"
    And a password "foo"
    And a confirmed password "boo"
    And a user type "SEARCHER"
    When I request for a user account
    Then I should get the error message "Passwords dont match"

@controller
Scenario Outline: Password validation
    Given a User with username "mh453"
    And I have entered a password which consists of 8 to 12 characters "password1"
    And I have entered the confirmed password "password1"
    When I request for a user account
    Then the user is redirect to the login page

@domain
Scenario Outline: Landlord submitting details
    Given a Landlord with firstname "Majid"
    And a surname "Hussain"
    And a email "mh453@example.com"
    When a Landlord submit the form
    Then the system stores the Landlord with firstname "Majid"
    And surname "Hussain"
    And email "mh453@example.com"

@controller
Scenario Outline: Landlord registering details (controller)
    Given a firstname "Majid"
    And a lastname "Hussain"
    And a email "mh453@example.com"
    When I Landlord submit the form
    Then the user is redirect to the login page

@domain
Scenario Outline: Searcher submitting details
    Given a Searcher with firstname "Andy"
    And a surname "Johnson"
    And a email "an001@example.com"
    And buddyUp is selected
    When I searcher submit the form
    Then the system stores the Landlord with firstname "Andy"
    And surname "Johnson"
    And email "an001@example.com"
    And buddyUp should be true

@controller
Scenario Outline: Searcher registering details (controller)
    Given a firstname "Andy"
    And a lastname "Johnson"
    And a email "an001@example.com"
    And buddyUp is selected
    When I searcher submit the form
    Then the user is redirect to the login page