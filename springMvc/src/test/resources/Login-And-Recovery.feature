Feature: Login and recovery

 As a user
 I would like to be able to login to the system
 So that I am authorised to access the services provided

# @Security
# @NotImplemented
# Scenario Outline: Login
#  Given I am a <ROLE> with username <USR> and password <PWD>
#  When I access <URL>
#  Then My authentication is <isAuth> with role <ROLE>
#
#     Examples:
#       | URL           | USR       | PWD        | ROLE        | isAuth |
#       | "/login-form" | "Jerry"   | "password" | "LANDLORD"  | true   |
#       | "/login-form" | "Jerry"   | "invalid"  | "LANDLORD"  | false  |
#       | "/login-form" | "Mark"    | "admin"    | "ADMIN"     | true   |
#       | "/login-form" | "Mark"    | "invalid"  | "ADMIN"     | false  |
#       | "/login-form" | "invalid" | "invalid"  | "ADMIN"     | false  |
#       | "/login-form" | "Harry"   | "football" | "SEARCHER"  | true   |
#       | "/login-form" | "Harry"   | "foo"      | "SEARCHER"  | false  |

# @Controller
# @NotImplemented
# Scenario: Username field length validation
#  Given I am on the login screen
#  And enter a username "UsernameIsTooLongToFitHere"
#  And password "invalid"
#  When I press login
#  Then I should receive an error message stating "Username must be between 3 to 12 characters"
#
# @Controller
# @NotImplemented
# Scenario: Entering non-existing login credentials
#  Given I have entered the username "JonJones"
#  And I have entered the password "Bones"
#  And the username "JonJones" does not exist in the database
#  When I press login
#  Then I receive the error message "Username does not exist"
#
# @Controller
# @NotImplemented
# Scenario: Entering the wrong password
#  Given I have a user "Bob" in the database
#  And with a password "validpassword"
#  And I enter "Bob" in the username field
#  And I enter the password "invalidpassword"
#  When I enter press login
#  Then I should receive an error message "Incorrect password, please try again."
#
# @Controller
# @NotImplemented
# Scenario: Logging out
#  Given a searcher "Bob" with password "foo"
#  And "Bob" is logged in
#  When searcher "Bob" presses logout
#  Then he should be redirected to the "login-page"
#
# @Security
# @NotImplemented
# Scenario: Password recovery
#  Given I have a Searcher "Bob" with password "foo"
#  And the user "Bob is registered with the email address "Bob@gmail.com"
#  And I request the "forgotten password" form
#  And I enter the email address "Bob@gmail.com"
#  When I press submit
#  Then the system should generate a unique link
#  And should send the unique link to the email "Bob@gmail.com"
#
# @Controller
# @NotImplemented
# Scenario: Recovering password with unpaired email
#  Given I have a Searcher "Bob" with password "foo"
#  And the user "Bob is registered with the email address "Bob@gmail.com"
#  And I request the "forgotten password" form
#  And I enter the email address "incorrect@hotmail.com"
#  When I press submit
#  Then I should receive an error message "Incorrect email"
#
# @Controller
# @NotImplemented
# Scenario: Logging into a temporarily suspended account
#  Given a user "Bob" with password "foo"
#  And "Bob" account is suspended
#  When "Bob" enters his username "Bob" and password "foo" into the login form fields
#  Then error message appears saying "Suspended account"
#  And is redirected to the "login" page
