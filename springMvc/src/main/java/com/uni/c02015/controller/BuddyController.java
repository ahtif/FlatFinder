package com.uni.c02015.controller;

import com.uni.c02015.domain.Searcher;
import com.uni.c02015.domain.User;
import com.uni.c02015.domain.buddy.BuddyProperty;
import com.uni.c02015.domain.buddy.Request;
import com.uni.c02015.domain.property.Property;
import com.uni.c02015.persistence.repository.LandlordRepository;
import com.uni.c02015.persistence.repository.RoleRepository;
import com.uni.c02015.persistence.repository.SearcherRepository;
import com.uni.c02015.persistence.repository.UserRepository;
import com.uni.c02015.persistence.repository.buddy.BuddyPropertyRepository;
import com.uni.c02015.persistence.repository.buddy.RequestRepository;
import com.uni.c02015.persistence.repository.property.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class BuddyController {

  @Autowired
  private UserRepository userRepo;
  @Autowired
  private RoleRepository roleRepo;
  @Autowired
  private SearcherRepository searcherRepo;
  @Autowired
  private LandlordRepository landlordRepo;
  @Autowired
  private RequestRepository buddyRepo;
  @Autowired
  private BuddyPropertyRepository buddyPropertyRepository;
  @Autowired
  private PropertyRepository propertyRepository;
  
  @ModelAttribute("User")
  public User getUser() {
    return new User();
  }

  /**
   * Find the buddies of the current user and take them to the view buddies page.
   */
  @RequestMapping("/buddy/viewAll")
  public ModelAndView viewBuddies(HttpServletRequest request) {
    
    // Get the request GET parameters
    Map<String, String[]> parameters = request.getParameterMap();

    // Create the model and view and add the GET parameters as object in the model
    ModelAndView modelAndView = new ModelAndView("/buddy/viewAll"); 
    modelAndView.addAllObjects(parameters);
    
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    User currentUser = userRepo.findByLogin(username);
    Searcher currentSearcher = searcherRepo.findById(currentUser.getId());
    
    List<Request> pendingRequests =
        buddyRepo.findByReceiverAndConfirmed(currentSearcher, false);
    modelAndView.addObject("pending", pendingRequests);
    
    List<Request> acceptedRequests = buddyRepo.findBySenderAndConfirmed(currentSearcher, true);
    List<Request> acceptedRequests2 =
        buddyRepo.findByReceiverAndConfirmed(currentSearcher, true);
    
    modelAndView.addObject("sentBuddies", acceptedRequests);
    modelAndView.addObject("acceptedBuddies", acceptedRequests2);
    
    return modelAndView;
  }

  /**
   * Create a buddy property request.
   * @param userId The user Id
   * @param propertyId The property Id
   * @return String
   */
  @RequestMapping(value = "/buddy/property/{userId}/{propertyId}")
  public String buddyPropertyPreference(@PathVariable Integer userId,
                                        @PathVariable Integer propertyId) {

    Property property = propertyRepository.findById(propertyId);
    User user = userRepo.findById(userId);

    BuddyProperty buddyProperty = buddyPropertyRepository.findByPropertyAndUser(property, user);

    if (buddyProperty != null) {

      buddyPropertyRepository.delete(buddyProperty);

    } else {

      buddyProperty = new BuddyProperty();
      buddyProperty.setProperty(property);
      buddyProperty.setUser(user);

      buddyPropertyRepository.save(buddyProperty);
    }

    return "redirect:/property/view/" + propertyId;
  }

  /**
   * Show the buddies for a property.
   * @param propertyId The property Id
   * @return ModelAndView
   */
  @RequestMapping("/buddy/showPropertyBuddies/{propertyId}")
  public ModelAndView showBuddiesForProperty(@PathVariable Integer propertyId) {

    ModelAndView modelAndView = new ModelAndView("/buddy/showPropertyBuddies");

    modelAndView.addObject("buddiesProperty",
        buddyPropertyRepository.findByProperty(propertyRepository.findById(propertyId)));

    return modelAndView;
  }
  
  /**
   * Create a new buddy up request and redirect the the user to the view buddies page.
   * @param id The id of the user to request a buddy up with.
   */
  @RequestMapping("/buddy/request/{id}")
  public String requestBuddy(@PathVariable Integer id) {
    
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    User currentUser = userRepo.findByLogin(username);
    Searcher currentSearcher = searcherRepo.findById(currentUser.getId());
    User requestedUser = userRepo.findById(id);
    Searcher requestedSearcher = searcherRepo.findById(requestedUser.getId());
    
    Request request = new Request();
    request.setSender(currentSearcher);
    request.setReceiver(requestedSearcher);
    buddyRepo.save(request);
    
    return "redirect:/buddy/viewAll?requested=true";
  }
  
  /**
   * Accept a buddy request.
   * @param id The id of the buddy request.
   */
  @RequestMapping("/buddy/accept/{id}")
  public String acceptBuddy(@PathVariable Integer id) {
    
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    User currentUser = userRepo.findByLogin(username);
    
    Request request = buddyRepo.findOne(id);
    if (request.getReceiver().getId() == currentUser.getId() && !request.getConfirmed()) {
      request.setConfirmed(true);
      buddyRepo.save(request);
      return "redirect:/buddy/viewAll?accepted=true";   
    }
    
    return "redirect:/buddy/viewAll";
  }
  
  /**
   * Reject a buddy request.
   * @param id The id of the buddy request.
   */
  @RequestMapping("/buddy/reject/{id}")
  public String rejectBuddy(@PathVariable Integer id) {
    
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    User currentUser = userRepo.findByLogin(username);
    
    Request request = buddyRepo.findOne(id);
    if (request.getReceiver().getId() == currentUser.getId() && !request.getConfirmed()) {
      buddyRepo.delete(request);
      return "redirect:/buddy/viewAll?rejected=true";   
    }
    
    return "redirect:/buddy/viewAll";
  }
  
  /**
   * View a buddy's profile
   * @param buddyId The id of the buddy.
   */
  @RequestMapping("/buddy/viewBuddy/{buddyId}")
  public ModelAndView viewBuddy(@PathVariable Integer buddyId) {
    System.out.println("hello1");
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    User currentUser = userRepo.findByLogin(username);
    Searcher currentSearcher = searcherRepo.findOne(currentUser.getId());
    Searcher buddy = searcherRepo.findOne(buddyId);
    User buddyUser = userRepo.findOne(buddyId);
    
    ModelAndView modelAndView = new ModelAndView("/buddy/view-buddy");
    List<Request> acceptedRequests = buddyRepo.findBySenderAndConfirmed(currentSearcher, true);
    List<Request> acceptedRequests2 =
        buddyRepo.findByReceiverAndConfirmed(currentSearcher, true);
    
    List<Searcher> receivers = new ArrayList<>();
    for (Request req : acceptedRequests) {
      receivers.add(req.getReceiver());
    }
    
    List<Searcher> senders = new ArrayList<>();
    for (Request req : acceptedRequests2) {
      senders.add(req.getSender());
    }
    
    if (receivers.contains(buddy) || senders.contains(buddy)) {
      modelAndView.addObject("buddy", buddy);
      modelAndView.addObject("buddyUser", buddyUser);
      System.out.println("hello");
      return modelAndView;
    }
    
    return new ModelAndView("redirect:/buddy/viewAll?notBuddy=true");
  }

  
}