package com.uni.c02015.controller;

import com.uni.c02015.domain.Landlord;
import com.uni.c02015.domain.Searcher;
import com.uni.c02015.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {
  @Autowired
  private UserRepository userRepo;

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
}