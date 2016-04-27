Feature: Login and recovery

 As a user
 I would like to be able to login to the system
 So that I am authorised to access the services provided

@Security
@NotImplemented
Scenario Outline: Login
Given I am a <ROLE> with username <USR> and password <PWD>
When I access <URL>
Then My authentication is <isAuth> with role <ROLE>

     Examples:
       | URL           | USR       | PWD        | ROLE        | isAuth |
       | "/login-form" | "Jerry"   | "password" | "LANDLORD"  | true   |
       | "/login-form" | "Jerry"   | "invalid"  | "LANDLORD"  | false  |
       | "/login-form" | "Mark"    | "admin"    | "ADMIN"     | true   |
       | "/login-form" | "Mark"    | "invalid"  | "ADMIN"     | false  |
       | "/login-form" | "invalid" | "invalid"  | "ADMIN"     | false  |
       | "/login-form" | "Harry"   | "football" | "SEARCHER"  | true   |
       | "/login-form" | "Harry"   | "foo"      | "SEARCHER"  | false  |

@Controller
Scenario: Username field length validation
 Given I have entered the username "UsernameIsTooLongToFitHere"
 And I have entered the password "invalid"
 When I press login
 Then I receive the error message "Username must be between 3 to 12 characters"

@Controller
Scenario: Entering non-existing login credentials
 Given I have entered the username "JonJones"
 And I have entered the password "Bones"
 And the username "JonJones" does not exist in the database
 When I press login
 Then I receive the error message "Username does not exist"

@Controller
Scenario: Entering the wrong password
 Given I have a user "Bob" with password "password"
 And I have entered the username "Bob"
 And I have entered the password "invalid"
 When I press login
 Then I receive the error message "Invalid username or password"

@Controller
Scenario: Logging out
 Given a searcher "Bob" with password "foo"
 And "Bob" is logged in
 When searcher "Bob" presses logout
 Then he should be redirected to the "/user-logout"

@Security
@NotImplemented
Scenario: Password recovery
 Given I have a Searcher "Barry" with password "foo" with email address "Bob@gmail.com"
 And I request the forgotten password form
 When I submit the password recovery form with username "Barry"
 Then I should be redirected to the "/forgot?submitted=true" page

@Controller
@NotImplemented
Scenario: Logging into a temporarily suspended account
 Given I have a user "Joe" with password "foo"
 And "Joe" account is suspended
 When I login with username "Joe" and password "foo"
 Then I receive the error message "Suspended account"
