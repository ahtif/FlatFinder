package com.uni.c02015;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;


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

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import javax.servlet.Filter;

@WebAppConfiguration
@ContextConfiguration(classes = {SpringMvc.class, SecurityConfig.class, WebConfig.class})
public class AccessControlStepDefs {

  @Autowired
  private WebApplicationContext wac;
  @Autowired
  private Filter springSecurityFilterChain;

  private MockMvc mockMvc;
  private ResultActions result;
  private Authentication authentication;

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
  //Creates an authentication token using username, password and role
  @Given("^I am a \"([^\"]*)\" with username \"([^\"]*)\" and password \"([^\"]*)\"$")
  public void iam_a_with_username_and_password(String arg1, String arg2, String arg3) throws Throwable {
    authentication = new UsernamePasswordAuthenticationToken(arg2, arg3,AuthorityUtils.createAuthorityList("ROLE_" + arg1));
  }
  //When you enter credentials in login
  @When("^I access \"([^\"]*)\"$")
  public void access(String arg1) throws Throwable {
    result = mockMvc.perform(get(arg1).with(authentication(authentication)));
  }
  //Checks your role
  @Then("^My authentication is true with role \"([^\"]*)\"$")
  public void my_authentication_is_true_with_role(String arg1) throws Throwable {
    result.andExpect(authenticated().withRoles(arg1));
  }
  //If not authorized you will be redirected to login
  @Then("^My authentication is false with role \"([^\"]*)\"$")
  public void my_authentication_is_false_with_role(String arg1) throws Throwable {
    result.andExpect(redirectedUrl("https://localhost/login-form"));
  }
  //Need to implement these
  @Given("^I am an authenticated \"([^\"]*)\" with username \"([^\"]*)\"$")
  public void authenticated_with_username(String arg1, String arg2) throws Throwable {
  }
  //Need to implement these
  @Then("^My authentication is <isAuth> with role \"([^\"]*)\"$")
  public void my_authentication_is_isAuth_with_role(String arg1) throws Throwable {

  }

}
