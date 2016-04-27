package com.uni.c02015.controller;

import com.uni.c02015.domain.TokenType;
import com.uni.c02015.domain.User;
import com.uni.c02015.domain.VerificationToken;
import com.uni.c02015.persistence.repository.UserRepository;
import com.uni.c02015.persistence.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

@Controller
public class PasswordController {

  @Autowired
  private UserRepository userRepo;
  @Autowired
  private VerificationTokenRepository tokenRepo;

  @ModelAttribute("User")
  public User getUser() {

    return new User();
  }

  /**
   * Redirect the user to the forgot password page, and add any GET parameters
   * to the page.
   * @param request The HTTP request.
   */
  @RequestMapping("/forgot")
  public ModelAndView forgot(HttpServletRequest request) {

    // Get the request GET parameters
    Map<String, String[]> parameters = request.getParameterMap();

    // Create the model and view and add the GET parameters as object in the
    // model
    ModelAndView modelAndView = new ModelAndView("/recover/forgot-password");
    modelAndView.addAllObjects(parameters);

    return modelAndView;
  }

  /**
   * If the user exists in the database, send a password recovery email to their
   * registered email address.
   * @param username The user name of the user.
   */
  @RequestMapping(value = "/forgot/send", method = RequestMethod.POST)
  public String recoverPassword(@RequestParam("login") String username) {

    User user = userRepo.findByLogin(username);

    if (user != null) {

      sendRecoveryEmail(user, user.getEmailAddress());
    }

    return "redirect:/forgot?submitted=true";
  }

  /**
   * Redirect the user to the reset password page.
   * @param tokenId The unique confirmation ID.
   */
  @RequestMapping("/forgot/{tokenId}")
  public ModelAndView confirmAccount(@PathVariable String tokenId, RedirectAttributes attrs) {

    ModelAndView resetView = new ModelAndView("redirect:/recover/resetPass", "user", new User());
    attrs.addFlashAttribute("token", tokenId);

    return resetView;
  }
  
  /**
   * Redirect the user to the forgot password page, and add any GET parameters
   * to the page.
   * @param request The HTTP request.
   */
  @RequestMapping("/recover/resetPass")
  public ModelAndView resetPassword(HttpServletRequest request) {

    // Get the request GET parameters
    Map<String, String[]> parameters = request.getParameterMap();

    // Create the model and view and add the GET parameters as object in the model
    ModelAndView modelAndView = new ModelAndView("/recover/resetPass");
    modelAndView.addAllObjects(parameters);
    
    return modelAndView;
  }
  
  /**
   * Check if the user's new password is valid and if so, save it to the database.
   * @param password The new password.
   * @param confPassword The confirmation password.
   * @param tokenId The id of the verification token.
   * @param attrs Redirect attributes.
   */
  @RequestMapping("/recover/savePassword")
  public ModelAndView savePassword(@RequestParam("password") String password,
      @RequestParam("cPassword") String confPassword,
      @RequestParam("token") String tokenId,
      RedirectAttributes attrs) {
    
    ModelAndView resetView;
    
    Calendar cal = Calendar.getInstance();
    VerificationToken token = tokenRepo.findByToken(tokenId);
    if (token == null || token.getType() != TokenType.PASSWORD
        || (token.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0 || token.isUsed()) {
      
      return new ModelAndView("redirect:/recover/invalidtoken");
    }
    
    // Password length is invalid
    if (password.length() < 8 || password.length() > 20) {
      
      resetView = new ModelAndView("redirect:/recover/resetPass?passwordLength=true",
          "user", new User());
      attrs.addFlashAttribute("token", tokenId);
      return resetView;

    // Password and confirm password is not equal
    } else if (!password.equals(confPassword)) {

      resetView = new ModelAndView("redirect:/recover/resetPass?passwordMismatch=true",
          "user", new User());
      attrs.addFlashAttribute("token", tokenId);
      return resetView;
    }
    
    BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
    User user = token.getUser();
    user.setPassword(pe.encode(password));
    userRepo.save(user);
    token.setUsed(true);
    tokenRepo.save(token);
    return new ModelAndView("redirect:/recover/passwordChanged");
  }

  @RequestMapping("/recover/passwordChanged")
  public String passwordChanged() {

    return "/recover/password-changed";
  }
  
  @RequestMapping("/recover/invalidtoken")
  public String invalidToken() {

    return "/recover/invalid-token";
  }

  private void sendRecoveryEmail(User user, String emailAddress) {

    String tokenId = UUID.randomUUID().toString();

    VerificationToken token = new VerificationToken(tokenId, user, TokenType.PASSWORD);
    tokenRepo.save(token);

    String subject = "Reset your FlatFinder password";

    String messageBody = "In order to reset your password at FlatFinder," 
        + " please click the following link: <br />"
        + "<a href='https://localhost:8070/forgot/" + tokenId + "'>Reset your password</a>";

    // Recipient's email ID needs to be mentioned.
    String to = emailAddress;

    // Sender's email ID needs to be mentioned
    String from = "password-recovery@flatfinder.com";

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
}