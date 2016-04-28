package com.uni.c02015;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.uni.c02015.domain.Landlord;
import com.uni.c02015.domain.Searcher;
import com.uni.c02015.domain.User;
import com.uni.c02015.persistence.repository.LandlordRepository;
import com.uni.c02015.persistence.repository.RoleRepository;
import com.uni.c02015.persistence.repository.SearcherRepository;
import com.uni.c02015.persistence.repository.UserRepository;
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
import cucumber.api.java.en.When;

import javax.servlet.Filter;

@WebAppConfiguration
@ContextConfiguration(classes = {SpringMvc.class, SecurityConfig.class, WebConfig.class})
public class LandlordStepDefs {

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
  private ResultActions resultActions;
  private Authentication authentication;

  private User user;
  private Landlord landlord;
  private Searcher searcher;

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
    landlord = new Landlord();
    searcher = new Searcher();
  }

  /**
   * Landlord login.
   * @param arg0 Landlord name
   * @throws Throwable On error
   */
  @Given("^I am logged in as a landlord \"([^\"]*)\"$")
  public void abc(String arg0) throws Throwable {

    user.setLogin(arg0);
    user.setPassword("password");
    user.setRole(roleRepository.findByRole(SpringMvc.ROLE_LANDLORD));
    user.setConfirmed(true);
    userRepository.save(user);

    authentication = new UsernamePasswordAuthenticationToken(
        arg0, "password",
        AuthorityUtils.createAuthorityList(SpringMvc.ROLE_LANDLORD));
  }

  @When("^I request the add property page \"([^\"]*)\"$")
  public void def(String arg0) throws Throwable {

    resultActions = mockMvc.perform(get(arg0).with(authentication(authentication)));
  }
}