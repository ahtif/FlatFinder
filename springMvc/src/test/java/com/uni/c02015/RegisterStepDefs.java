package com.uni.c02015;

import com.uni.c02015.domain.User;
import com.uni.c02015.persistence.repository.LandlordRepository;
import com.uni.c02015.persistence.repository.RoleRepository;
import com.uni.c02015.persistence.repository.SearcherRepository;
import com.uni.c02015.persistence.repository.UserRepository;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import javax.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

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
  private ArrayList<User> userAccounts;
  //Created by Majid on 26/02/2016.

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

    user = new User();
    //deletes all records before starting scenario
    userRepository.deleteAll();
  }

  @Given("^a username \"([^\"]*)\"$")
  public void ausername(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    user.setLogin(arg1);
  }

  @Given("^a password \"([^\"]*)\"$")
  public void apassword(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    user.setPassword(arg1);
  }

  @Given("^a user type \"([^\"]*)\"$")
  public void auser_type(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    user.setRole(roleRepository.findByRole(arg1));
  }

  @When("^I press create account$")
  public void ipress_create_account() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    result = mockMvc.perform(post("/createAccount")
            .param("login",user.getLogin())
            .param("password",user.getPassword())
            .param("role",user.getRole().toString()));

    //Not sure about this
    userRepository.save(user);
  }

  @Then("^the system stores the User with username \"([^\"]*)\"$")
  public void the_system_stores_the_User_with_username(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    boolean found = false;

    for (User eachUser : userRepository.findAll()) {
      System.out.println(eachUser.getLogin());
      if (eachUser.getLogin().equals(arg1)) {
        found = true;
      }
    }
    assertThat(found, is(true));
  }

  @Then("^passwords \"([^\"]*)\"$")
  public void passwords(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    boolean found = false;

    for (User user : userRepository.findAll()) {
      if (user.getPassword().equals(arg1)) {
        found = true;
      }
    }
    assertThat(found, is(true));
  }

  @Then("^user type \"([^\"]*)\"$")
  public void user_type(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    boolean found = false;
    for (User user : userRepository.findAll()) {
      if (user.getRole().getRole().equals(arg1)) {
        found = true;
      }
    }
    assertThat(found, is(true));
  }

  @Given("^I am a user with username \"([^\"]*)\"$")
  public void iam_a_user_with_username(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    user.setLogin(arg1);
  }

  @Given("^password \"([^\"]*)\"$")
  public void password(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    user.setPassword(arg1);
  }

  @Given("^confirmed password \"([^\"]*)\"$")
  public void confirmed_password(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    user.setPassword(arg1);
  }

  @Then("^the system should redirect me to \"([^\"]*)\"$")
  public void the_system_should_redirect_me_to(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    //throw new PendingException();
  }

  @Given("^a Landlord with firstname \"([^\"]*)\"$")
  public void aLandlord_with_firstname(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    //throw new PendingException();
  }

  @Given("^a surname \"([^\"]*)\"$")
  public void asurname(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    //throw new PendingException();
  }

  @Given("^I have entered a email \"([^\"]*)\"$")
  public void ihave_entered_a_email(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    //throw new PendingException();
  }

  @When("^I request for a user account$")
  public void irequest_for_a_user_account() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    //throw new PendingException();
  }

  @Then("^I should get an error message \"([^\"]*)\"$")
  public void ishould_get_an_error_message(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    //throw new PendingException();
  }

  @Given("^a User with username \"([^\"]*)\" which is an existing username in the User Repository$")
  public void aUser_with_username_which_is_an_existing_username_in_the_User_Repository(String arg1)
          throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    //throw new PendingException();
  }

  @Given("^a confirmed password \"([^\"]*)\"$")
  public void aconfirmed_password(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    //throw new PendingException();
  }

  @Then("^an error message should occur: \"([^\"]*)\"$")
  public void anerror_message_should_occur(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    //throw new PendingException();
  }

  @Given("^a User with username \"([^\"]*)\"$")
  public void aUser_with_username(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    //throw new PendingException();
  }

  @Then("^I should get the error message \"([^\"]*)\"$")
  public void ishould_get_the_error_message(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    //throw new PendingException();
  }

  @Given("^I have entered a password which consists of (\\d+) to (\\d+) characters \"([^\"]*)\"$")
  public void ihave_entered_a_password_which_consists_of_to_characters(int arg1, int arg2, String arg3)
          throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    //throw new PendingException();
  }

  @Given("^I have entered the confirmed password \"([^\"]*)\"$")
  public void ihave_entered_the_confirmed_password(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    //throw new PendingException();
  }

  @Then("^the user is redirect to the login page$")
  public void the_user_is_redirect_to_the_login_page() throws Throwable {
    // Write code here that turns the phrase above into concrete action
    throw new PendingException();
  }

  @Given("^a email \"([^\"]*)\"$")
  public void aemail(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    //throw new PendingException();
  }

  @When("^I submit the form$")
  public void isubmit_the_form() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    //throw new PendingException();
  }

  @Then("^the system stores the Landlord with firstname \"([^\"]*)\"$")
  public void the_system_stores_the_Landlord_with_firstname(String arg1)
  throws Throwable {
    //                                                                                                                                                                 Write code here that turns the phrase above into concrete actions
    //throw new PendingException();
  }

  @Then("^surname \"([^\"]*)\"$")
  public void surname(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    //throw new PendingException();
  }

  @Then("^email \"([^\"]*)\"$")
  public void email(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    //throw new PendingException();
  }

  @Given("^a firstname \"([^\"]*)\"$")
  public void afirstname(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    //throw new PendingException();
  }

  @Given("^a lastname \"([^\"]*)\"$")
  public void alastname(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    //throw new PendingException();
  }

  @Given("^a Searcher with firstname \"([^\"]*)\"$")
  public void aSearcher_with_firstname(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    //throw new PendingException();
  }

  @Given("^buddyUp is selected$")
  public void buddyup_is_selected() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    //throw new PendingException();
  }

  @Then("^buddyUp should be true$")
  public void buddyup_should_be_true() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    //throw new PendingException();
  }

}
