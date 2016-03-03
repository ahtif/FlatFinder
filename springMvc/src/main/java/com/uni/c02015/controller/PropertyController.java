package com.uni.c02015.controller;

import com.uni.c02015.domain.User;
import com.uni.c02015.domain.property.Property;
import com.uni.c02015.persistence.repository.LandlordRepository;
import com.uni.c02015.persistence.repository.UserRepository;
import com.uni.c02015.persistence.repository.property.PropertyRepository;
import com.uni.c02015.persistence.repository.property.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PropertyController {

  @Autowired
  TypeRepository typeRepository;
  @Autowired
  LandlordRepository landlordRepository;
  @Autowired
  UserRepository userRepository;
  @Autowired
  PropertyRepository propertyRepository;

  /**
   * View property.
   * @return ModelAndView
   */
  @RequestMapping(value = "/property/add", method = RequestMethod.GET)
  public ModelAndView propertyAdd(HttpServletRequest request) {

    ModelAndView modelAndView = new ModelAndView("property/add");

    modelAndView.addObject("types", typeRepository.findAll());

    return modelAndView;
  }

  /**
   * Add property to database.
   * @return String
   */
  @RequestMapping(value = "/property/addPost", method = RequestMethod.POST)
  public String propertyAddPost(HttpServletRequest request, Principal principal) {

    Property property = new Property();

    property.setNumber(request.getParameter("pNumber"));
    property.setStreet(request.getParameter("pStreet"));
    property.setCity(request.getParameter("pCity"));
    property.setPostcode(request.getParameter("pPostcode"));
    property.setType(
        typeRepository.findById(new Integer(request.getParameter("pType")))
    );
    property.setRooms(new Integer(request.getParameter("pRooms")));

    User user = userRepository.findByLogin(((org.springframework.security.core.userdetails.User) 
        ((Authentication) principal).getPrincipal()).getUsername());

    property.setLandlord(landlordRepository.findById(user.getId()));

    propertyRepository.save(property);

    return "property/addPost";
  }

  /**
   * View all properties.
   * @return ModelAndView
   */
  @RequestMapping(value = "/property/viewAll", method = RequestMethod.GET)
  public ModelAndView viewProperty(Principal principal) {

    ModelAndView modelAndView = new ModelAndView("property/viewAll");

    User user = userRepository.findByLogin(((org.springframework.security.core.userdetails.User) 
        ((Authentication) principal).getPrincipal()).getUsername());
    modelAndView.addObject("properties", 
        propertyRepository.findByLandlord(landlordRepository.findById(user.getId())));

    return modelAndView;
  }
  
  /**
   * View property.
   * @return ModelAndView
   */
  @RequestMapping(value = "/property/view/{id}", method = RequestMethod.GET)
  public ModelAndView viewProperty(@PathVariable(value = "id") Integer id) {

    ModelAndView modelAndView = new ModelAndView("property/view");

    // TODO check the property ID exists
    modelAndView.addObject("property", propertyRepository.findById(id));

    return modelAndView;
  }
}