package com.uni.c02015;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.uni.c02015.domain.Landlord;
import com.uni.c02015.domain.Message;
import com.uni.c02015.domain.Role;
import com.uni.c02015.domain.Searcher;
import com.uni.c02015.domain.User;
import com.uni.c02015.persistence.repository.LandlordRepository;
import com.uni.c02015.persistence.repository.MessageRepository;
import com.uni.c02015.persistence.repository.RoleRepository;
import com.uni.c02015.persistence.repository.SearcherRepository;
import com.uni.c02015.persistence.repository.UserRepository;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import cucumber.api.PendingException;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import javax.servlet.Filter;
import javax.validation.constraints.AssertTrue;

@WebAppConfiguration
@ContextConfiguration(classes = { SpringMvc.class, SecurityConfig.class, WebConfig.class })
public class BuddyUpStepDefs {

  @Autowired
  private WebApplicationContext wac;
  @Autowired
  private Filter springSecurityFilterChain;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private MessageRepository messageRepository;
  @Autowired
  private LandlordRepository landlordRepository;
  @Autowired
  private SearcherRepository searcherRepository;
  @Autowired
  private RoleRepository roleRepository;

  private MockMvc mockMvc;
  private ResultActions result;
  private Authentication authentication;
  private User user1;
  private User user2;
  private User user3;
  private User admin;
  private Searcher searcher;
  private Landlord landlord;
  private Message message;

  public static final int ROLE_ADMINISTRATOR_ID = 1;
  public static final String ROLE_ADMINISTRATOR = "ADMINISTRATOR";
  public static final int ROLE_LANDLORD_ID = 2;
  public static final String ROLE_LANDLORD = "LANDLORD";
  public static final int ROLE_SEARCHER_ID = 3;
  public static final String ROLE_SEARCHER = "SEARCHER";

  /**
   * Ran before every scenario.
   */
  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(
        this.wac).addFilters(springSecurityFilterChain)
        .apply(springSecurity()).build();

    // Set up Roles
    Role role = new Role();
    role.setId(ROLE_ADMINISTRATOR_ID);
    role.setRole(ROLE_ADMINISTRATOR);
    roleRepository.save(role);

    role = new Role();
    role.setId(ROLE_LANDLORD_ID);
    role.setRole(ROLE_LANDLORD);
    roleRepository.save(role);

    role = new Role();
    role.setId(ROLE_SEARCHER_ID);
    role.setRole(ROLE_SEARCHER);
    roleRepository.save(role);

    searcher = new Searcher();
    landlord = new Landlord();

  }

  /**
   * Delete contents from each repo's after each scenario.
   */
  // @After
  // public void delete() {
  // messageRepository.deleteAll();
  // searcherRepository.deleteAll();
  // landlordRepository.deleteAll();
  // userRepository.deleteAll();
  // roleRepository.deleteAll();
  // }
  //
  @Given("^a searcher \"([^\"]*)\" who is looking for a property in \"([^\"]*)\"$")
  public void searcher_who_is_looking_for_a_property_in(
      String arg1, String arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @Given("^\"([^\"]*)\" is \"([^\"]*)\" years old$")
  public void is_years_old(String arg1, String arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @When("^\"([^\"]*)\" looks for a buddy who is looking for a property in \"([^\"]*)\"$")
  public void looks_for_a_buddy_who_is_looking_for_a_property_in(
      String arg1, String arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @When("^is \"([^\"]*)\" and over$")
  public void is_and_over(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @Then("^\"([^\"]*)\" will be included in the list of possible buddies$")
  public void will_be_included_in_the_list_of_possible_buddies(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @When("^I search for a buddy on the \"([^\"]*)\" page$")
  public void search_for_a_buddy_on_the_page(
      String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @When("^set the location filter to \"([^\"]*)\"$")
  public void set_the_location_filter_to(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @When("^age filter to \"([^\"]*)\"$")
  public void age_filter_to(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @Then("^I should be redirected to \"([^\"]*)\"$")
  public void should_be_redirected_to(
      String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @Given("^a searcher \"([^\"]*)\" with password \"([^\"]*)\"$")
  public void searcher_with_password(
      String arg1, String arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @Given("^a searcher \"([^\"]*)\" who hasnt opted in to the buddyup system$")
  public void searcher_who_hasnt_opted_in_to_the_buddyup_system(
      String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @Given("^I am logged in as \"([^\"]*)\"$")
  public void am_logged_in_as(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @When("^I search for a buddy \"([^\"]*)\"$")
  public void search_for_a_buddy(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @When("^I access the \"([^\"]*)\" to find \"([^\"]*)\"$")
  public void access_the_to_find(String arg1, String arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @Then("^I should navigate to \"([^\"]*)\"$")
  public void should_navigate_to(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @Then("^I should be able to send a buddy up request$")
  public void should_be_able_to_send_a_buddy_up_request() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @When("^I send \"([^\"]*)\" a \"([^\"]*)\"$")
  public void send_a(String arg1, String arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @Then("^a buddy request \"([^\"]*)\" will be "
      + "added to the list of buddy request for \"([^\"]*)\"$")
  public void buddy_request_will_be_added_to_the_list_of_buddy_request_for(String arg1, String arg2)
      throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @Given("^a searcher \"([^\"]*)\" has sent searcher \"([^\"]*)\" a buddy request$")
  public void searcher_has_sent_searcher_buddy_request(String arg1, String arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @Given("^\"([^\"]*)\" should have received a buddy requests notification$")
  public void should_have_received_a_buddy_requests_notification(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @When("^\"([^\"]*)\" access the notification \"([^\"]*)\"$")
  public void access_the_notification(String arg1, String arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @When("^\"([^\"]*)\" the buddy request$")
  public void the_buddy_request(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @Then("^\"([^\"]*)\" should be redirect to the next notification$")
  public void should_be_redirect_to_the_next_notification(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @When("^\"([^\"]*)\" accepts the buddy request$")
  public void accepts_the_buddy_request(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @Then("^\"([^\"]*)\" is added to \"([^\"]*)\" buddy list$")
  public void is_added_to_buddy_list(String arg1, String arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @Then("^removed from list of buddy request$")
  public void removed_from_list_of_buddy_request() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @When("^\"([^\"]*)\" rejects the buddy request$")
  public void rejects_the_buddy_request(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @Then("^\"([^\"]*)\" is not added to \"([^\"]*)\" buddy list$")
  public void is_not_added_to_buddy_list(String arg1, String arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

}
