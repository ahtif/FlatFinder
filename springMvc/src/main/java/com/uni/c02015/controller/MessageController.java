package com.uni.c02015.controller;

import com.uni.c02015.SpringMvc;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MessageController {

  @Autowired
  private UserRepository userRepo;
  @Autowired
  private RoleRepository roleRepo;
  @Autowired
  private SearcherRepository searcherRepo;
  @Autowired
  private LandlordRepository landlordRepo;
  @Autowired
  private MessageRepository messageRepo;

  @ModelAttribute("User")
  public User getUser() {
    return new User();
  }

  /**
   * Get the user logged in, find all the message sent to them, and take them to their inbox.
   */
  @RequestMapping("messaging/inbox")
  public ModelAndView goToInbox() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    User currentUser = userRepo.findByLogin(username);
    List<Message> usersMessages = messageRepo.findByReceiver(currentUser);
    ModelAndView inboxView = new ModelAndView("messaging/inbox");
    inboxView.addObject("messages", usersMessages);    
    return inboxView;
  }
  
  @RequestMapping("messaging/new")
  public ModelAndView newMessage() {
    return new ModelAndView("messaging/newMessage","message",new Message());
  }
  
  /**
   * Take the message information entered in the page and send it.
   * @param to The receiver of the message.
   * @param subject The subject of the message.
   * @param body The body of the message.
   * @return Return to the current user's inbox.
   */
  @RequestMapping("messaging/sendMessage")
  public String sendMessage(@RequestParam(value = "receiver", required = true) String to,
      @RequestParam(value = "subject", required = true) String subject,
      @RequestParam(value = "message", required = true) String body) {
    
    
    System.out.println("sending message");
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    User currentUser = userRepo.findByLogin(username);
    
    User receiver = userRepo.findByLogin(to);
    
    Message message = new Message();
    message.setSenderName(currentUser.getLogin());
    message.setSender(currentUser);
    message.setReceiver(receiver);
    message.setSubject(subject);
    message.setMessage(body);
    message.setMessageDate(new Date());
    message.setIsRead(false);
    messageRepo.save(message);
    
    return "redirect:/messaging/inbox";
  }
  
}