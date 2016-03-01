package com.uni.c02015;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import com.uni.c02015.domain.User;
import com.uni.c02015.persistence.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import javax.servlet.Filter;

@WebAppConfiguration
@ContextConfiguration(classes = {SpringMvc.class, SecurityConfig.class, WebConfig.class})
public class AdminManagement {

  @Autowired
  private WebApplicationContext wac;
  @Autowired
  private Filter springSecurityFilterChain;
  @Autowired
  private UserRepository userRepository;

  private MockMvc mockMvc;
  private ResultActions result;
  private Authentication authentication;
  private User user;

  /**
   * Ran before every scenario.
   */
  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders
            .webAppContextSetup(this.wac)
            .addFilters(springSecurityFilterChain)
            .apply(springSecurity())
            .build();
  }

  @Given("^I am logged in as an administrator \"([^\"]*)\"$")
  public void iamlogged_in_as_an_administrator(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @When("^a user \"([^\"]*)\" is reported for malicious behaviour$")
  public void auser_is_reported_for_malicious_behaviour(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^I should be able to delete the user account \"([^\"]*)\"$")
  public void ishould_be_able_to_delete_the_user_account(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^user \"([^\"]*)\" is removed from the system$")
  public void useris_removed_from_the_system(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @When("^a searcher \"([^\"]*)\" makes a report about a user \"([^\"]*)\"$")
  public void asearcher_makes_a_report_about_a_user(String arg1, String arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^I should be notified$")
  public void ishould_be_notified() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^I should be able to view their report$")
  public void ishould_be_able_to_view_their_report() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^I should be able to suspend them for (\\d+) days$")
  public void ishould_be_able_to_suspend_them_for_days(int arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^user \"([^\"]*)\" cannot access the system for (\\d+) days$")
  public void usercannot_access_the_system_for_days(String arg1, int arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @When("^I search for inactive users$")
  public void isearch_for_inactive_users() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^only users that have been inactive for more than (\\d+) days should show$")
  public void onlyusers_that_have_been_inactive_for_more_than_days_should_show(int arg1)
          throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @When("^a searcher \"([^\"]*)\" has been inactive for (\\d+) or more$")
  public void asearcher_has_been_inactive_for_or_more(String arg1, int arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^I should be able to expire their account removing them from search results$")
  public void ishould_be_able_to_expire_their_account_removing_them_from_search_results()
          throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("^I am an administrator \"([^\"]*)\"$")
  public void iamanadministrator(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @When("^I request the total number of users of the website$")
  public void irequest_the_total_number_of_users_of_the_website() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^I should be able to view them$")
  public void ishould_be_able_to_view_them() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^only administrators should be able to view them$")
  public void onlyadministrators_should_be_able_to_view_them() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @When("^I receive a false listing report of \"([^\"]*)\" by a landlord \"([^\"]*)\"$")
  public void ireceive_a_false_listing_report_of_by_a_landlord(String arg1, String arg2)
          throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @When("^I review it$")
  public void ireview_it() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^I should be able remove the listing \"([^\"]*)\"$")
  public void ishould_be_able_remove_the_listing(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^I should be able to suspend landlord\"([^\"]*)\"$")
  public void ishould_be_able_to_suspend_landlord(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

}

