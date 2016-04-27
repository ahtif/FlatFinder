package com.uni.c02015;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class LoginRecoveryStepDefs {

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
  private User user1 = new User();
  private User user2;
  private User user3;
  private Role adminRole;
  private User admin;
  private Searcher searcher;
  private Landlord landlord;
  private Message message;
  private Property property;
  private BCryptPasswordEncoder pe = new BCryptPasswordEncoder();

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

  @Given("^I have entered the username \"([^\"]*)\"$")
  public void have_entered_the_username(String arg1) throws Throwable {
    user1.setLogin(arg1);
  }

  @Given("^I have entered the password \"([^\"]*)\"$")
  public void have_entered_the_password(String arg1) throws Throwable {
    user1.setPassword(arg1);
  }

  @Given("^the username \"([^\"]*)\" does not exist in the database$")
  public void the_username_does_not_exist_in_the_database(String arg1) throws Throwable {
    Assert.assertNull(userRepository.findByLogin(arg1));
  }

  /**
   * Attempt to login.
   */
  @When("^I press login$")
  public void press_login() throws Throwable {
    result = mockMvc.perform(post("https://localhost:8070/login").param("username", user1.getLogin())
        .param("password", user1.getPassword()).with(csrf()));
  }

  @Then("^I receive the error message \"([^\"]*)\"$")
  public void receive_the_error_message(String arg1) throws Throwable {
    result.andExpect(redirectedUrl("/invalid-login"));
  }

  /**
   * Log in a user
   * 
   * @param arg1
   *          The username of the user.
   */
  @Given("^\"([^\"]*)\" is logged in$")
  public void is_logged_in(String arg1) throws Throwable {
    result = mockMvc.perform(post("https://localhost:8070/login").param("username", user1.getLogin())
        .param("password", user1.getPassword()).with(csrf()));
  }

  @When("^searcher \"([^\"]*)\" presses logout$")
  public void searcher_presses_logout(String arg1) throws Throwable {
    result = mockMvc.perform(get("https://localhost:8070/logout").with(csrf())).andDo(print());
  }

  @Then("^he should be redirected to the \"([^\"]*)\"$")
  public void he_should_be_redirected_to_the(String arg1) throws Throwable {
    result.andExpect(redirectedUrl(arg1));
  }

  @Given("^a searcher \"([^\"]*)\" with password \"([^\"]*)\"$")
  public void have_a_Searcher_with_password(String arg1, String arg2) throws Throwable {
    user1 = new User(arg1, pe.encode(arg2), roleRepository.findByRole(ROLE_SEARCHER));
    userRepository.save(user1);
  }

  /**
   * Create a user and save it to the database
   * 
   * @param arg1
   *          The user name of the user.
   * @param arg2
   *          The password of the user.
   */
  @Given("^I have a user \"([^\"]*)\" with password \"([^\"]*)\"$")
  public void user_with_password(String arg1, String arg2) throws Throwable {
    user1.setLogin(arg1);
    user1.setPassword(arg2);
    userRepository.save(user1);
  }

  /**
   * Created a searcher and store it in the database.
   * @param arg1 The username of the searcher.
   * @param arg2 The password of the searcher.
   * @param arg3 The email of the searcher.
   */
  @Given("^I have a Searcher \"([^\"]*)\" with"
      + " password \"([^\"]*)\" with email address \"([^\"]*)\"$")
  public void have_Searcher_with_password_with_email_address(String arg1, String arg2, String arg3)
      throws Throwable {
    user1 = new User(arg1, pe.encode(arg2), roleRepository.findByRole(ROLE_SEARCHER));
    user1.setEmailAddress(arg3);
    userRepository.save(user1);
  }

  @Given("^I request the forgotten password form$")
  public void request_the_forgotten_password_form() throws Throwable {
    result = mockMvc.perform(get("https://localhost:8070/forgot").with(csrf()));
  }

  @When("^I submit the password recovery form with username \"([^\"]*)\"$")
  public void recover_my_password_with_email_address(String arg1) throws Throwable {
    result = mockMvc.perform(post("https://localhost:8070/forgot/send").param("login", user1.getLogin())
        .with(csrf())).andDo(print());
  }

  @Then("^I should be redirected to the \"([^\"]*)\" page$")
  public void should_be_redirected_to_the_page(String arg1) throws Throwable {
    result.andExpect(redirectedUrl(arg1));
  }

  /**
   * Suspend a user account.
   * @param arg1 The user to suspend.
   */
  @Given("^\"([^\"]*)\" account is suspended$")
  public void account_is_suspended(String arg1) throws Throwable {
    user1.setSuspended(true);
    userRepository.save(user1);
  }
  
  /**
   * Attempt to login.
   * @param arg1 The username.
   * @param arg2 The password.
   */
  @When("^I login with username \"([^\"]*)\" and password \"([^\"]*)\"$")
  public void login_with_username_and_password(String arg1, String arg2) throws Throwable {
    result = mockMvc
        .perform(post("https://localhost:8070/login")
            .param("username", arg1)
            .param("password", arg2)
            .with(csrf()));
  }

}
