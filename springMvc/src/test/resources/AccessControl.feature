Feature: Access Control

 In order to access control content
 As a Users should be able to only access certain content based on their role
 So that the system is secure

@Security
Scenario Outline: multiuser authentication
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

@Security
@NotImplemented
Scenario Outline: authorization
 Given I am an authenticated <ROLE> with username <USR>
 When I access <Service>
 Then My authentication is <isAuthorized> with role <ROLE>

     Examples:
      | Service                          | USR     | ROLE        | isAuthorized|
      | "/property/addProperty"          | "Jerry" | "LANDLORD"  | true        |
      | "/property/propertyStatistic"    | "Jerry" | "LANDLORD"  | true        |
      | "/profile/viewInbox"             | "Jerry" | "LANDLORD"  | true        |
      | "/profile/sendMessage"           | "Jerry" | "LANDLORD"  | true        |
      | "/profile/profileStatistic"      | "Jerry" | "LANDLORD"  | true        |
      | "/reporting/sendReport"          | "Jerry" | "LANDLORD"  | true        |
#      | "/reporting/reviewReports"       | "Jerry" | "LANDLORD"  | false       |
#      | "/profile/sendAllUsersMessage"   | "Jerry" | "LANDLORD"  | false       |
#      | "/accounts/deleteAccounts"       | "Jerry" | "LANDLORD"  | false       |
#      | "/accounts/UsersStatistics"      | "Jerry" | "LANDLORD"  | false       |
#      | "/property/search"               | "Jerry" | "LANDLORD"  | false       |
#      | "/property/propertyResultMap"    | "Jerry" | "LANDLORD"  | false       |
#      | "/property/BuddyUp"              | "Jerry" | "LANDLORD"  | false       |
#      | "/property/likeProperty"         | "Jerry" | "LANDLORD"  | false       |
#      | "/GPS/findProperty"              | "Jerry" | "LANDLORD"  | false       |
#      | "/reporting/reviewReports"       | "Mark"  | "ADMIN"     | true        |
#      | "/profile/sendAllUsersMessage"   | "Mark"  | "ADMIN"     | true        |
#      | "/accounts/deleteAccounts"       | "Mark"  | "ADMIN"     | true        |
#      | "/accounts/suspendAccounts"      | "Mark"  | "ADMIN"     | true        |
#      | "/accounts/expireAccounts"       | "Mark"  | "ADMIN"     | true        |
#      | "/accounts/UsersStatistics"      | "Mark"  | "ADMIN"     | true        |
#      | "/property/addProperty"          | "Mark"  | "ADMIN"     | false       |
#      | "/reporting/sendReport"          | "Mark"  | "ADMIN"     | false       |
#      | "/property/search"               | "Mark"  | "ADMIN"     | false       |
#      | "/property/propertyResultMap"    | "Mark"  | "ADMIN"     | false       |
#      | "/property/BuddyUp"              | "Mark"  | "ADMIN"     | false       |
#      | "/property/likeProperty"         | "Mark"  | "ADMIN"     | false       |
#      | "/GPS/findProperty"              | "Mark"  | "ADMIN"     | false       |
#      | "/property/search"               | "Harry" | "SEARCHER"  | true        |
#      | "/property/advanceSearch"        | "Harry" | "SEARCHER"  | true        |
#      | "/property/propertyResultMap"    | "Harry" | "SEARCHER"  | true        |
#      | "/property/BuddyUp"              | "Harry" | "SEARCHER"  | true        |
#      | "/property/likeProperty"         | "Harry" | "SEARCHER"  | true        |
#      | "/profile/profileStatistic"      | "Harry" | "SEARCHER"  | true        |
#      | "/profile/propertyHistory"       | "Harry" | "SEARCHER"  | true        |
#      | "/GPS/findProperty"              | "Harry" | "SEARCHER"  | true        |
#      | "/property/mostViewed"           | "Harry" | "SEARCHER"  | true        |
#      | "/property/addProperty"          | "Harry" | "SEARCHER"  | false       |
#      | "/reporting/reviewReports"       | "Harry" | "SEARCHER"  | false       |
#      | "/profile/sendAllUsersMessage"   | "Harry" | "SEARCHER"  | false       |
#      | "/accounts/deleteAccounts"       | "Harry" | "SEARCHER"  | false       |
#      | "/accounts/UsersStatistics"      | "Harry" | "SEARCHER"  | false       |

@Security
@NotImplemented
Scenario Outline: Redirection to homepage based on role
 Given I am an authenticated user with <ROLE>
 When I go to my specific homepage
 Then I should be on a <ROLES> specific homepage.

     Examples:
 	| ROLE        | ROLES      |
 	| "LANDLORD"  | "LANDLORD" |
  | "ADMIN"     | "ADMIN"    |
  | "SEARCHER"  | "SEARCHER" |
