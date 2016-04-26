package com.uni.c02015;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import com.uni.c02015.domain.Landlord;
import com.uni.c02015.domain.Message;
import com.uni.c02015.domain.Role;
import com.uni.c02015.domain.Searcher;
import com.uni.c02015.domain.User;
import com.uni.c02015.domain.property.Property;
import com.uni.c02015.persistence.repository.LandlordRepository;
import com.uni.c02015.persistence.repository.MessageRepository;
import com.uni.c02015.persistence.repository.RoleRepository;
import com.uni.c02015.persistence.repository.SearcherRepository;
import com.uni.c02015.persistence.repository.UserRepository;
import com.uni.c02015.persistence.repository.property.PropertyRepository;

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
public class AdminManagementStepDefs {

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
  @Autowired
  private PropertyRepository propertyRepository;

  private MockMvc mockMvc;
  private ResultActions result;
  private Authentication authentication;
  private User user1;
  private User user2;
  private User user3;
  private Role adminRole;
  private User admin;
  private Searcher searcher;
  private Landlord landlord;
  private Message message;
  private Property property;

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
  @After
  public void delete() {
    try {
      propertyRepository.deleteAll();
      messageRepository.deleteAll();
      landlordRepository.deleteAll();
      searcherRepository.deleteAll();
      userRepository.deleteAll();
      roleRepository.deleteAll();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
  
  /**
   * Make a new user.
   * @param arg1 The name of the user.
   */
  @Given("^a user \"([^\"]*)\"$")
  public void user(String arg1) throws Throwable {
    Role searcher = roleRepository.findByRole("SEARCHER");
    User user = new User(arg1, "", searcher);
    userRepository.save(user);
  }

  // Unimplemented
  @Given("^\"([^\"]*)\" is reported for malicious behaviour$")
  public void is_reported_for_malicious_behaviour(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions

  }

  /**
   *.
   * @param arg1.
   */
  @When("^I delete the user account \"([^\"]*)\"$")
  public void delete_the_user_account(String arg1) throws Throwable {
    adminRole = roleRepository.findByRole("ADMIN");
    int id = userRepository.findByLogin(arg1).getId();
    result = mockMvc.perform(post("/admin/user/delete/" + id));
  }

  @Then("^user \"([^\"]*)\" is removed from the system$")
  public void user_is_removed_from_the_system(String arg1) throws Throwable {
    Assert.assertNull(userRepository.findByLogin(""));
  }

  // unimplemented
  @When("^I located the user \"([^\"]*)\" on the delete user page$")
  public void located_the_user_on_the_delete_user_page(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  // unimplemented
  @Then("^I should be redirect to \"([^\"]*)\"$")
  public void should_be_redirect_to(String arg1) throws Throwable {
    // result.andExpect(redirectedUrl("/admin/viewUsers"));

  }

  @When("^I suspend \"([^\"]*)\"$")
  public void suspend(String arg1) throws Throwable {
    int id = userRepository.findByLogin(arg1).getId();
    mockMvc.perform(post("/admin/user/suspend/" + id));
  }

  /**
   * Suspend a user.
   * @param arg1 The user.
   */
  @Then("^\"([^\"]*)\" is suspended$")
  public void is_suspended(String arg1) throws Throwable {
    user1 = userRepository.findByLogin(arg1);
    user1.isSuspended();
    userRepository.save(user1);
  }

  @Then("^account status is changed to \"([^\"]*)\"$")
  public void account_status_is_changed_to(String arg1) throws Throwable {
    user1.isSuspended();

  }

  // unimplemented
  @Given("^a user \"([^\"]*)\" who is active for (\\d+) days$")
  public void user_who_is_active_for_days(String arg1, int arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions

  }

  // unimplemented
  @When("^I search for inactive users$")
  public void search_for_inactive_users() throws Throwable {
    // Write code here that turns the phrase above into concrete actions

  }

  // unimplemented
  @Then("^\"([^\"]*)\" should be displayed in the list of inactive users$")
  public void should_be_displayed_in_the_list_of_inactive_users(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions

  }

  // unimplemented
  @Given("^a user \"([^\"]*)\" who is inactive for (\\d+) days$")
  public void user_who_is_inactive_for_days(String arg1, int arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions

  }

  // unimplemented
  @When("^click \"([^\"]*)\"$")
  public void click(String arg1) throws Throwable {

  }

  // unimplemented
  @When("^I suspend the user \"([^\"]*)\"$")
  public void suspend_the_user(String arg1) throws Throwable {

  }

  // unimplemented
  @Then("^the user account status is \"([^\"]*)\"$")
  public void the_user_account_status_is(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions

  }

  // unimplemented
  @When("^I located the user \"([^\"]*)\" on the inactive user page$")
  public void located_the_user_on_the_inactive_user_page(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions

  }

  // unimplemented
  @When("^I request the total number of users of the website$")
  public void request_the_total_number_of_users_of_the_website() throws Throwable {
    // Write code here that turns the phrase above into concrete actions

  }

  // unimplemented
  @Then("^I should be able to view them$")
  public void should_be_able_to_view_them() throws Throwable {
    // Write code here that turns the phrase above into concrete actions

  }

  // unimplemented
  @Then("^only administrators should be able to view them$")
  public void only_administrators_should_be_able_to_view_them() throws Throwable {
    // Write code here that turns the phrase above into concrete actions

  }

  /**
   * Create an Admin.
   * @param name The name of the admin.
   */
  @Given("^I am an admin \"([^\"]*)\"$")
  public void am_an_administrator(String name) throws Throwable {
    Role adminRole = roleRepository.findByRole("ADMINISTRATOR");
    admin = new User(name, "", adminRole);
    userRepository.save(admin);
  }
  
  /**
   * Create a new listing and assing a landlord to it.
   */
  @Given("^a listing with number \"([^\"]*)\" and road \"([^\"]*)\" by landlord \"([^\"]*)\"$")
  public void listing_with_number_and_road_by_landlord(
      String arg1, String arg2, String arg3) throws Throwable {

    Role landlordRole = roleRepository.findByRole("LANDLORD");
    User landlordUser = new User(arg3, "", landlordRole);
    userRepository.save(landlordUser);

    landlord = new Landlord(landlordUser.getId());

    landlord.setFirstName(arg3);
    landlordRepository.save(landlord);
    property = new Property();
    property.setNumber(arg1);
    property.setStreet(arg2);
    property.setLandlord(landlord);

    propertyRepository.save(property);

  }

  /**
   * Remove a property from the database.
   */
  @When("^\"([^\"]*)\" remove the property number \"([^\"]*)\" and street \"([^\"]*)\"$")
  public void remove_the_property_number_and_street(
      String arg1, String arg2, String arg3) throws Throwable {
    int id = propertyRepository.findByStreetAndNumber(arg3, arg2).getId();
    mockMvc.perform(post("https://localhost:8070/admin/property/delete/" + id)
        .with(authentication(new UsernamePasswordAuthenticationToken(admin.getLogin(), "",
            AuthorityUtils.createAuthorityList("ROLE_ADMINISTRATOR"))))
        .with(csrf()));
  }

  @Then("^the property \"([^\"]*)\" should be removed$")
  public void the_property_should_be_removed(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }
  
  @Then("^the property should be removed$")
  public void the_property_should_be_removed() throws Throwable {
    Assert.assertNull(propertyRepository
        .findByStreetAndNumber(property.getStreet(), property.getNumber()));
  }

  // unimplemented
  @Given("^I receive a false listing report$")
  public void receive_a_false_listing_report() throws Throwable {
    // Write code here that turns the phrase above into concrete actions

  }

  // unimplemented
  @When("^I remove the listing \"([^\"]*)\"$")
  public void remove_the_listing(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions

  }

  // unimplemented
  @When("^I suspend landlord \"([^\"]*)\"$")
  public void suspend_landlord(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions

  }



  // unimplemented
  @Then("^\"([^\"]*)\" account status should be suspend$")
  public void account_status_should_be_suspend(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions

  }

}
