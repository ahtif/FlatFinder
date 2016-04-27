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
Scenario Outline: authorization
 Given I am an authenticated <ROLE> with username <USR>
 When I access <Service>
 Then My authentication is <isAuthorized> with role <ROLE>

     Examples:
      | Service                          | USR     | ROLE        | isAuthorized|
      | "/property/add"                  | "Jerry" | "LANDLORD"  | true        |
      | "/searcher/profile"              | "Harry" | "SEARCHER"  | true        |

@Security
Scenario Outline: Redirection to homepage based on role
 Given I am an authenticated user with <ROLE>
 When I go to my specific homepage
 Then I should be on a <ROLES> specific homepage.

     Examples:
 	| ROLE        | ROLES      |
 	| "LANDLORD"  | "LANDLORD" |
    | "SEARCHER"  | "SEARCHER" |
