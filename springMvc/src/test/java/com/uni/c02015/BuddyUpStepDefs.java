package com.uni.c02015;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.uni.c02015.domain.Landlord;
import com.uni.c02015.domain.Message;
import com.uni.c02015.domain.Role;
import com.uni.c02015.domain.Searcher;
import com.uni.c02015.domain.User;
import com.uni.c02015.domain.buddy.BuddyProperty;
import com.uni.c02015.domain.buddy.Request;
import com.uni.c02015.domain.property.Property;
import com.uni.c02015.persistence.repository.LandlordRepository;
import com.uni.c02015.persistence.repository.MessageRepository;
import com.uni.c02015.persistence.repository.RoleRepository;
import com.uni.c02015.persistence.repository.SearcherRepository;
import com.uni.c02015.persistence.repository.UserRepository;
import com.uni.c02015.persistence.repository.buddy.BuddyPropertyRepository;
import com.uni.c02015.persistence.repository.buddy.RequestRepository;
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
  @Autowired
  private PropertyRepository propertyRepository;
  @Autowired
  private BuddyPropertyRepository buddyPropertyRepository;
  @Autowired
  private RequestRepository requestRepo;

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
  private Property property;
  private Property property2;
  private Request request;

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
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
        .addFilters(springSecurityFilterChain).apply(springSecurity()).build();

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
      buddyPropertyRepository.deleteAll();
      requestRepo.deleteAll();
      propertyRepository.deleteAll();
      messageRepository.deleteAll();
      landlordRepository.deleteAll();
      searcherRepository.deleteAll();
      userRepository.deleteAll();
      roleRepository.deleteAll();
    } catch (Exception e) {
      System.out.println("error");
    }
  }

  /**
   * Go to page to see a list of buddies for the property.
   * @param arg1 The searcher.
   */
  @When("^\"([^\"]*)\" wants to see buddies$")
  public void wants_to_see_buddies(String arg1) throws Throwable {

    result = mockMvc
        .perform(get("https://localhost:8070/buddy/showPropertyBuddies/" + property.getId())
            .with(authentication(new UsernamePasswordAuthenticationToken(arg1, "",
                AuthorityUtils.createAuthorityList("ROLE_SEARCHER"))))
            .with(csrf()))
        .andDo(print());
  }

  /**
   * Allow the user to buddy up on the given property.
   * @param arg1 The searcher.
   * @param arg2 The property number.
   * @param arg3 The property road.
   * @param arg4 The landlord of the property.
   */
  @Given("^\"([^\"]*)\" wants to buddy up on property with"
      + " number \"([^\"]*)\" and road \"([^\"]*)\" by landlord \"([^\"]*)\"$")
  public void wants_to_buddy_up_on_property_with_number_and_road_by_landlord(String arg1,
      String arg2, String arg3, String arg4) throws Throwable {
    User user = userRepository.findByLogin(arg1);
    Searcher searcher = searcherRepository.findOne(user.getId());
    property = propertyRepository.findByStreetAndNumber(arg3, arg2);
    BuddyProperty buddyProp = new BuddyProperty();
    buddyProp.setProperty(property);
    buddyProp.setUser(user);
    buddyPropertyRepository.save(buddyProp);
  }

  @Then("^I should be redirected to the property buddy page"
      + " for property with number \"([^\"]*)\" and road \"([^\"]*)\" by landlord \"([^\"]*)\"$")
  public void should_be_redirected_to_the_property_buddy_page(
      String arg1, String arg2, String arg3) throws Throwable {
    result.andExpect(view().name("/buddy/showPropertyBuddies"));
  }

  /**
   * Create 2 searchers and make a pending buddy request between them.
   * @param arg1 The first searcher.
   * @param arg2 The second searcher.
   */
  @Given("^a searcher \"([^\"]*)\" has sent searcher \"([^\"]*)\" a buddy request$")
  public void searcher_has_sent_searcher_a_buddy_request(String arg1, String arg2)
      throws Throwable {
    user1 = new User(arg1, "", roleRepository.findByRole("SEARCHER"));
    user2 = new User(arg2, "", roleRepository.findByRole("SEARCHER"));
    userRepository.save(user1);
    userRepository.save(user2);
    Property property = new Property();
    propertyRepository.save(property);
    request = new Request();
    request.setProperty(property);
    request.setSender(user1);
    request.setReceiver(user2);
    requestRepo.save(request);
  }

  /**
   * Accept a buddy request.
   * @param arg1 The searcher.
   */
  @When("^\"([^\"]*)\" accepts the buddy request$")
  public void accepts_the_buddy_request(String arg1) throws Throwable {

    result = mockMvc.perform(post("https://localhost:8070/buddy/accept/" + request.getId())
        .with(csrf()).with(authentication(new UsernamePasswordAuthenticationToken(arg1, "",
            AuthorityUtils.createAuthorityList("ROLE_SEARCHER")))));
  }

  /**
   * Check that the buddy request has been accepted.
   * @param arg1 The searcher.
   * @param arg2 The buddy.
   */
  @Then("^\"([^\"]*)\" is added to \"([^\"]*)\" buddy list$")
  public void is_added_to_buddy_list(String arg1, String arg2) throws Throwable {
    User user1 = userRepository.findByLogin(arg1);
    User user2 = userRepository.findByLogin(arg2);

    Request req = requestRepo.findOne(request.getId());
    Assert.assertEquals(req.getSender().getLogin(), arg1);
    Assert.assertEquals(req.getReceiver().getLogin(), arg2);
  }

  @Then("^removed from list of buddy request$")
  public void removed_from_list_of_buddy_request() throws Throwable {
    Request req = requestRepo.findOne(request.getId());
    Assert.assertTrue(req.getConfirmed());
  }

  @Then("^he should be redirected to the \"([^\"]*)\" page$")
  public void he_should_be_redirected_to_the_page(String arg1) throws Throwable {
    result.andExpect(redirectedUrl(arg1));
  }

  /**
   * Rejected a buddy request.
   * @param arg1 The searcher.
   */
  @When("^\"([^\"]*)\" rejects the buddy request$")
  public void rejects_the_buddy_request(String arg1) throws Throwable {
    result = mockMvc.perform(post("https://localhost:8070/buddy/reject/" + request.getId())
        .with(csrf()).with(authentication(new UsernamePasswordAuthenticationToken(arg1, "",
            AuthorityUtils.createAuthorityList("ROLE_SEARCHER")))))
        .andDo(print());
  }
  
  /**
   * Check that the buddy request is not accepted.
   * @param arg1 The searcher.
   * @param arg2 The buddy that was rejected.
   */
  @Then("^\"([^\"]*)\" is not added to \"([^\"]*)\" buddy list$")
  public void is_not_added_to_buddy_list(String arg1, String arg2) throws Throwable {
    User user1 = userRepository.findByLogin(arg1);
    User user2 = userRepository.findByLogin(arg2);
    Assert.assertNull(requestRepo.findBySenderAndReceiver(user1, user2));
  }
  
  /**
   * Create 2 searchers and make them buddies.
   * @param arg1 The searcher.
   * @param arg2 The buddy.
   */
  @Given("^a searcher \"([^\"]*)\" is a buddy with searcher \"([^\"]*)\"$")
  public void searcher_is_a_buddy_with_searcher(String arg1, String arg2) throws Throwable {
    user1 = new User(arg1, "", roleRepository.findByRole("SEARCHER"));
    user2 = new User(arg2, "", roleRepository.findByRole("SEARCHER"));
    userRepository.save(user1);
    userRepository.save(user2);
    Property property = new Property();
    propertyRepository.save(property);
    request = new Request();
    request.setProperty(property);
    request.setSender(user1);
    request.setReceiver(user2);
    request.setConfirmed(true);
    requestRepo.save(request);

  }
  
  /**
   * View a profile of a buddy.
   * @param arg1 The searcher.
   * @param arg2 The buddy.
   */
  @When("^\"([^\"]*)\" wants to view \"([^\"]*)\" profile$")
  public void wants_to_view_profile(String arg1, String arg2) throws Throwable {
    user1 = userRepository.findByLogin(arg1);
    user2 = userRepository.findByLogin(arg2);
    result = mockMvc.perform(get("https://localhost:8070/buddy/viewBuddy/" + user2.getId())
        .with(csrf()).with(authentication(new UsernamePasswordAuthenticationToken(arg1, "",
            AuthorityUtils.createAuthorityList("ROLE_SEARCHER")))));
  }

  @Then("^he should be able to view \"([^\"]*)\" profile$")
  public void he_should_be_redirected_to_profile(String arg1) throws Throwable {
    user1 = userRepository.findByLogin(arg1);
    result.andExpect(view().name("/buddy/view-buddy"));
  }

  /**
   * Message a buddy.
   * @param arg1 The searcher.
   * @param arg2 The buddy.
   */
  @When("^\"([^\"]*)\" wants to contact \"([^\"]*)\"$")
  public void wants_to_contact(String arg1, String arg2) throws Throwable {
    result = mockMvc.perform(get("https://localhost:8070/messaging/new?contact=" + user2.getId())
        .with(csrf()).with(authentication(new UsernamePasswordAuthenticationToken(arg1, "",
            AuthorityUtils.createAuthorityList("ROLE_SEARCHER")))));
  }

  @Then("^I should be on new message page$")
  public void should_be_on_new_message_page() throws Throwable {
    result.andExpect(view().name("messaging/newMessage"));
  }

}
