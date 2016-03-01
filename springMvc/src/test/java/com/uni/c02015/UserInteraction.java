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
public class UserInteraction {

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

  @When("^I send a message \"([^\"]*)\" to a landlord \"([^\"]*)\"$")
  public void isend_a_message_to_a_landlord(String arg1, String arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^they should receive the message \"([^\"]*)\"$")
  public void theyshould_receive_the_message(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("^I am a logged in as a searcher \"([^\"]*)\"$")
  public void iam_a_logged_in_as_a_searcher(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("^I am on my inbox page$")
  public void iam_on_my_inbox_page() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @When("^I receive a message \"([^\"]*)\" from a user \"([^\"]*)\"$")
  public void ireceive_a_message_from_a_user(String arg1, String arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^I should be able to reply with \"([^\"]*)\" from my inbox$")
  public void ishould_be_able_to_reply_with_from_my_inbox(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("^the user \"([^\"]*)\" is a landlord$")
  public void theuser_is_a_landlord(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("^the user \"([^\"]*)\" is the owner of the property$")
  public void theuser_is_the_owner_of_the_property(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @When("^I like a property \"([^\"]*)\"$")
  public void ilike_a_property(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^user \"([^\"]*)\" receives a notification that user \"([^\"]*)\" liked his property$")
  public void user_receive_a_notification_that_user_liked_his_property(
          String arg1, String arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("^I am logged in as an administator \"([^\"]*)\"$")
  public void iam_logged_in_as_an_administator(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @When("^I broadcast a message \"([^\"]*)\"$")
  public void ibroadcast_a_message(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^users \"([^\"]*)\" and \"([^\"]*)\" should receive the message \"([^\"]*)\"$")
  public void usersand_should_receive_the_message(String arg1, String arg2, String arg3)
          throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("^I am a registered searcher \"([^\"]*)\"$")
  public void iam_a_registered_searcher(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @When("^I have dealt with a landlord \"([^\"]*)\"$")
  public void ihave_dealt_with_a_landlord(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^I should be able to leave feedback: \"([^\"]*)\"$")
  public void ishould_be_able_to_leave_feedback(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^rate him (\\d+)/(\\d+) stars$")
  public void ratehim_stars(int arg1, int arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("^I am logged in$")
  public void iam_logged_in() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @When("^a searcher Bob expresses interest$")
  public void asearcher_Bob_expresses_interest() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @When("^a searcher Sarah expresses interest$")
  public void asearcher_Sarah_expresses_interest() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @When("^a searcher Jacob expresses interest$")
  public void asearcher_Jacob_expresses_interest() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @When("^a searcher Kim expresses interest$")
  public void asearcher_Kim_expresses_interest() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("^I am a searcher \"([^\"]*)\"$")
  public void iam_a_searcher(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @When("^a landlord \"([^\"]*)\" makes an inaccurate property listing$")
  public void alandlord_makes_an_inaccurate_property_listing(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^I should be able to report them$")
  public void ishould_be_able_to_report_them() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^the administrator \"([^\"]*)\" should be notified$")
  public void theadministrator_should_be_notified(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

}

