package com.uni.c02015.controller;

import com.uni.c02015.domain.*;
import com.uni.c02015.persistence.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class RegistrationController {


  public static final String SIGN_UP_ID_SESSION;
  private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
      + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

  static {

    SIGN_UP_ID_SESSION = "signUpID";
  }

  @Autowired
  private UserRepository userRepo;
  @Autowired
  private RoleRepository roleRepo;
  @Autowired
  private SearcherRepository searcherRepo;
  @Autowired
  private LandlordRepository landlordRepo;
  @Autowired
  private VerificationTokenRepository tokenRepo;

  private Pattern emailPattern = Pattern.compile(EMAIL_REGEX);

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
   * Add a searcher.
   * @param request The request
   * @return String
   */
  @RequestMapping(value = "/addSearcher", method = RequestMethod.POST)
  public String addSearcher(
      @RequestParam(value = "firstName", required = true) String firstName,
      @RequestParam(value = "lastName", required = true) String lastName,
      @RequestParam(value = "buddyPref", required = true) boolean buddyPref,
      Model model,
      HttpServletRequest request) {

    String query = "";

    // First name is not set
    if (firstName.length() == 0) {

      query += "fNameLength=true";
    }

    // Last name is not set
    if (lastName.length() == 0) {

      if (query.length() > 0) {

        query += "&";
      }

      query += "lNameLength=true";
    }


    if (query.length() > 0) {

      return "redirect:/searcher/registration?" + query;
    }

    Searcher searcher = new Searcher(
        (Integer) request.getSession().getAttribute(SIGN_UP_ID_SESSION));
    
    searcher.setFirstName(firstName);
    searcher.setLastName(lastName);
    searcher.setBuddyPref(buddyPref);
    searcherRepo.save(searcher);
    
    // Remove the sign up session
    request.getSession().removeAttribute(SIGN_UP_ID_SESSION);

    return "redirect:/";
  }

  /**
   * Add a landlord.
   * @param request The request
   * @return String
   */
  @RequestMapping(value = "/addLandlord", method = RequestMethod.POST)
  public String addLandlord(@RequestParam(value = "firstName", required = true) String firstName,
      @RequestParam(value = "lastName", required = true) String lastName,
      Model model,
      HttpServletRequest request) {

    String query = "";

    // First name is not set
    if (firstName.length() == 0) {

      query += "fNameLength=true";
    }

    // Last name is not set
    if (lastName.length() == 0) {

      if (query.length() > 0) {

        query += "&";
      }

      query += "lNameLength=true";
    }

    if (query.length() > 0) {

      return "redirect:/landlord/registration?" + query;
    }

    Landlord landlord = 
        new Landlord((Integer) request.getSession().getAttribute(SIGN_UP_ID_SESSION));

    landlord.setFirstName(firstName);
    landlord.setLastName(lastName);
    landlordRepo.save(landlord);
    
    
    
    // Remove the sign up session
    request.getSession().removeAttribute(SIGN_UP_ID_SESSION);



    return "redirect:/";
  }

  /**
   * Registration page for landlord.
   * @param request The request
   * @return ModelAndView
   */
  @RequestMapping(value = "/landlord/registration", method = RequestMethod.GET)
  public ModelAndView showLandlordRegistrationForm(HttpServletRequest request) {

    // Don't allow access unless the sign up session was set
    if (request.getSession().getAttribute(SIGN_UP_ID_SESSION) == null) {

      return new ModelAndView("redirect:/register");
    }

    // Get the request GET parameters
    Map<String, String[]> parameters = request.getParameterMap();

    // Create the model and view and add the GET parameters as object in the model
    ModelAndView modelAndView = new ModelAndView("landlord/registration-landlord", "Landlord",
        new Landlord((Integer) request.getSession().getAttribute(SIGN_UP_ID_SESSION)));
    modelAndView.addAllObjects(parameters);

    return modelAndView;
  }

  /**
   * Registration page for a landlord.
   * @param request The request
   * @return ModelAndView
   */
  @RequestMapping(value = "/searcher/registration", method = RequestMethod.GET)
  public ModelAndView showSearcherRegistrationForm(HttpServletRequest request) {

    // Don't allow access unless the sign up session was set
    if (request.getSession().getAttribute(SIGN_UP_ID_SESSION) == null) {

      return new ModelAndView("redirect:/register");
    }

    // Get the request GET parameters
    Map<String, String[]> parameters = request.getParameterMap();

    // Create the model and view and add the GET parameters as object in the model
    ModelAndView modelAndView = new ModelAndView("searcher/registration-searcher", "Searcher",
        new Searcher((Integer) request.getSession().getAttribute(SIGN_UP_ID_SESSION)));
    modelAndView.addAllObjects(parameters);

    return modelAndView;
  }

  /**
   * Register view.
   * @param request The request
   * @return ModelAndView
   */
  @RequestMapping("/register")
  public ModelAndView register(HttpServletRequest request) {

    // Get the request GET parameters
    Map<String, String[]> parameters = request.getParameterMap();

    // Create the model and view and add the GET parameters as object in the model
    ModelAndView modelAndView = new ModelAndView("register");
    modelAndView.addAllObjects(parameters);

    return modelAndView;
  }
  
  /**
   * Create the user from the details given and store it in the database.
   * @param username The username.
   * @param password The password.
   * @param role The account type
   */
  @RequestMapping(value = "/createAccount", method = RequestMethod.POST)
  public String assessRequest(
      @RequestParam(value = "login", required = true) String username,
      @RequestParam(value = "password", required = true) String password,
      @RequestParam(value = "cPassword", required = true) String confPassword,
      @RequestParam(value = "role", required = true) String role,
      @RequestParam(value = "emailAddress", required = false) String emailAddress,
      Model model,
      HttpServletRequest request) {
    
    String query = "";

    // Username length is invalid
    if (username.length() < 3 || username.length() > 15) {

      query += "usernameLength=true";

    // The username already exists
    } else if (username.length() >= 3 && userRepo.findByLogin(username) != null) {

      query += "usernameExists=true";
    }
    
    // Email regex matcher
    Matcher matcher = emailPattern.matcher(emailAddress);
    // Email address is not set
    if (emailAddress.length() == 0) {

      if (query.length() > 0) {

        query += "&";
      }

      query += "emailLength=true";

      // Email is not of the correct pattern
    } else if (!matcher.find()) {

      if (query.length() > 0) {

        query += "&";
      }

      query += "emailFormat=true";

    } else if (userRepo.findByEmailAddress(emailAddress) != null) {

      if (query.length() > 0) {

        query += "&";
      }

      query += "emailTaken=true";
    }

    // Password length is invalid
    if (password.length() < 8 || password.length() > 20) {

      if (query.length() > 0) {

        query += "&";
      }

      query += "passwordLength=true";

    // Password and confirm password is not equal
    } else if (!password.equals(confPassword)) {

      if (query.length() > 0) {

        query += "&";
      }

      query += "passwordMismatch=true";
    }

    if (query.length() > 0) {

      return "redirect:/register?" + query;
    }

    User user = new User();
    user.setLogin(username);
    user.setEmailAddress(emailAddress);
    user.setPassword(new BCryptPasswordEncoder().encode(password));
    user.setRole(roleRepo.findByRole(role));
    userRepo.save(user);

    // Set the sign up session
    request.getSession().setAttribute(SIGN_UP_ID_SESSION, user.getId());

    //Send the confirmation email
    sendConfirmationEmail(user, emailAddress);
    
    return "redirect:confirm/email";
  }
}