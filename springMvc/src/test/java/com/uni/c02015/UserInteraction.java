package com.uni.c02015;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


import com.uni.c02015.domain.*;
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
@ContextConfiguration(classes = {SpringMvc.class, SecurityConfig.class, WebConfig.class})
public class UserInteraction {

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
  private User user1, user2;
  private Searcher searcher;
  private Landlord landlord;
  Message message = new Message();

  
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
    this.mockMvc = MockMvcBuilders
            .webAppContextSetup(this.wac)
           .addFilters(springSecurityFilterChain)
            .apply(springSecurity())
            .build();
    
    //Set up Roles
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
  
  @After
  public void delete() {
	  messageRepository.deleteAll();
	  searcherRepository.deleteAll();
	  landlordRepository.deleteAll();
	  userRepository.deleteAll();
	  roleRepository.deleteAll();
  }

  @Given("^\"([^\"]*)\" is a landlord$")
  public void is_a_landlord(String arg1) throws Throwable {
	User user2 = userRepository.findByLogin(arg1);
	user2.setRole(roleRepository.findByRole("landlord"));
  }

  @Given("^a user called \"([^\"]*)\"$")
  public void a_user_called(String arg1) throws Throwable {
      user2 = new User();
      user2.setLogin(arg1);
      userRepository.save(user2);
  }
  
  @Given("^I am a searcher \"([^\"]*)\"$")
  public void i_am_a_searcher(String arg1) throws Throwable {
	  user2 = userRepository.findByLogin(arg1);
      Role role = roleRepository.findByRole("SEARCHER");
      user2.setRole(role);
	  searcher.setFirstName(arg1);
      searcher.setId(user2.getId());
      searcherRepository.save(searcher);
      userRepository.save(user2);
  }

  @When("^I send a message \"([^\"]*)\" to a landlord \"([^\"]*)\"$")
  public void i_send_a_message_to_a_landlord(String arg1, String arg2) throws Throwable {
      landlord.setFirstName(arg2);
      result = this.mockMvc.perform(post("https://localhost:8070/messaging/sendMessage")
            .param("receiver", landlord.getFirstName())
            .param("subject", "test")
            .param("message", arg1)
            .with(authentication(new UsernamePasswordAuthenticationToken(user2.getLogin(),"", AuthorityUtils.createAuthorityList("ROLE_SEARCHER"))))
            .with(csrf())
            );
  }

  @Then("^\"([^\"]*)\" should receive the message \"([^\"]*)\"$")
  public void should_receive_the_message(String arg1, String arg2) throws Throwable {
    Message message = messageRepository.findByMessage(arg2);
    Assert.assertEquals(message.getReceiver().getLogin(), arg1);
  }

  @When("^I receive a message \"([^\"]*)\" from a user \"([^\"]*)\"$")
  public void i_receive_a_message_from_a_user(String arg1, String arg2) throws Throwable {
	  
	  message.setMessage(arg1);
	  message.setSender(userRepository.findByLogin(arg2));
	  message.setReceiver(user2);
	  messageRepository.save(message);
      
  }

  @Then("^I should be able to reply with \"([^\"]*)\"$")
  public void ierror: The following untracked working tree files would be overwritten by merge:
    springMvc/.classpath
    springMvc/.settings/gradle.prefs
_should_be_able_to_reply_with_from_my_inbox(String arg1) throws Throwable {
      
    Message messageReply = new Message();
    messageReply.setMessage(arg1);
    messageReply.setSender(message.getReceiver());
    messageReply.setReceiver(message.getSender());
  }

  @Given("^the user \"([^\"]*)\" is a landlord$")
  public void the_user_is_a_landlord(String arg1) throws Throwable {
     
     User user = new User();
     user.setLogin(arg1);
     user.setRole(roleRepository.findByRole("Landlord"));
     userRepository.save(user);
  }

  @Given("^the user \"([^\"]*)\" is the owner of the property$")
  public void the_user_is_the_owner_of_the_property(String arg1) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      
  }

  @When("^I like a property \"([^\"]*)\"$")
  public void i_like_a_property(String arg1) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      
  }

  @Then("^user \"([^\"]*)\" receives a notification that user \"([^\"]*)\" liked his property$")
  public void user_receives_a_notification_that_user_liked_his_property(String arg1, String arg2) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      
  }

  @Given("^I am an administator \"([^\"]*)\"$")
  public void i_am_an_administator(String arg1) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      
  }

  @When("^I broadcast a message \"([^\"]*)\"$")
  public void i_broadcast_a_message(String arg1) throws Throwable {
      // Write coerror: The following untracked working tree files would be overwritten by merge:
    springMvc/.classpath
    springMvc/.settings/gradle.prefs
de here that turns the phrase above into concrete actions
      
  }

  @Then("^users \"([^\"]*)\" and \"([^\"]*)\" should receive the message \"([^\"]*)\"$")
  public void users_and_should_receive_the_message(String arg1, String arg2, String arg3) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      
  }

  @Given("^I am a registered searcher \"([^\"]*)\"$")
  public void i_am_a_registered_searcher(String arg1) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      
  }

  @When("^I have dealt with a landlord \"([^\"]*)\"$")
  public void i_have_dealt_with_a_landlord(String arg1) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      
  }

  @Then("^I should be able to leave feedback: \"([^\"]*)\"$")
  public void i_should_be_able_to_leave_feedback(String arg1) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      
  }

  @Then("^rate him \"([^\"]*)\" stars$")
  public void rate_him_stars(String arg1) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      
  }

  @Given("^I am a registered user \"([^\"]*)\"$")
  public void i_am_a_registered_user(String arg1) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      
  }

  @Given("^I am logged in$")
  public void i_am_logged_in() throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      
  }

  @Given("^I am a searcher$")
  public void i_am_a_searcher() throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      
  }

  @When("^I view a property$")
  public void i_view_a_property() throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      
  }

  @Then("^I should be able to express interest$")
  public void i_should_be_able_to_express_interest() throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      
  }

  @Given("^I am a landlord \"([^\"]*)\"$")
  public void i_am_a_landlord(String arg1) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      
  }

  @When("^a searcher Bob expresses interest$")
  public void a_searcher_Bob_expresses_interest() throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      
  }

  @When("^a searcher Sarah expresses interest$")
  public void a_searcher_Sarah_expresses_interest() throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      
  }

  @When("^a searcher Jacob expresses interest$")
  public void a_searcher_Jacob_expresses_interest() throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      
  }

  @When("^a searcher Kim expresses interest$")
  public void a_searcher_Kim_expresses_interest() throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      
  }

  @When("^a landlord \"([^\"]*)\" makes an inaccurate property listing$")
  public void a_landlord_makes_an_inaccurate_property_listing(String arg1) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      
  }

  @Then("^I should be able to report them$")
  public void i_should_be_able_to_report_them() throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      
  }

  @Then("^the administrator \"([^\"]*)\" should be notified$")
  public void the_administrator_should_be_notified(String arg1) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      
  }

  @When("^a searcher \"([^\"]*)\" verbally abuses me over the messaging system$")
  public void a_searcher_verbally_abuses_me_over_the_messaging_system(String arg1) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      
  }

  @Then("^I should be able to report searcher \"([^\"]*)\"$")
  public void i_should_be_able_to_report_searcher(String arg1) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      
  }



}
