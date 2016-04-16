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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class RegistrationController {

  public static final String SIGN_UP_ID_SESSION;
  private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
      + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

  static {

    SIGN_UP_ID_SESSION = "signUpID";
  }

  @Autowired
  private UserRepository userRepo;
  @Autowired
  private RoleRepository roleRepo;
  @Autowired
  private SearcherRepository searcherRepo;
  @Autowired
  private LandlordRepository landlordRepo;

  private Pattern emailPattern = Pattern.compile(EMAIL_REGEX);

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

    String query = "";

    // First name is not set
    if (firstName.length() == 0) {

      query += "fNameLength=true";
    }

    // Last name is not set
    if (lastName.length() == 0) {

      if (query.length() > 0) {

        query += "&";
      }

      query += "lNameLength=true";
    }

    // Email regex matcher
    Matcher matcher = emailPattern.matcher(emailAddress);
    // Email address is not set
    if (emailAddress.length() == 0) {

      if (query.length() > 0) {

        query += "&";
      }

      query += "emailLength=true";

    // Email is not of the correct pattern
    } else if (!matcher.find()) {

      if (query.length() > 0) {

        query += "&";
      }

      query += "emailFormat=true";
    }

    if (query.length() > 0) {

      return "redirect:/searcher/registration?" + query;
    }

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

    String query = "";

    // First name is not set
    if (firstName.length() == 0) {

      query += "fNameLength=true";
    }

    // Last name is not set
    if (lastName.length() == 0) {

      if (query.length() > 0) {

        query += "&";
      }

      query += "lNameLength=true";
    }

    // Email regex matcher
    Matcher matcher = emailPattern.matcher(emailAddress);
    // Email address is not set
    if (emailAddress.length() == 0) {

      if (query.length() > 0) {

        query += "&";
      }

      query += "emailLength=true";

      // Email is not of the correct pattern
    } else if (!matcher.find()) {

      if (query.length() > 0) {

        query += "&";
      }

      query += "emailFormat=true";
    }

    if (query.length() > 0) {

      return "redirect:/landlord/registration?" + query;
    }

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

    // Get the request GET parameters
    Map<String, String[]> parameters = request.getParameterMap();

    // Create the model and view and add the GET parameters as object in the model
    ModelAndView modelAndView = new ModelAndView("landlord/registration-landlord", "Landlord",
        new Landlord((Integer) request.getSession().getAttribute(SIGN_UP_ID_SESSION)));
    modelAndView.addAllObjects(parameters);

    return modelAndView;
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

    // Get the request GET parameters
    Map<String, String[]> parameters = request.getParameterMap();

    // Create the model and view and add the GET parameters as object in the model
    ModelAndView modelAndView = new ModelAndView("searcher/registration-searcher", "Searcher",
        new Searcher((Integer) request.getSession().getAttribute(SIGN_UP_ID_SESSION)));
    modelAndView.addAllObjects(parameters);

    return modelAndView;
  }

  /**
   * Register view.
   * @param request The request
   * @return ModelAndView
   */
  @RequestMapping("/register")
  public ModelAndView register(HttpServletRequest request) {

    // Get the request GET parameters
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
      @RequestParam(value = "cPassword", required = true) String confPassword,
      @RequestParam(value = "role", required = true) String role,
      Model model,
      HttpServletRequest request) {

    String query = "";

    // Username length is invalid
    if (username.length() < 3 || username.length() > 15) {

      query += "usernameLength=true";

    // The username already exists
    } else if (username.length() >= 3 && userRepo.findByLogin(username) != null) {

      query += "usernameExists=true";
    }

    // Password length is invalid
    if (password.length() < 8 || password.length() > 20) {

      if (query.length() > 0) {

        query += "&";
      }

      query += "passwordLength=true";

    // Password and confirm password is not equal
    } else if (!password.equals(confPassword)) {

      if (query.length() > 0) {

        query += "&";
      }

      query += "passwordMismatch=true";
    }

    if (query.length() > 0) {

      return "redirect:/register?" + query;
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