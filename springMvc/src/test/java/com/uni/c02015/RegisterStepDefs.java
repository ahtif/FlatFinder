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

import cucumber.api.java.After;
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

    try {
      userRepository.deleteAll();
    } catch (Exception e) {
      System.out.println("Cant delete repository @BEFORE");
    }

    user = new User();
    landlord = new Landlord();
    searcher = new Searcher();
  }
  
  // @After
  // public void after() {
  //   userRepository.deleteAll();
  // }

 

  @Given("^I am a user with username \"([^\"]*)\"$")
  public void iama_user_with_username(String arg1) throws Throwable {
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
   * When users presses create account.
   */
  @When("^I press create account$")
  public void ipress_create_account() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    //result = mockMvc.perform(post("/createAccount")
    //        .param("login", user.getLogin())
    //       .param("password", user.getPassword())
    //        .param("role", user.getRole().toString()));

    //Not sure about this
    //userRepository.save(user);
  }

  /**
   * Username should be saved in repository.
   */
  @Then("^the system stores the User with username \"([^\"]*)\"$")
  public void thesystem_stores_the_User_with_username(String arg1) throws Throwable {
    //
    // User user = userRepository.findByLogin(arg1);
    //
    // Assert.assertEquals(arg1, user.getLogin());
  }

  @Then("^passwords \"([^\"]*)\"$")
  public void passwords(String arg1) throws Throwable {

    // Write code here that turns the phrase above into concrete actions.
   /*boolean found = false;
    for (User user : userRepository.findAll()) {
      if (user.getPassword().equals(arg1)) {
        found = true;
      }
    }
    assertThat(found, is(true));*/
  }

  @Then("^user type \"([^\"]*)\"$")
  public void user_type(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
   /*boolean found = false;
    for (User user : userRepository.findAll()) {
      if (user.getRole().getRole().equals(arg1)) {
        found = true;
      }
    }
    assertThat(found, is(true));*/
  }

  @Given("^I am a user with username \"([^\"]*)\"$")
  public void iam_a_user_with_username(String arg1) throws Throwable {
    //user.setLogin(arg1);
  }

  @Given("^confirmed password \"([^\"]*)\"$")
  public void confirmed_password(String arg1) throws Throwable {
    //user.setPassword(arg1);
  }

  @Then("^the system should redirect me to \"([^\"]*)\"$")
  public void thesystem_should_redirect_me_to(String arg1) throws Throwable {
//    result.andExpect(view().name(arg1));
  }

  @Given("^a username \"([^\"]*)\"$")
  public void ausername(String arg1) throws Throwable {
    user.setLogin(arg1);
  }

  @Then("^the system stores the User with username \"([^\"]*)\"$")
  public void thesystem_stores_the_User_with_username(String arg1) throws Throwable {
    Assert.assertEquals(arg1, user.getLogin());
  }

  @Then("^passwords \"([^\"]*)\"$")
  public void passwords(String arg1) throws Throwable {
    Assert.assertEquals(arg1, user.getPassword());
  }

  @Then("^user type \"([^\"]*)\"$")
  public void usertype(String arg1) throws Throwable {
    //Assert.assertEquals(arg1, user.getRole().getRole());
  }

   /**
   * When Landlord submits form.
   */
  @When("^a Landlord submit the form$")
  public void alandlord_submit_the_form() throws Throwable {
    result = this.mockMvc.perform(post("/addLandlord")
            .param("firstname", landlord.getFirstName())
            .param("lastname", landlord.getLastName())
            .param("emailAddress", landlord.getEmailAddress())
    );
  }

  @Then("^the user is redirect to the login page$")
  public void theuser_is_redirect_to_the_login_page() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
  }

  @Then("^the system stores the Landlord with firstname \"([^\"]*)\"$")
  public void thesystem_stores_the_Landlord_with_firstname(String arg1) throws Throwable {
    Assert.assertEquals(arg1, landlord.getFirstName());
  }

  @Then("^a Landlord with lastname \"([^\"]*)\"$")
  public void alandlord_with_lastname(String arg1) throws Throwable {
    landlord.setLastName(arg1);
  }

  @Then("^a Landlord with email \"([^\"]*)\"$")
  public void alandlord_with_email(String arg1) throws Throwable {
    landlord.setEmailAddress(arg1);
  }

  @Given("^a Searcher with firstname \"([^\"]*)\"$")
  public void asearcher_with_firstname(String arg1) throws Throwable {
    searcher.setFirstName(arg1);
  }

  @Given("^a Searcher with lastname \"([^\"]*)\"$")
  public void asearcher_with_lastname(String arg1) throws Throwable {
    searcher.setLastName(arg1);
  }

  @Given("^a Searcher with email \"([^\"]*)\"$")
  public void asearcher_with_email(String arg1) throws Throwable {
    searcher.setEmailAddress(arg1);
  }

  @Given("^buddyUp is selected$")
  public void buddyup_is_selected() throws Throwable {
    searcher.setBuddyPref(true);
  }
  
  /**
   * Searcher submits the form.
   */
  @When("^a Searcher submit the form$")
  public void asearcher_submit_the_form() throws Throwable {
    result = this.mockMvc.perform(post("/addSearcher")
            .param("firstname", searcher.getFirstName())
            .param("lastname", searcher.getLastName())
            .param("emailAddress", searcher.getEmailAddress())
            .param("buddyPref", Boolean.toString(searcher.getBuddyPref()))
    );
  }

  @Then("^the system stores the Searcher with firstname \"([^\"]*)\"$")
  public void thesystem_stores_the_Searcher_with_firstname(String arg1) throws Throwable {
    Assert.assertEquals(arg1, searcher.getFirstName());
  }

  @Then("^a Searcher with \"([^\"]*)\"$")
  public void asearcher_with(String arg1) throws Throwable {
    Assert.assertEquals(arg1, searcher.getLastName());
  }

  @Then("^buddyUp should be true$")
  public void buddyup_should_be_true() throws Throwable {
    Assert.assertEquals(true, searcher.getBuddyPref());
  }

  @When("^a Landlord press create account$")
  public void alandlord_press_create_account() throws Throwable {
    userRepository.save(user);
  }

  @Given("^a Landlord with firstname \"([^\"]*)\"$")
  public void alandlord_with_firstname(String arg1) throws Throwable {
    landlord.setFirstName(arg1);
  }
}
