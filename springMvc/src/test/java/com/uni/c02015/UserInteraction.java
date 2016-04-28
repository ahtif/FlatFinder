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
import com.uni.c02015.persistence.repository.property.PropertyRepository;

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
  @Autowired
  private PropertyRepository propertyRepository;

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

  /**
   * saves a user that is a searcher to the userRepository.
   */
  @Given("^I am a Searcher \"([^\"]*)\"$")
  public void iam_a_searcher(String name) throws Throwable {
    Role searcher = roleRepository.findByRole("SEARCHER");
    User user = new User(name, "", searcher);
    userRepository.save(user);
  }

  /**
   * Create a landlord and save into the database.
   * @param name The name of the landlord.
   */
  @Given("a landlord \"([^\"]*)\"$")
  public void is_a_landlord(String name) throws Throwable {
    Role landlord = roleRepository.findByRole("LANDLORD");
    User user = new User(name, "", landlord);
    userRepository.save(user);
  }

  /**
   * a searcher sends a message to a landlord.
   */
  @When("^\"([^\"]*)\" sends a message \"([^\"]*)\" to"
      + " \"([^\"]*)\" with Subject \"([^\"]*)\"$")
  public void sends_a_message_to_with_Subject(String sender, String body, 
      String receiver, String subject) throws Throwable {
    
    message = new Message();
    message.setMessage(body);
    message.setSenderName(sender);

    result = this.mockMvc.perform(post("https://localhost:8070/messaging/sendMessage")
        .param("receiver", receiver)
        .param("subject", subject)
        .param("message", body)
        .param("sender", sender)
        .with(authentication(new UsernamePasswordAuthenticationToken(sender, "",
            AuthorityUtils.createAuthorityList("ROLE_SEARCHER"))))
        .with(csrf()));
  }
  
  /**
   * Assert that the user has received the message.
   * @param receiver The receiver of the message.
   * @param body The message body.
   * @param sender The sender of the message.
   */
  @Then("^\"([^\"]*)\" should receive the message \"([^\"]*)\" from \"([^\"]*)\"$")
  public void should_receive_the_message_from(String receiver, String body, String sender)
      throws Throwable {
    User send = userRepository.findByLogin(sender);
    User receive = userRepository.findByLogin(receiver);
    Message message = messageRepository.findByMessageAndSenderAndReceiver(body, send, receive);
    Assert.assertEquals(message.getMessage(), body);
    Assert.assertEquals(message.getSenderName(), sender);
    Assert.assertEquals(message.getReceiver().getLogin(), receiver);
  }
  
  /**
   * Creates a searcher and stores it into the database.
   * @param name The name of the searcher.
   */
  @Given("a searcher \"([^\"]*)\"$")
  public void is_a_searcher(String name) throws Throwable {
    Role searcherRole = roleRepository.findByRole("SEARCHER");
    User user = new User(name, "", searcherRole);
    userRepository.save(user);
    Searcher searcher = new Searcher(user.getId());
    searcher.setFirstName(name);
    searcher.setBuddyPref(true);
    searcherRepository.save(searcher);
  }
  
 
  /**
   * Create a new message with the specified data and store it in the database.
   * @param body The body of the message.
   * @param sender The sender of the message.
   * @param subject The subject of the message.
   * @param receiver The receiver of the message.
   */
  @Given("^a message \"([^\"]*)\" from \"([^\"]*)\" with Subject \"([^\"]*)\" to \"([^\"]*)\"$")
  public void message_from_with_Subject_to(String body, String sender,
      String subject, String receiver)throws Throwable {
    

    message = new Message();
    message.setMessage(body);
    message.setSubject(subject);
    message.setSenderName(sender);
    
    User send = userRepository.findByLogin(sender);
    User receive = userRepository.findByLogin(receiver);    
    
    message.setSender(send);
    message.setReceiver(receive);   
    messageRepository.save(message);
  }
  
  /**
   * Replying to a sent message.
   * @param body The message to reply with.
   */
  @When("^I reply with \"([^\"]*)\"$")
  public void reply_with(String body) throws Throwable {
    
    String senderName = message.getReceiver().getLogin();
    result = this.mockMvc.perform(post("https://localhost:8070/messaging/sendMessage")
        .param("receiver", message.getSenderName())
        .param("subject", "RE: " + message.getSubject())
        .param("message", body)
        .param("sender", senderName)
        .with(authentication(new UsernamePasswordAuthenticationToken(senderName, "",
            AuthorityUtils.createAuthorityList("ROLE_SEARCHER"))))
        .with(csrf()));
  }
  
  @Then("^the message \"([^\"]*)\" should be stored in the database$")
  public void the_message_should_be_stored_in_the_database(String arg1) throws Throwable {
    Assert.assertNotNull(messageRepository.findById(message.getId()));
  }
  
  @Then("^the message should not be sent$")
  public void message_should_not_be_sent() throws Throwable {
    Assert.assertNull(messageRepository.findById(message.getId()));
  }
  
  /**
   * Create an admin and save it to the database
   * @param name The name of the admin.
   */
  @Given("^I am an administrator \"([^\"]*)\"$")
  public void am_an_administrator(String name) throws Throwable {
    Role adminRole = roleRepository.findByRole("ADMINISTRATOR");
    admin = new User(name, "", adminRole);
    userRepository.save(admin);
  }

  /**
   * Allow a admin to broadcast a message.
   * @param body The body of message.
   * @param subject The subject of the message.
   */
  @When("^I broadcast a message \"([^\"]*)\" with subject \"([^\"]*)\"$")
  public void broadcast_a_message_with_subject(String body, String subject) throws Throwable {
    result = this.mockMvc.perform(post("https://localhost:8070/admin/broadcast/send")
        .param("subject", subject)
        .param("message", body)
        .param("sendTo", "all")
        .with(authentication(new UsernamePasswordAuthenticationToken(admin.getLogin(), "",
            AuthorityUtils.createAuthorityList("ROLE_ADMINISTRATOR"))))
        .with(csrf()));
  }

  /**
   * Verify that the users given haven received the message.
   * @param name1 The name of the first user.
   * @param name2 The name of the second user.
   * @param body The body of the message.
   */
  @Then("^users \"([^\"]*)\" and \"([^\"]*)\" should receive the message \"([^\"]*)\"$")
  public void users_and_should_receive_the_message(String name1,
      String name2, String body) throws Throwable {
    User user1 = userRepository.findByLogin(name1);
    User user2 = userRepository.findByLogin(name2);
    Assert.assertNotNull(messageRepository.findByMessageAndReceiver(body, user1));
    Assert.assertNotNull(messageRepository.findByMessageAndReceiver(body, user2));
  }





  //
  // /**
  // * sets a user with a username.
  // */
  // @Given("^a user called \"([^\"]*)\"$")
  // public void auser_called(String arg1) throws Throwable {
  // user2 = new User();
  // user2.setLogin(arg1);
  // userRepository.save(user2);
  // }
  //
  // /**
  // * saves a user that is a searcher to the userRepository.
  // */
  // @Given("^I am a searcher \"([^\"]*)\"$")
  // public void iam_a_searcher(String arg1) throws Throwable {
  // user2 = userRepository.findByLogin(arg1);
  // Role role = roleRepository.findByRole("SEARCHER");
  // user2.setRole(role);
  // searcher.setFirstName(arg1);
  // searcher.setId(user2.getId());
  // searcherRepository.save(searcher);
  // userRepository.save(user2);
  // }
  //

  //
  // @Then("^\"([^\"]*)\" should receive the message \"([^\"]*)\"$")
  // public void should_receive_the_message(String arg1, String arg2) throws
  // Throwable {
  // Message message = messageRepository.findByMessage(arg2);
  // Assert.assertEquals(message.getReceiver().getLogin(), arg1);
  // }
  //
  // /**
  // * A user receives a message from another user.
  // */
  // @When("^I receive a message \"([^\"]*)\" from a user \"([^\"]*)\"$")
  // public void ireceive_a_message_from_a_user(String arg1, String arg2) throws
  // Throwable {
  // message.setMessage(arg1);
  // message.setSender(userRepository.findByLogin(arg2));
  // message.setReceiver(user2);
  // messageRepository.save(message);
  //
  // }
  //
  // /**
  // * Replying to a message.
  // */
  // @Then("^I should be able to reply with \"([^\"]*)\"$")
  // public void ishould_be_able_to_reply_with_from_my_inbox(String arg1) throws
  // Throwable {
  // Message messageReply = new Message();
  // messageReply.setMessage(arg1);
  // messageReply.setSender(message.getReceiver());
  // messageReply.setReceiver(message.getSender());
  // }
  //
  // /**
  // * Set a user to be a landlord.
  // */
  // @Given("^the user \"([^\"]*)\" is a landlord$")
  // public void the_user_is_a_landlord(String arg1) throws Throwable {
  // User user = new User();
  // user.setLogin(arg1);
  // user.setRole(roleRepository.findByRole("Landlord"));
  // userRepository.save(user);
  // }
  //
  // @Given("^the user \"([^\"]*)\" is the owner of the property$")
  // public void the_user_is_the_owner_of_the_property(String arg1) throws
  // Throwable {
  // // Write code here that turns the phrase above into concrete actions
  //
  // }
  //
  // @When("^I like a property \"([^\"]*)\"$")
  // public void ilike_a_property(String arg1) throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  //
  // }
  //
  // @Then("^user \"([^\"]*)\" receives a notification that user \"([^\"]*)\"
  // liked his property$")
  // public void
  // user_receives_a_notification_that_user_liked_his_property(String arg1,
  // String arg2)
  // throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  //
  // }
  //
  // @Given("^I am an administator \"([^\"]*)\"$")
  // public void iam_an_administator(String arg1) throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  //
  // }
  //
  // @When("^I broadcast a message \"([^\"]*)\"$")
  // public void ibroadcast_a_message(String arg1) throws Throwable {
  //
  // }
  //
  // @Then("^users \"([^\"]*)\" and \"([^\"]*)\" should receive the message
  // \"([^\"]*)\"$")
  // public void users_and_should_receive_the_message(String arg1, String arg2,
  // String arg3)
  // throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  //
  // }
  //
  // @Given("^I am a registered searcher \"([^\"]*)\"$")
  // public void iam_a_registered_searcher(String arg1) throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  //
  // }
  //
  // @When("^I have dealt with a landlord \"([^\"]*)\"$")
  // public void ihave_dealt_with_a_landlord(String arg1) throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  //
  // }
  //
  // @Then("^I should be able to leave feedback: \"([^\"]*)\"$")
  // public void ishould_be_able_to_leave_feedback(String arg1) throws Throwable
  // {
  // // Write code here that turns the phrase above into concrete actions
  //
  // }
  //
  // @Then("^rate him \"([^\"]*)\" stars$")
  // public void rate_him_stars(String arg1) throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  //
  // }
  //
  // @Given("^I am a registered user \"([^\"]*)\"$")
  // public void iam_a_registered_user(String arg1) throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  //
  // }
  //
  // @Given("^I am logged in$")
  // public void iam_logged_in() throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  //
  // }
  //
  // @When("^I view a property$")
  // public void iview_a_property() throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  //
  // }
  //
  // @Then("^I should be able to express interest$")
  // public void ishould_be_able_to_express_interest() throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  //
  // }
  //
  // @Given("^I am a landlord \"([^\"]*)\"$")
  // public void iam_a_landlord(String arg1) throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  //
  // }
  //
  // @When("^a searcher Bob expresses interest$")
  // public void asearcher_Bob_expresses_interest() throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  //
  // }
  //
  // @When("^a searcher Sarah expresses interest$")
  // public void asearcher_Sarah_expresses_interest() throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  //
  // }
  //
  // @When("^a searcher Jacob expresses interest$")
  // public void asearcher_Jacob_expresses_interest() throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  //
  // }
  //
  // @When("^a searcher Kim expresses interest$")
  // public void asearcher_Kim_expresses_interest() throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  //
  // }
  //
  // @When("^a landlord \"([^\"]*)\" makes an inaccurate property listing$")
  // public void alandlord_makes_an_inaccurate_property_listing(String arg1)
  // throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  //
  // }
  //
  // @Then("^I should be able to report them$")
  // public void ishould_be_able_to_report_them() throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  //
  // }
  //
  // @Then("^the administrator \"([^\"]*)\" should be notified$")
  // public void the_administrator_should_be_notified(String arg1) throws
  // Throwable {
  // // Write code here that turns the phrase above into concrete actions
  //
  // }
  //
  // @When("^a searcher \"([^\"]*)\" verbally abuses me over the messaging
  // system$")
  // public void asearcher_verbally_abuses_me_over_the_messaging_system(String
  // arg1) throws Throwable {
  // // Write code here that turns the phrase above into concrete actions
  //
  // }
  //
  // @Then("^I should be able to report searcher \"([^\"]*)\"$")
  // public void ishould_be_able_to_report_searcher(String arg1) throws
  // Throwable {
  // // Write code here that turns the phrase above into concrete actions
  //
  // }

}
