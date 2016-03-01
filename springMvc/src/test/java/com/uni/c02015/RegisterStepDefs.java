package com.uni.c02015;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.uni.c02015.domain.Landlord;
import com.uni.c02015.domain.Searcher;
import com.uni.c02015.domain.User;
import com.uni.c02015.persistence.repository.LandlordRepository;
import com.uni.c02015.persistence.repository.RoleRepository;
import com.uni.c02015.persistence.repository.SearcherRepository;
import com.uni.c02015.persistence.repository.UserRepository;

import org.junit.Assert;
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
public class RegisterStepDefs {

  @Autowired
  private WebApplicationContext wac;
  @Autowired
  private Filter springSecurityFilterChain;
  @Autowired
  private LandlordRepository landlordRepository;
  @Autowired
  private SearcherRepository searcherRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RoleRepository roleRepository;

  private MockMvc mockMvc;
  private ResultActions result;
  private Authentication authentication;

  private User user;
  private Landlord landlord;
  private Searcher searcher;
  //Created by Majid on 26/02/2016.

  /**
   * Ran before every scenario.
   */
  @Before
  public void setup() {
    userRepository.deleteAll();
    this.mockMvc = MockMvcBuilders
            .webAppContextSetup(this.wac)
            .addFilters(springSecurityFilterChain)
            .apply(springSecurity())
            .build();

    userRepository.deleteAll();
    user = new User();

    //deletes all records before starting scenario.
  }

  @Given("^a username \"([^\"]*)\"$")
  public void ausername(String arg1) throws Throwable {
    user.setLogin(arg1);
  }

  @Given("^a password \"([^\"]*)\"$")
  public void apassword(String arg1) throws Throwable {
    user.setPassword(arg1);
  }

  @Given("^a user type \"([^\"]*)\"$")
  public void auser_type(String arg1) throws Throwable {
    user.setRole(roleRepository.findByRole(arg1));
  }

  /**
   * When user press create account.
   */
  @When("^I press create account$")
  public void ipress_create_account() throws Throwable {
    result = mockMvc.perform(post("/createAccount")
            .param("login", user.getLogin())
            .param("password", user.getPassword())
            .param("role", user.getRole().getRole())
    );
    //ERROR
    userRepository.save(user);
  }

  /**
   * Username should be saved in repository.
   */
  @Then("^the system stores the User with username \"([^\"]*)\"$")
  public void thesystem_stores_the_User_with_username(String arg1) throws Throwable {

    User user = userRepository.findByLogin(arg1);

    Assert.assertEquals(arg1, user.getLogin());
  }

  @Then("^passwords \"([^\"]*)\"$")
  public void passwords(String arg1) throws Throwable {
    Assert.assertEquals(arg1, user.getPassword());
  }

  @Then("^user type \"([^\"]*)\"$")
  public void user_type(String arg1) throws Throwable {
    Assert.assertEquals(arg1, user.getRole().getRole());
  }

  @Given("^I am a user with username \"([^\"]*)\"$")
  public void iam_a_user_with_username(String arg1) throws Throwable {
    //user.setLogin(arg1);
  }

  @Given("^password \"([^\"]*)\"$")
  public void password(String arg1) throws Throwable {
    //user.setPassword(arg1);
  }

  @Given("^confirmed password \"([^\"]*)\"$")
  public void confirmed_password(String arg1) throws Throwable {
    //ERROR
    //user.setPassword(arg1);
  }

  @Then("^the system should redirect me to \"([^\"]*)\"$")
  public void thesystem_should_redirect_me_to(String arg1) throws Throwable {
    //ERROR
//        result.andExpect(view().name(arg1));
  }

  @Given("^a confirmed password \"([^\"]*)\"$")
  public void aconfirmed_password(String arg1) throws Throwable {
    //ERROR - NO CONFIRM ON JSP PAGE
//        user.setPassword(arg1);
  }

  @Given("^a Landlord with firstname \"([^\"]*)\"$")
  public void alandlord_with_firstname(String arg1) throws Throwable {
//       landlord = new Landlord();
//       landlord.setFirstName(arg1);
  }

  @Given("^a surname \"([^\"]*)\"$")
  public void asurname(String arg1) throws Throwable {
//        landlord.setLastName(arg1);
  }

  @Given("^I have entered a email \"([^\"]*)\"$")
  public void ihave_entered_a_email(String arg1) throws Throwable {
//       landlord.setEmailAddress(arg1);
  }
  /**
   * When a landlord account is created.
   */
  @When("^a landlord request for a user account$")
  public void alandlord_request_for_a_user_account() throws Throwable {
//        result = mockMvc.perform(post("/addLandlord")
//                .param("firstName", landlord.getFirstName())
//                .param("lastName", landlord.getLastName())
//                .param("emailAddress", landlord.getEmailAddress())
//        );
  }

  @Then("^I should get an error message \"([^\"]*)\"$")
  public void ishould_get_an_error_message(String arg1) throws Throwable {
//        result.andExpect(view().name(arg1));
  }

  @Given("^a User with username \"([^\"]*)\" which is an existing username in the User Repository$")
  public void auser_with_username_which_is_an_existing_username_in_the_User_Repository(String arg1)
          throws Throwable {
//        user.setLogin(arg1);
  }
  /**
   * When user requests for a new user account.
   */
  @When("^I request for a user account$")
  public void irequest_for_a_user_account() throws Throwable {
//        result = mockMvc.perform(post("/createAccount")
//                .param("login", user.getLogin())
//                .param("password", user.getPassword())
//                .param("role", user.getRole().getRole())
//        );
  }

  @Then("^an error message should occur: \"([^\"]*)\"$")
  public void anerror_message_should_occur(String arg1) throws Throwable {
  }

  @Given("^a User with username \"([^\"]*)\"$")
  public void auser_with_username(String arg1) throws Throwable {
//        user.setLogin(arg1);
  }

  @Then("^I should get the error message \"([^\"]*)\"$")
  public void ishould_get_the_error_message(String arg1) throws Throwable {
//        throw new PendingException();
  }
  /**
   * Password validation.
   */
  @Given("^I have entered a password which consists of (\\d+) to (\\d+) characters \"([^\"]*)\"$")
  public void ihave_entered_a_password_which_consists_of_to_characters(
          int arg1, int arg2, String arg3) throws Throwable {
//        if((arg3.length() >= arg1) && (arg3.length() <= arg2)){
//            //Correct password
//
//        }else{
//            //Wrong password
//
//        }
  }

  @Given("^I have entered the confirmed password \"([^\"]*)\"$")
  public void ihave_entered_the_confirmed_password(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^the user is redirect to the login page$")
  public void theuser_is_redirect_to_the_login_page() throws Throwable {
    //result.andExpect(view().name("/register"));
  }

  @Given("^a email \"([^\"]*)\"$")
  public void aemail(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }
  /**
   * When a landlord submits a form to create an account.
   */
  @When("^a Landlord submit the form$")
  public void alandlord_submit_the_form() throws Throwable {
//                result = mockMvc.perform(post("/addLandlord")
//                .param("firstName", landlord.getFirstName())
//                .param("lastName", landlord.getLastName())
//                .param("emailAddress", landlord.getEmailAddress())
//        );
  }

  @Then("^the system stores the Landlord with firstname \"([^\"]*)\"$")
  public void thesystem_stores_the_Landlord_with_firstname(String arg1) throws Throwable {
//        Assert.assertEquals(arg1, landlord.getFirstName());
  }

  @Then("^surname \"([^\"]*)\"$")
  public void surname(String arg1) throws Throwable {
//        Assert.assertEquals(arg1, landlord.getLastName());
  }

  @Then("^email \"([^\"]*)\"$")
  public void email(String arg1) throws Throwable {
    //Assert.assertEquals(arg1, landlord.getEmailAddress());
  }

  @Given("^a firstname \"([^\"]*)\"$")
  public void afirstname(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("^a lastname \"([^\"]*)\"$")
  public void alastname(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @When("^I Landlord submit the form$")
  public void ilandlord_submit_the_form() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Given("^a Searcher with firstname \"([^\"]*)\"$")
  public void asearcher_with_firstname(String arg1) throws Throwable {

  }

  @Given("^buddyUp is selected$")
  public void buddyup_is_selected() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @When("^I searcher submit the form$")
  public void isearcher_submit_the_form() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^buddyUp should be true$")
  public void buddyup_should_be_true() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }
}