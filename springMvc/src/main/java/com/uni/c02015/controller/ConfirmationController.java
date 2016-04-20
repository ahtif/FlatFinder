package com.uni.c02015.controller;

import com.uni.c02015.SpringMvc;
import com.uni.c02015.domain.Landlord;
import com.uni.c02015.domain.Searcher;
import com.uni.c02015.domain.TokenType;
import com.uni.c02015.domain.User;
import com.uni.c02015.domain.VerificationToken;
import com.uni.c02015.persistence.repository.LandlordRepository;
import com.uni.c02015.persistence.repository.RoleRepository;
import com.uni.c02015.persistence.repository.SearcherRepository;
import com.uni.c02015.persistence.repository.UserRepository;
import com.uni.c02015.persistence.repository.VerificationTokenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.util.Calendar;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Controller
public class ConfirmationController {

  
  
  public static final String SIGN_UP_ID_SESSION = "signUpID";

  @Autowired
  private UserRepository userRepo;
  @Autowired
  private VerificationTokenRepository tokenRepo;

  // Email regex and pattern
  private final String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
      + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
  private Pattern emailPattern = Pattern.compile(emailRegex);

  @ModelAttribute("User")
  public User getUser() {

    return new User();
  }
  
  /**
   * Send an email message to the user
   * @param user The user to send the message to.
   * @param address The email address of the user.
   */
  public void sendConfirmationEmail(User user,
      String address) {
    
    String tokenId = UUID.randomUUID().toString();
    
    VerificationToken token = new VerificationToken(tokenId, user, TokenType.ACTIVATION);
    tokenRepo.save(token);
    
    String subject = "Please confirm your account at FlatFinder";
    
    String messageBody = "In order to activate your account at FlatFinder,"
        + " please click the following link: <br />"
        + "<a href='https://localhost:8070/confirm/" + tokenId + "'>Activate your account</a>";
    
    // Recipient's email ID needs to be mentioned.
    String to = address;

    // Sender's email ID needs to be mentioned
    String from = "confirmations@flatfinder.com";

    // Assuming you are sending email from localhost
    String host = "localhost";

    // Get system properties
    Properties properties = System.getProperties();

    // Setup mail server
    properties.setProperty("mail.smtp.host", host);

    // Get the default Session object.
    Session session = Session.getDefaultInstance(properties);

    try {
      // Create a default MimeMessage object.
      MimeMessage message = new MimeMessage(session);

      // Set From: header field of the header.
      message.setFrom(new InternetAddress(from));

      // Set To: header field of the header.
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

      // Set Subject: header field
      message.setSubject(subject);

      // Send the actual HTML message, as big as you like
      message.setContent(messageBody, "text/html");

      // Send message
      Transport.send(message);
      System.out.println("Sent message successfully....");
    } catch (MessagingException mex) {
      mex.printStackTrace();
    }
  }
  
  /**
   * Confirm a user's account.
   * @param confirmId The unique confirmation ID.
   */
  @RequestMapping("/confirm/{confirmId}")
  public String confirmAccount(@PathVariable String confirmId) {
    Calendar cal = Calendar.getInstance();
    VerificationToken token = tokenRepo.findByToken(confirmId);
    if (token == null || token.getType() != TokenType.ACTIVATION 
        || token.getExpiryDate().getTime() - cal.getTime().getTime() <= 0 
        || token.isUsed()) {
      return "redirect:/confirm/notConfirmed";
    }
   
    User user = token.getUser();
    token.setUsed(true);
    user.setConfirmed(true);
    userRepo.save(user);
    tokenRepo.save(token);
    return "redirect:/confirm/confirmed";
  }
  /**
   * Take in the user's login, check if their account is not already activated and 
   * send a new activation email.
   * @param username The username of the user.
   */
  
  @RequestMapping("/confirm/sendNewEmail")
  public String resendConfirmation(@RequestParam("login")String username) {
    User user = userRepo.findByLogin(username);
    if (user != null && !user.getConfirmed()) {
      sendConfirmationEmail(user, user.getEmailAddress());
    }
    if (user != null && user.getConfirmed()) {
      return "redirect:/confirm/newEmail?activated=true";
    }
    return "redirect:/confirm/newEmail?submitted=true";
  }
  
  /**
   * Redirect the user to the email confirmed page, and add any GET parameters
   * to the page.
   * @param request The HTTP request.
   */
  @RequestMapping("/confirm/confirmed")
  public ModelAndView emailConfirmed(HttpServletRequest request) {
    // Get the request GET parameters
    Map<String, String[]> parameters = request.getParameterMap();

    // Create the model and view and add the GET parameters as object in the model
    ModelAndView modelAndView = new ModelAndView("/confirm/email-confirmed");
    modelAndView.addAllObjects(parameters);

    return modelAndView;
  }
  
  /**
   * Redirect the user to the email confirmed page, and add any GET parameters
   * to the page.
   * @param request The HTTP request.
   */
  @RequestMapping("/confirm/newEmail")
  public ModelAndView sendNewEmail(HttpServletRequest request) {
    // Get the request GET parameters
    Map<String, String[]> parameters = request.getParameterMap();

    // Create the model and view and add the GET parameters as object in the model
    ModelAndView modelAndView = new ModelAndView("/confirm/new-email");
    modelAndView.addAllObjects(parameters);

    return modelAndView;
  }
  
  @RequestMapping("/confirm/email")
  public String confirmEmail() {
    return "/confirm/confirm-email";
  }

  
  @RequestMapping("/confirm/notConfirmed")
  public String notConfirmed() {
    return "/confirm/not-confirmed";
  }
  

  
  
  
  
  
}
