package com.uni.c02015.controller;

import com.uni.c02015.domain.Message;
import com.uni.c02015.domain.User;
import com.uni.c02015.domain.property.Property;
import com.uni.c02015.persistence.repository.MessageRepository;
import com.uni.c02015.persistence.repository.UserRepository;
import com.uni.c02015.persistence.repository.property.PropertyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {

  @Autowired
  private MessageRepository messageRepo;
  @Autowired
  private UserRepository userRepo;
  @Autowired
  PropertyRepository propertyRepo;
  
  
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
  
  /**
   * Take the admin to the manage properties pages, and add any GET parameters to the page.
   * @param request The HTTP request.
   */
  @RequestMapping("/admin/viewProperties")
  public ModelAndView viewProperties(HttpServletRequest request) {
    // Get the request GET parameters
    Map<String, String[]> parameters = request.getParameterMap();

    // Create the model and view and add the GET parameters as object in the model
    ModelAndView modelAndView = new ModelAndView("/administrator/view-properties");
    modelAndView.addAllObjects(parameters);
    
    ArrayList<Property> properties = (ArrayList<Property>) propertyRepo.findAll();
    modelAndView.addObject("properties", properties);

    return modelAndView;
  }
  
  /**
   * Take in a property ID and delete the property from the database if it exists.
   * @param id The ID of the property.
   */
  @RequestMapping("/admin/property/delete/{id}")
  public String deleteProperty(@PathVariable Integer id) {
    Property property = propertyRepo.findById(id);
    if (property != null){
      propertyRepo.delete(id);
      return "redirect:/admin/viewProperties?deleted=true";
    }
    return "redirect:/admin/viewProperties";
  }
  
  
  
  
}