package com.uni.c02015;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;

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
  private User user1, user2,user4;
  private Searcher searcher;
  private Landlord landlord;
  Message message = new Message();
  Message message2 = new Message();

  
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
  public void i_should_be_able_to_reply_with_from_my_inbox(String arg1) throws Throwable {
      
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


}
