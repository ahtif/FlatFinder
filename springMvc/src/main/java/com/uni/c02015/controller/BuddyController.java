package com.uni.c02015.controller;

import com.uni.c02015.domain.Searcher;
import com.uni.c02015.domain.User;
import com.uni.c02015.domain.property.Property;
import com.uni.c02015.persistence.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BuddyController {

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
   * Find the buddies of the current user and take them to the view buddies page.
   */
  @RequestMapping("/buddy/viewAll")
  public ModelAndView viewBuddies() {
    
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    User currentUser = userRepo.findByLogin(username);
    Searcher searcher = searcherRepo.findById(currentUser.getId());
    ModelAndView modelAndView = new ModelAndView("/buddy/viewAll");
    modelAndView.addObject("searcher", searcher);
    
    return modelAndView;
    
  }
  /**
   * Take the user to a page show the list of all available buddies.
   * If the user has not opted in to the buddy system, then do not allow them to find buddies.
   */
  @RequestMapping("/buddy/findBuddies")
  public ModelAndView findBuddies() {
    
    
    ModelAndView buddyView = new ModelAndView("/buddy/findBuddies");
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    User currentUser = userRepo.findByLogin(username);
    Searcher searcher = searcherRepo.findById(currentUser.getId());
    if (searcher.getBuddyPref()) {
      List<Searcher> buddies = searcherRepo.findByBuddyPref(true);
      buddyView = new ModelAndView("/buddy/findBuddies");
      buddyView.addObject("buddies", buddies);
      return buddyView;
    }
    buddyView.addObject("buddyStatus", false);
    return buddyView;
  }
  

  
}