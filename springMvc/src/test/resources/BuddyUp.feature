#NB URL NEED TO BE CHANGED

Feature: Searching for Buddies

 In order to get possible flat mates
 As an Searcher I want to be able to search for possible buddies
 So that I can find suitable flat mates

#@Domain
#@NotImplemented
#Scenario: Searching buddies by city and age
# Given a searcher "Majid" who is looking for a property in "Derby"
 #And a searcher "John"
 #When "John" looks for a buddy who is looking for a property in "Derby"
 #Then "Majid" will be included in the list of possible buddies

@Controller
Scenario: Searching buddies
 Given a searcher "Josh"
 And a listing with number "300" and road "Welford Road" by landlord "Ted"
 And "Josh" wants to buddy up on property with number "300" and road "Welford Road" by landlord "Ted"
 And a searcher "John"
 And "John" wants to buddy up on property with number "300" and road "Welford Road" by landlord "Ted"
 When "Josh" wants to see buddies 
 Then I should be redirected to the property buddy page for property with number "300" and road "Welford Road" by landlord "Ted"

 #@Controller
 #@NotImplemeneted
 #Scenario: Searching for people who havent chosen to buddyup
  #Given a searcher "Majid" with password "foo"
  #And a searcher "John" who hasnt opted in to the buddyup system
  #And I am logged in as "Majid"
  #When I search for a buddy "John"
  #Then I should be redirected to "nosearchresult/buddy"

#@Controller
#@NotImplemented
# Scenario: Sending a buddy request
# Given a searcher "Majid" with password "foo"
# And a searcher "John"
# And I am logged in as "Majid"
# When I access the "search buddy page" to find "John"
# Then I should navigate to "buddySearch-John"
# And I should be able to send a buddy up request

#@Domain
#@NotImplemeneted
#Scenario: Sending a buddy request (domain)
# Given a searcher "Majid" with password "foo"
# And a searcher "John"
# And I am logged in as "Majid"
# When I send "John" a "buddy request"
# Then a buddy request "Majid" will be added to the list of buddy request for "John"

 #@Controller
 #@NotImplemented
#Scenario: Accepting buddy request
# Given a searcher "Majid" has sent searcher "John" a buddy request
# And "John" should have received a buddy requests notification
 #When "John" access the notification "sent-buddy-request/user-John"
 #And "accepts" the buddy request
 #Then "John" should be redirect to the next notification

@Domain
Scenario: Accepting buddy request
 Given a searcher "Paul" has sent searcher "Barry" a buddy request
 When "Barry" accepts the buddy request
 Then "Paul" is added to "Barry" buddy list
 And he should be redirected to the "/buddy/viewAll?accepted=true" page

#@Controller
#@NotImplemented
#Scenario: Rejecting buddy request
# Given a searcher "Majid" has sent searcher "John" a buddy request
# And "John" should have received a buddy requests notification
# When "John" access the notification "sent-buddy-request/user-John"
# And "rejects" the buddy request
# Then "John" should be redirect to the next notification

@Controller
Scenario: Viewing a buddies profile
 Given a searcher "Andy" is a buddy with searcher "Louis"
 When "Andy" wants to view "Louis" profile
 Then he should be able to view "Louis" profile

@Controller
Scenario: Contacting a buddy
 Given a searcher "Dylan" is a buddy with searcher "Brian"
 When "Dylan" wants to contact "Brian"
 Then I should be on new message page


@Domain
Scenario: Rejecting buddy request
 Given a searcher "Harry" has sent searcher "Will" a buddy request
 When "Will" rejects the buddy request
 Then "Harry" is not added to "Will" buddy list
 And he should be redirected to the "/buddy/viewAll?rejected=true" page
