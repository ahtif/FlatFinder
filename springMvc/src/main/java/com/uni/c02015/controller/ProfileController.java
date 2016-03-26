package com.uni.c02015.controller;

import com.uni.c02015.SpringMvc;
import com.uni.c02015.domain.Landlord;
import com.uni.c02015.domain.Searcher;
import com.uni.c02015.domain.User;
import com.uni.c02015.persistence.repository.LandlordRepository;
import com.uni.c02015.persistence.repository.RoleRepository;
import com.uni.c02015.persistence.repository.SearcherRepository;
import com.uni.c02015.persistence.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;


@Controller
public class ProfileController {

  @Autowired
  private UserRepository userRepo;
  @Autowired
  private RoleRepository roleRepo;
  @Autowired
  private SearcherRepository searcherRepo;
  @Autowired
  private LandlordRepository landlordRepo;

  @ModelAttribute("User")
  public User getUser() {
    return new User();
  }

  /**
   * Find the role of the user and redirect them to the appropriate profile page.
   * @param request HttpServletRequest
   * @return String
   */
  @RequestMapping("/profile")
  public String getRole(HttpServletRequest request) {
    if (request.isUserInRole(SpringMvc.ROLE_LANDLORD)) {
      return "redirect:/landlord/profile";
    } else {
      return "redirect:/searcher/profile";
    }
  }
  
  /**
   * Update searcher information.
   * @return ModelAndView
   */
  @RequestMapping("/searcher/profile")
  public ModelAndView viewProfile() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    User user = userRepo.findByLogin(username);
    Searcher searcher = searcherRepo.findById(user.getId());
    ModelAndView profileView = new ModelAndView("searcher/view","user",new Searcher());
    profileView.addObject("searcher", searcher);
    return profileView;
  }

  /**
   * Edit searcher information.
   * @param firstName String
   * @param lastName String
   * @param emailAddress String
   * @param buddy Boolean
   * @return String
   */
  @RequestMapping("/searcher/edit")
  public String editSearcherProfile(@RequestParam("firstName") String firstName,
          @RequestParam("lastName") String lastName,
          @RequestParam("emailAddress") String emailAddress,
          @RequestParam("buddyPref")Boolean buddy) {
    
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    User user = userRepo.findByLogin(username);
    Searcher searcher = searcherRepo.findById(user.getId());
    searcher.setFirstName(firstName);
    searcher.setLastName(lastName);
    searcher.setEmailAddress(emailAddress);
    searcher.setBuddyPref(buddy);
    searcherRepo.save(searcher);
    
    return "redirect:/searcher/profile";
  }

  /**
   * Get landlord information.
   * @return ModelAndView
   */
  @RequestMapping("/landlord/profile")
  public ModelAndView viewLandLordProfile() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    User user = userRepo.findByLogin(username);
    Landlord landlord = landlordRepo.findById(user.getId());
    ModelAndView profileView = new ModelAndView("landlord/view","user",new Landlord());
    profileView.addObject("landlord", landlord);
    return profileView;
  }

  /**
   * Update landlord information.
   * @param firstName String
   * @param lastName String
   * @param emailAddress String
   * @return String
   */
  @RequestMapping("/landlord/edit")
  public String editLandlordProfile(@RequestParam("firstName") String firstName,
          @RequestParam("lastName") String lastName,
          @RequestParam("emailAddress") String emailAddress) {
    
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    User user = userRepo.findByLogin(username);
    Landlord landlord = landlordRepo.findById(user.getId());
    landlord.setFirstName(firstName);
    landlord.setLastName(lastName);
    landlord.setEmailAddress(emailAddress);
    landlordRepo.save(landlord);
    
    return "redirect:/landlord/profile";
  }
}