package com.uni.c02015;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AccessControlStepDefs {

  @Autowired
  private WebApplicationContext wac;
  @Autowired
  private Filter springSecurityFilterChain;

  private MockMvc mockMvc;
  private ResultActions result;
  private Authentication authentication;

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders
            .webAppContextSetup(this.wac)
            .addFilters(springSecurityFilterChain)
            .apply(springSecurity())
            .build();
  }

  @Given("^I am a \"([^\"]*)\" with username \"([^\"]*)\" and password \"([^\"]*)\"$")
  public void i_am_a_with_username_and_password(String arg1, String arg2, String arg3) throws Throwable {
    authentication = new UsernamePasswordAuthenticationToken(arg2, arg3,
            AuthorityUtils.createAuthorityList("ROLE_" + arg1));
  }


  @When("^I access \"([^\"]*)\"$")
  public void i_access(String arg1) throws Throwable {
    result = mockMvc.perform(get(arg1).with(authentication(authentication)));
  }

  @Then("^My authentication is true with role \"([^\"]*)\"$")
  public void my_authentication_is_true_with_role(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    result.andExpect(authenticated().withRoles(arg1));
  }

  @Then("^My authentication is false with role \"([^\"]*)\"$")
  public void my_authentication_is_false_with_role(String arg1) throws Throwable {
    result.andExpect(redirectedUrl("https://localhost/login-form"));
  }

  @Given("^I am an authenticated \"([^\"]*)\" with username \"([^\"]*)\"$")
  public void i_am_an_authenticated_with_username(String arg1, String arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    //throw new PendingException();
  }

  @Then("^My authentication is <isAuth> with role \"([^\"]*)\"$")
  public void my_authentication_is_isAuth_with_role(String arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    //throw new PendingException();
  }

}
