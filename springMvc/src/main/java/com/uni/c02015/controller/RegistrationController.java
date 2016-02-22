package com.uni.c02015.controller;

import com.uni.c02015.domain.Landlord;
import com.uni.c02015.domain.Searcher;
import com.uni.c02015.domain.Role;
import com.uni.c02015.persistence.repository.UserRepository;
import com.uni.c02015.persistence.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.ModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

import com.uni.c02015.domain.User;

@Controller
public class RegistrationController {
  @Autowired
  private UserRepository userRepo;
  @Autowired
  private RoleRepository roleRepo;

  @ModelAttribute("User")
  public User getUser(){
	  return new User();
  }
  
  //returns registration page for landlord
  @RequestMapping(value = "/landlord/registration", method = RequestMethod.GET)
  public ModelAndView showLandlordRegistrationForm() {
    return new ModelAndView("landlord/registration-landlord","Landlord",new Landlord());
  }

  //returns registration page for searcher
  @RequestMapping(value = "/searcher/registration", method = RequestMethod.GET)
  public ModelAndView showSearcherRegistrationForm() {
    return new ModelAndView("searcher/registration-searcher","Searcher",new Searcher());
  }
  
  @RequestMapping("/register")
  public String register() {
	  return "register";
  }
  
  @RequestMapping(value = "/createAccount", method = RequestMethod.POST)
  public ModelAndView assessRequest(
  		@RequestParam(value="login", required=true) String username,
  		@RequestParam(value="password", required=true) String password,
  		@RequestParam(value="role", required=true) String role,
        Model model){
	  
	    User u = new User();
		
		u.setLogin(username);
		u.setPassword(password);
		u.setRole(roleRepo.findByRole(role));
		userRepo.save(u);
		
		
		
     return new ModelAndView("redirect:/index");
  }

}