package com.uni.c02015.controller;

import com.uni.c02015.domain.Landlord;
import com.uni.c02015.domain.property.Property;
import com.uni.c02015.persistence.repository.LandlordRepository;
import com.uni.c02015.persistence.repository.property.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PropertyController {

  @Autowired
  TypeRepository typeRepository;
  @Autowired
  LandlordRepository landlordRepository;

  @RequestMapping(value = "/property/add", method = RequestMethod.GET)
  public ModelAndView propertyAdd(HttpServletRequest request) {

    ModelAndView modelAndView = new ModelAndView("property/add");

    modelAndView.addObject("types", typeRepository.findAll());

    return modelAndView;
  }

  @RequestMapping(value = "/property/addPost", method = RequestMethod.POST)
  public String propertyAddPost(HttpServletRequest request) {

    Property property = new Property();

    property.setNumber(request.getParameter("pNumber"));
    property.setStreet(request.getParameter("pStreet"));
    property.setCity(request.getParameter("pCity"));
    property.setPostcode(request.getParameter("pPostcode"));
    property.setType(
        typeRepository.findById(new Integer(request.getParameter("pType")))
    );
    property.setRooms(new Integer(request.getParameter("pRooms")));
    property.setLandlord(landlordRepository.findById());

    return "property/addPost";
  }
}