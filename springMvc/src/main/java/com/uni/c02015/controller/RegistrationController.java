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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class RegistrationController {

  private static final String SIGN_UP_ID_SESSION = "signUpID";

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
   * Add a searcher.
   * @param request The request
   * @return String
   */
  @RequestMapping(value = "/addSearcher", method = RequestMethod.POST)
  public String addSearcher(
      @RequestParam(value = "firstName", required = true) String firstName,
      @RequestParam(value = "lastName", required = true) String lastName,
      @RequestParam(value = "emailAddress", required = true) String emailAddress,
      @RequestParam(value = "buddyPref", required = true) boolean buddyPref,
      Model model,
      HttpServletRequest request) {

    Searcher searcher = new Searcher(
        (Integer) request.getSession().getAttribute(SIGN_UP_ID_SESSION));
    
    searcher.setFirstName(firstName);
    searcher.setLastName(lastName);
    searcher.setEmailAddress(emailAddress);
    searcher.setBuddyPref(buddyPref);
    searcherRepo.save(searcher);

    // Remove the sign up session
    request.getSession().removeAttribute(SIGN_UP_ID_SESSION);

    return "redirect:/";
  }

  /**
   * Add a landlord.
   * @param request The request
   * @return String
   */
  @RequestMapping(value = "/addLandlord", method = RequestMethod.POST)
  public String addLandlord(@RequestParam(value = "firstName", required = true) String firstName,
      @RequestParam(value = "lastName", required = true) String lastName,
      @RequestParam(value = "emailAddress", required = true) String emailAddress, Model model,
      HttpServletRequest request) {

    Landlord landlord = 
        new Landlord((Integer) request.getSession().getAttribute(SIGN_UP_ID_SESSION));

    landlord.setFirstName(firstName);
    landlord.setLastName(lastName);
    landlord.setEmailAddress(emailAddress);
    landlordRepo.save(landlord);

    // Remove the sign up session
    request.getSession().removeAttribute(SIGN_UP_ID_SESSION);

    return "redirect:/";
  }

  /**
   * Registration page for landlord.
   * @param request The request
   * @return ModelAndView
   */
  @RequestMapping(value = "/landlord/registration", method = RequestMethod.GET)
  public ModelAndView showLandlordRegistrationForm(HttpServletRequest request) {

    // Don't allow access unless the sign up session was set
    if (request.getSession().getAttribute(SIGN_UP_ID_SESSION) == null) {

      return new ModelAndView("redirect:/register");
    }

    //
    return new ModelAndView("landlord/registration-landlord",
        "Landlord",
        new Landlord((Integer) request.getSession().getAttribute(SIGN_UP_ID_SESSION)));
  }

  /**
   * Registration page for a landlord.
   * @param request The request
   * @return ModelAndView
   */
  @RequestMapping(value = "/searcher/registration", method = RequestMethod.GET)
  public ModelAndView showSearcherRegistrationForm(HttpServletRequest request) {

    // Don't allow access unless the sign up session was set
    if (request.getSession().getAttribute(SIGN_UP_ID_SESSION) == null) {

      return new ModelAndView("redirect:/register");
    }

    return new ModelAndView("searcher/registration-searcher",
        "Searcher",
        new Searcher((Integer) request.getSession().getAttribute(SIGN_UP_ID_SESSION)));
  }

  /**
   * Register view.
   * @param request The request
   * @return ModelAndView
   */
  @RequestMapping("/register")
  public ModelAndView register(HttpServletRequest request) {

    // Get the request GET paramaters
    Map<String, String[]> parameters = request.getParameterMap();

    // Create the model and view and add the GET parameters as object in the model
    ModelAndView modelAndView = new ModelAndView("register");
    modelAndView.addAllObjects(parameters);

    return modelAndView;
  }
  
  /**
   * Create the user from the details given and store it in the database.
   * @param username The username.
   * @param password The password.
   * @param role The account type
   */
  @RequestMapping(value = "/createAccount", method = RequestMethod.POST)
  public String assessRequest(
      @RequestParam(value = "login", required = true) String username,
      @RequestParam(value = "password", required = true) String password,
      @RequestParam(value = "role", required = true) String role,
      Model model,
      HttpServletRequest request) {

    // The username already exists
    if (userRepo.findByLogin(username) != null) {

      return "redirect:/register?usernameExists=true";
    }

    User user = new User();
    user.setLogin(username);

    user.setPassword(new BCryptPasswordEncoder().encode(password));
    user.setRole(roleRepo.findByRole(role));
    userRepo.save(user);

    // Set the sign up session
    request.getSession().setAttribute(SIGN_UP_ID_SESSION, user.getId());

    // Searcher
    if (user.getRole().getRole().equals(SpringMvc.ROLE_SEARCHER)) {

      return "redirect:/searcher/registration";

    // Landlord
    } else {

      return "redirect:/landlord/registration";
    }
  }
}