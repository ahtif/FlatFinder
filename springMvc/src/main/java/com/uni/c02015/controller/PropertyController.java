package com.uni.c02015.controller;

import com.uni.c02015.persistence.repository.property.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PropertyController {

  @Autowired
  TypeRepository typeRepository;

  @RequestMapping(value = "/property/add", method = RequestMethod.GET)
  public ModelAndView showLandlordRegistrationForm(HttpServletRequest request) {

    ModelAndView modelAndView = new ModelAndView("property/add");

    modelAndView.addObject("types", typeRepository.findAll());

    return modelAndView;
  }
}