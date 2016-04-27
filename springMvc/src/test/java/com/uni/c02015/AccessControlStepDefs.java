
package com.uni.c02015;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.uni.c02015.domain.User;
import com.uni.c02015.persistence.repository.LandlordRepository;
import com.uni.c02015.persistence.repository.MessageRepository;
import com.uni.c02015.persistence.repository.RoleRepository;
import com.uni.c02015.persistence.repository.SearcherRepository;
import com.uni.c02015.persistence.repository.UserRepository;
import com.uni.c02015.persistence.repository.property.PropertyRepository;

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

import javax.servlet.Filter;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@WebAppConfiguration
@ContextConfiguration(classes = {SpringMvc.class, SecurityConfig.class, WebConfig.class})
public class AccessControlStepDefs {

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

  //Creates an authentication token using username, password and role
  @Given("^I am a \"([^\"]*)\" with username \"([^\"]*)\" and password \"([^\"]*)\"$")
  public void iamawith_username_and_password(String arg1, String arg2, String arg3)
          throws Throwable {
    authentication = new UsernamePasswordAuthenticationToken(arg2, arg3,
            AuthorityUtils.createAuthorityList("ROLE_" + arg1));
  }

  //When you enter credentials in login
  @When("^I access \"([^\"]*)\"$")
  public void access(String arg1) throws Throwable {
    result = mockMvc.perform(get(arg1).with(authentication(authentication)));
  }

  //Checks your role
  @Then("^My authentication is true with role \"([^\"]*)\"$")
  public void myauthentication_is_true_with_role(String arg1) throws Throwable {
    result.andExpect(authenticated().withRoles(arg1));
  }

  //If not authorized you will be redirected to login
  @Then("^My authentication is false with role \"([^\"]*)\"$")
  public void myauthentication_is_false_with_role(String arg1) throws Throwable {
    result.andExpect(redirectedUrl("https://localhost/login-form"));
  }

  @Given("^I am an authenticated \"([^\"]*)\" with username \"([^\"]*)\"$")
  public void authenticated_with_username(String arg1, String arg2) throws Throwable {
    authentication = new UsernamePasswordAuthenticationToken(arg2, null,
            AuthorityUtils.createAuthorityList("ROLE_" + arg1));
  }

  /**
   * Checks authorization.
   */
  @Then("^My authentication is <isAuth> with role \"([^\"]*)\"$")
  public void myauthentication_is_isAuth_with_role(boolean arg1, String arg2) throws Throwable {
    if (arg1) {
      result.andExpect(authenticated().withRoles(arg2));
    } else {
      result.andExpect(status().is3xxRedirection());
    }
  }

  @Given("^I am an authenticated user with \"([^\"]*)\"$")
  public void am_an_authenticated_user_with(String arg1) throws Throwable {
    authentication = new UsernamePasswordAuthenticationToken("majid", null,
            AuthorityUtils.createAuthorityList("ROLE_" + arg1));
  }
  
  /**
   * Go to the user's homepage.
   */
  @When("^I go to my specific homepage$")
  public void go_to_my_specific_homepage() throws Throwable {
    if (authentication.getAuthorities().equals("ROLE_LANDLORD")) {
      result = mockMvc.perform(get("landlord/index").with(authentication(authentication)));
    } else {
      result = mockMvc.perform(get("searcher/index").with(authentication(authentication)));
    }
  }

  /**
   * Check that the user is ont the specific page.
   * @param arg1 The type of page the user should be on.
   */
  @Then("^I should be on a \"([^\"]*)\" specific homepage\\.$")
  public void should_be_on_a_specific_homepage(String arg1) throws Throwable {
    if (authentication.getAuthorities().equals("ROLE_LANDLORD")) {
      result.andExpect(redirectedUrl("https://localhostlandlord/index"));
    } else {
      result.andExpect(redirectedUrl("https://localhostsearcher/index"));
    }
  }
}
