package com.uni.c02015.controller;

import com.uni.c02015.SpringMvc;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthorizationController {

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
  public String successLogin(HttpServletRequest request) {

    if (request.isUserInRole(SpringMvc.ROLE_ADMINISTRATOR)) {

      return "administrator/index";
    }

    if (request.isUserInRole(SpringMvc.ROLE_LANDLORD)) {

      return "landlord/index";
    }

    return "searcher/index";
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