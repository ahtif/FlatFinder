Feature: Viewing and updating profile
 As a searcher or a landlord
 I want to have a profile and be able to view other users profiles
 So that I may find a suitable buddy-up partner or tenant

@Domain
@NotImplemented
Scenario: Changing preferences
 Given the Searcher "Bob"
 And I am opted in for the buddy-up system
 And my preference is set to allow "Smoking" tenant
 And my preference is set to allow "Undergraduate"
 When I update my profile and set my preference to "Non-smoking" tenant
 Then my profile should show my preferences as "Non-smoking"
 And "Undergraduate"

# @Domain
# @NotImplemented
# Scenario Outline: Tracking property views
#  Given a Landlord "James"
#  And I "James" owns properties <PROPERTIES>
#  And I have a "999" property views on each property
#  When a searcher "Bob" views my property "13 Aylestone Rd"
#  Then my property views for "13 Aylestone Rd" should be "1000"
#  And my property views for "300 Welford Rd" should be "999"
#  And my property views for "171 London Rd" should be "999"
#
# 	Examples:
# 	 |PROPERTIES	 |
# 	 |13 Aylestone Rd|
# 	 |171 London Rd  |
# 	 |300 Welford Rd |

@Controller
@NotImplemented
Scenario: Tracking recent looked at properties using controller
 Given a Searcher "Bob"
 When I access the profile page of "Bob"
 Then I should be navigate to "profile-page/Bob"

@Domain
@NotImplemented
Scenario: Tracking recent looked at properties
 Given a Searcher "Bob"
 And I "Bob" has recently viewed two properties, "Aylestone Road, LE2 7LG" and "Welford Road, LE2 6EG"
 When I access my profile page
 Then I should be able to see "Aylestone Road, LE2 7LG" and "Welford Road, LE2 6EG" on my recently viewed properties

@Controller
@NotImplemented
Scenario: Viewing profile of non buddies
 Given a Searcher "foo"
 And I am located on the searching buddies page
 And I am not a buddy with Searcher "Majid"
 When I access the profile page of "Majid"
 Then I should navigate to "searchresult/NotBuddy"

@Domain
@NotImplemented
Scenario: Viewing profile of buddies
 Given a Searcher "foo"
 And I am not a buddy with Searcher "Majid"
 When I add "Majid" to "foo" buddy list
 Then "foo" should be an located in "Majid" buddy list
