#NB URL NEED TO BE CHANGED

Feature: Searching for Buddies

 In order to get possible flat mates
 As an Searcher I want to be able to search for possible buddies
 So that I can find suitable flat mates

@Domain
@NotImplemented
Scenario: Searching buddies by city and age
 Given a searcher "Majid" who is looking for a property in "Derby"
 And "Majid" is "20" years old
 And a searcher "John"
 When "John" looks for a buddy who is looking for a property in "Derby"
 And is "20" and over
 Then "Majid" will be included in the list of possible buddies

@Controller
@NotImplemented
Scenario: Searching buddies by city and age
 Given a searcher "Majid" who is looking for a property in "Derby"
 And "Majid" is "20" years old
 And a searcher "John"
 When I search for a buddy on the "findbuddy" page
 And set the location filter to "Derby"
 And age filter to "20 and over"
 Then I should be redirected to "findbuddy/location=derby&age=20+"

 @Controller
 @NotImplemeneted
 Scenario: Searching for people who havent chosen to buddyup
  Given a searcher "Majid" with password "foo"
  And a searcher "John" who hasnt opted in to the buddyup system
  And I am logged in as "Majid"
  When I search for a buddy "John"
  Then I should be redirected to "nosearchresult/buddy"

@Controller
@NotImplemented
 Scenario: Sending a buddy request
 Given a searcher "Majid" with password "foo"
 And a searcher "John"
 And I am logged in as "Majid"
 When I access the "search buddy page" to find "John"
 Then I should navigate to "buddySearch-John"
 And I should be able to send a buddy up request

@Domain
@NotImplemeneted
Scenario: Sending a buddy request (domain)
 Given a searcher "Majid" with password "foo"
 And a searcher "John"
 And I am logged in as "Majid"
 When I send "John" a "buddy request"
 Then a buddy request "Majid" will be added to the list of buddy request for "John"

 @Controller
 @NotImplemented
Scenario: Accepting buddy request
 Given a searcher "Majid" has sent searcher "John" a buddy request
 And "John" should have received a buddy requests notification
 When "John" access the notification "sent-buddy-request/user-John"
 And "accepts" the buddy request
 Then "John" should be redirect to the next notification

@Domain
@NotImplemented
Scenario: Accepting buddy request
 Given a searcher "Majid" has sent searcher "John" a buddy request
 When "John" accepts the buddy request
 Then "Majid" is added to "Johns" buddy list
 And removed from list of buddy request

@Controller
@NotImplemented
Scenario: Rejecting buddy request
 Given a searcher "Majid" has sent searcher "John" a buddy request
 And "John" should have received a buddy requests notification
 When "John" access the notification "sent-buddy-request/user-John"
 And "rejects" the buddy request
 Then "John" should be redirect to the next notification

@Domain
@NotImplemented
Scenario: Rejecting buddy request
 Given a searcher "Majid" has sent searcher "John" a buddy request
 When "John" rejects the buddy request
 Then "Majid" is not added to "Johns" buddy list
 And removed from list of buddy request
