package com.uni.c02015.controller;

import com.uni.c02015.domain.Message;
import com.uni.c02015.domain.User;
import com.uni.c02015.persistence.repository.UserRepository;
import com.uni.c02015.persistence.repository.RoleRepository;
import com.uni.c02015.persistence.repository.SearcherRepository;
import com.uni.c02015.persistence.repository.LandlordRepository;
import com.uni.c02015.persistence.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
   * @return ModelAndView
   */
  @RequestMapping("messaging/inbox")
  public ModelAndView goToInbox() {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    User currentUser = userRepo.findByLogin(username);
    List<Message> usersMessages = messageRepo.findByReceiver(currentUser);
    ModelAndView inboxView = new ModelAndView("messaging/inboxPage");
    inboxView.addObject("messages", usersMessages);    
    return inboxView;
  }

  /**
   * User inbox.
   * @return ModelAndView
   */
  @RequestMapping("messaging")
  public ModelAndView messageIndex() {
    return goToInbox();
  }
  
  /**
   * Get a specific message from the database and send it to the view.
   * @param id The id of the message.
   */
  @RequestMapping("messaging/view")
  public ModelAndView viewMessage(@RequestParam("id") String id) {
    Message message = messageRepo.findById(Integer.parseInt(id));
    ModelAndView messageView = new ModelAndView("messaging/view","messageAttribute", new Message());
    messageView.addObject("message", message);
    return messageView;
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
   * @param sender The sender of the message
   * @return Return to the current user's inbox.
   */
  @RequestMapping("messaging/sendMessage")
  public String sendMessage(@RequestParam(value = "receiver", required = true) String to,
      @RequestParam(value = "subject", required = true) String subject,
      @RequestParam(value = "message", required = true) String body,
      @RequestParam(value = "parent", required = false) String parent,
      @RequestParam(value = "sender", required = false) String sender) {
     
    User currentUser;
    Message message = new Message();
    
    if (sender != null && !sender.isEmpty()) {
      currentUser = userRepo.findByLogin(sender);
      message.setSender(currentUser);
    } else {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String username = auth.getName();
      currentUser = userRepo.findByLogin(username);
    }
    
    if (parent != null && !parent.isEmpty()) {
      message.setParent(messageRepo.findById(Integer.parseInt(parent)));
    }
    User receiver = userRepo.findByLogin(to);
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
  
  /**
   * Allows an admin to broadcast a message.
   * @param subject The subject of the message.
   * @param body The body of the message.
   * @param sender The sender of the message
   * @return Return to the current user's inbox.
   */
  @RequestMapping("/admin/broadcast/send")
  public String broadcast(@RequestParam(value = "subject", required = true) String subject,
      @RequestParam(value = "message", required = true) String body,
      @RequestParam(value = "parent", required = false) String parent,
      @RequestParam(value = "sender", required = false) String sender) {

    User currentUser;
    Message message = new Message();

    if (sender != null && !sender.isEmpty()) {
      currentUser = userRepo.findByLogin(sender);
    } else {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String username = auth.getName();
      currentUser = userRepo.findByLogin(username);
    }

    if (parent != null && !parent.isEmpty()) {
      message.setParent(messageRepo.findById(Integer.parseInt(parent)));
    }

    ArrayList<User> allUsers = (ArrayList<User>) userRepo.findAll();
    for (User user : allUsers) {
      message = new Message();
      message.setSenderName(currentUser.getLogin());
      message.setSender(currentUser);
      message.setReceiver(user);
      message.setSubject(subject);
      message.setMessage(body);
      message.setMessageDate(new Date());
      message.setIsRead(false);
      messageRepo.save(message);
    }

    return "redirect:/messaging/inbox";
  }
  
}