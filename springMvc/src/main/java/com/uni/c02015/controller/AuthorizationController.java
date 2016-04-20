package com.uni.c02015.controller;

import com.uni.c02015.SpringMvc;
import com.uni.c02015.persistence.repository.LandlordRepository;
import com.uni.c02015.persistence.repository.SearcherRepository;
import com.uni.c02015.persistence.repository.UserRepository;
import com.uni.c02015.persistence.repository.property.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthorizationController {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private SearcherRepository searcherRepository;
  @Autowired
  private LandlordRepository landlordRepository;
  @Autowired
  private TypeRepository typeRepository;

  /**
   * Login.
   * @return ModelAndView
   */
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView loginForm() {

    return new ModelAndView("index");
  }

  /**
   * Invalid login.
   * @return ModelAndView
   */
  @RequestMapping(value = "/invalid-login", method = RequestMethod.GET)
  public ModelAndView invalidLogin() {

    ModelAndView modelAndView = new ModelAndView("index");
    modelAndView.addObject("error", true);
    return modelAndView;
  }

  /**
   * Login success.
   * @param request HttpServletRequest
   * @return String
   */
  @RequestMapping(value = "/success-login", method = RequestMethod.GET)
  public ModelAndView successLogin(HttpServletRequest request) {

    if (request.isUserInRole(SpringMvc.ROLE_ADMINISTRATOR)) {

      return new ModelAndView("administrator/index");
    }

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User userDetails = (User) authentication.getPrincipal();
    com.uni.c02015.domain.User user = userRepository.findByLogin(userDetails.getUsername());

    if (request.isUserInRole(SpringMvc.ROLE_LANDLORD)) {

      // Check the landlord has completed the sign up process
      if (landlordRepository.findById(user.getId()) == null) {

        request.getSession().setAttribute(RegistrationController.SIGN_UP_ID_SESSION, user.getId());

        return new ModelAndView("redirect:/landlord/registration");
      }

      return new ModelAndView("landlord/index");
    }

    // Check the searcher has completed the sign up process
    if (searcherRepository.findById(user.getId()) == null) {

      request.getSession().setAttribute(RegistrationController.SIGN_UP_ID_SESSION, user.getId());

      return new ModelAndView("redirect:/searcher/registration");
    }
    // Add property types
    ModelAndView modelAndView = new ModelAndView("searcher/index");
    modelAndView.addObject("types", typeRepository.findAll());

    return modelAndView;
  }

  /**
   * User logout.
   * @param request HttpServletRequest
   * @return ModelAndView
   * @throws ServletException Throws on error
   */
  @RequestMapping(value = "/user-logout", method = RequestMethod.GET)
  public ModelAndView logout(HttpServletRequest request) throws ServletException {

    // Ensure the user is logged out if the URL is accessed directly
    request.logout();

    ModelAndView modelAndView = new ModelAndView("index");
    modelAndView.addObject("logout", true);
    return modelAndView;
  }

  /**
   * User error.
   * @return String
   */
  @RequestMapping(value = "/user-error", method = RequestMethod.GET)
  public String error() {

    return "/error-message";
  }
}