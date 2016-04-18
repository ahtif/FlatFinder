package com.uni.c02015;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.uni.c02015.domain.Landlord;
import com.uni.c02015.domain.Message;
import com.uni.c02015.domain.Role;
import com.uni.c02015.domain.Searcher;
import com.uni.c02015.domain.User;
import com.uni.c02015.persistence.repository.LandlordRepository;
import com.uni.c02015.persistence.repository.MessageRepository;
import com.uni.c02015.persistence.repository.RoleRepository;
import com.uni.c02015.persistence.repository.SearcherRepository;
import com.uni.c02015.persistence.repository.UserRepository;

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
import javax.swing.JOptionPane;
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
	        .addFilters(springSecurityFilterChain)
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
	    messageRepository.deleteAll();
	    searcherRepository.deleteAll();
	    landlordRepository.deleteAll();
	    userRepository.deleteAll();
	    roleRepository.deleteAll();
	  }
	  
	@Given("^a user \"([^\"]*)\"$")
	public void a_user(String arg1) throws Throwable {
		Role searcher = roleRepository.findByRole("SEARCHER");
		User user = new User(arg1, "", searcher);
	    userRepository.save(user);
	}
//Unimplemented
	@Given("^\"([^\"]*)\" is reported for malicious behaviour$")
	public void is_reported_for_malicious_behaviour(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I delete the user account \"([^\"]*)\"$")
	public void i_delete_the_user_account(String arg1) throws Throwable {
		
		
	    //userRepository.delete(arg1);
	}

	@Then("^user \"([^\"]*)\" is removed from the system$")
	public void user_is_removed_from_the_system(String arg1) throws Throwable {
	   
		
		
	}

	@When("^I located the user \"([^\"]*)\" on the delete user page$")
	public void i_located_the_user_on_the_delete_user_page(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^click \"([^\"]*)\"$")
	public void click(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I should be redirect to \"([^\"]*)\"$")
	public void i_should_be_redirect_to(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I suspend \"([^\"]*)\" for (\\d+) days$")
	public void i_suspend_for_days(String arg1, int arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^\"([^\"]*)\" is suspended for (\\d+) days$")
	public void is_suspended_for_days(String arg1, int arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^account status is changed to \"([^\"]*)\"$")
	public void account_status_is_changed_to(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I located the user \"([^\"]*)\" on the suspend user page$")
	public void i_located_the_user_on_the_suspend_user_page(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^enter (\\d+) days$")
	public void enter_days(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^\"([^\"]*)\" is already suspended for (\\d+) days$")
	public void is_already_suspended_for_days(String arg1, int arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I should be able to delete the user \"([^\"]*)\"$")
	public void i_should_be_able_to_delete_the_user(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^should be redirect to \"([^\"]*)\"$")
	public void should_be_redirect_to(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^a searcher \"([^\"]*)\" makes a report about a user \"([^\"]*)\"$")
	public void a_searcher_makes_a_report_about_a_user(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I should be notified$")
	public void i_should_be_notified() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I should be able to view their report$")
	public void i_should_be_able_to_view_their_report() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^a user \"([^\"]*)\" who is active for (\\d+) days$")
	public void a_user_who_is_active_for_days(String arg1, int arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I search for inactive users$")
	public void i_search_for_inactive_users() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^\"([^\"]*)\" should be displayed in the list of inactive users$")
	public void should_be_displayed_in_the_list_of_inactive_users(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^a user \"([^\"]*)\" who is inactive for (\\d+) days$")
	public void a_user_who_is_inactive_for_days(String arg1, int arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I suspend the user \"([^\"]*)\"$")
	public void i_suspend_the_user(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^the user account status is \"([^\"]*)\"$")
	public void the_user_account_status_is(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I located the user \"([^\"]*)\" on the inactive user page$")
	public void i_located_the_user_on_the_inactive_user_page(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I request the total number of users of the website$")
	public void i_request_the_total_number_of_users_of_the_website() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I should be able to view them$")
	public void i_should_be_able_to_view_them() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^only administrators should be able to view them$")
	public void only_administrators_should_be_able_to_view_them() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^a listing \"([^\"]*)\" by a Landlord \"([^\"]*)\"$")
	public void a_listing_by_a_Landlord(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^I receive a false listing report$")
	public void i_receive_a_false_listing_report() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I remove the listing \"([^\"]*)\"$")
	public void i_remove_the_listing(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I suspend landlord \"([^\"]*)\"$")
	public void i_suspend_landlord(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^the property \"([^\"]*)\" should be removed$")
	public void the_property_should_be_removed(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^\"([^\"]*)\" account status should be suspend$")
	public void account_status_should_be_suspend(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^\"([^\"]*)\" remove the property \"([^\"]*)\"$")
	public void remove_the_property(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^the report should be closed$")
	public void the_report_should_be_closed() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

}
