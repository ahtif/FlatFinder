Feature: Registration
  As a User
  I want to be able to register as Searcher or Landlord
  So that I can search for properties or add a property

  Background:
    Given a firstname "Majid"
    And a surname "Hussain"
    And a username "mh453"

@controller
Scenario: Requesting registration page for Searcher
    Given I am a user
    When I request the Searcher registration form
    Then the system shows the Searcher registration page

@controller
Scenario: Requesting registration page for Landlord
    Given I am a user
    When I request the Landlord registration form
    Then the system shows the Landlord registration page

@controller
Scenario: Email validation
    Given I have entered a email "@mh453.com"
    When I submit the form
    Then I should get an error message "Invalid email address entered"

@controller
Scenario: Existing Username
    Given I have entered an existing username
    When I submit the form
    Then an error message should occur: "username already exist"

@controller
Scenario: Entering non matching passwords
    Given I have entered a password "foo"
    And I have re-entered the confirmed password "boo"
    When I submit the form
    Then I should get the error message "Passwords dont match"

@controller
Scenario: Password validation
    Given I have entered a password which consists of 8 to 12 characters "password1"
    And I have re-entered the confirmed password "password1"
    When I submit the form
    Then the user is redirect to the login page

@domain
Scenario: Landlord submitting details
    Given a email "mh453@example.com"
    And password "password"
    And confirmed password "password"
    When I submit the form
    Then the system stores the Landlord with firstname "Majid"
    And surname "Hussain"
    And username "mh453"
    And password "password"
    And email "mh453@example.com"

@controller
Scenario: Landlord registering details (controller)
    Given a email "mh453@example.com"
    And password "password"
    And confirmed password "password"
    When I submit the form
    Then the user is redirect to the login page

@domain
Scenario: Searcher submitting details
    Given a email "ml33@example.com"
    And password "password123"
    And confirmed password "password123"
    And buddyUp is selected
    When I submit the form
    Then the system stores the Landlord with firstname "Majid"
    And surname "Hussain"
    And username "mh453"
    And password "password123"
    And email "ml33@example.com"
    And I should be subscribed to the buddy up system

@controller
Scenario: Searcher registering details (controller)
    Given a email "ml33@example.com"
    And password "password123"
    And confirmed password "password123"
    And buddyUp is selected
    When I submit the form
    Then the user is redirect to the login page
