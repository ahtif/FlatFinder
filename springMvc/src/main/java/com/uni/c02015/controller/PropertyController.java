package com.uni.c02015.controller;

import com.uni.c02015.domain.Landlord;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PropertyController {

  @RequestMapping(value = "/property/add", method = RequestMethod.GET)
  public ModelAndView showLandlordRegistrationForm(HttpServletRequest request) {

    ModelAndView modelAndView = new ModelAndView("property/add");

    return modelAndView;
  }
}