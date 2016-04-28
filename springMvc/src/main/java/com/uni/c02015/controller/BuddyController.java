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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
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

    List<Request> pendingRequests =
        buddyRepo.findByReceiverAndConfirmed(currentUser, false);
    modelAndView.addObject("pending", pendingRequests);
    
    List<Request> acceptedRequests = buddyRepo.findBySenderAndConfirmed(currentUser, true);
    List<Request> acceptedRequests2 =
        buddyRepo.findByReceiverAndConfirmed(currentUser, true);
    
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

    List<BuddyProperty> buddyProperties =
        buddyPropertyRepository.findByProperty(propertyRepository.findById(propertyId));

    // Remove current user from list
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    User currentUser = userRepo.findByLogin(username);

    Iterator<BuddyProperty> iter = buddyProperties.iterator();
    while (iter.hasNext()) {

      BuddyProperty buddyProperty = iter.next();

      if (buddyProperty.getUser().getId() == currentUser.getId()) {

        iter.remove();

      } else if (buddyRepo.findBySenderAndReceiver(currentUser, buddyProperty.getUser()) != null
          || buddyRepo.findBySenderAndReceiver(buddyProperty.getUser(), currentUser) != null) {

        iter.remove();
      }
    }

    modelAndView.addObject("buddiesProperty", buddyProperties);

    return modelAndView;
  }

  /**
   * Create a new buddy up request and redirect the the user to the view buddies page.
   * @param userId User Id
   * @param propertyId Property Id
   * @return String
   */
  @RequestMapping("/buddy/request/{userId}/{propertyId}")
  public String requestBuddy(@PathVariable Integer userId, @PathVariable Integer propertyId) {
    
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    User currentUser = userRepo.findByLogin(username);
    User requestedUser = userRepo.findById(userId);

    if (currentUser.getId() != requestedUser.getId()) {

      Request request = new Request();
      request.setSender(currentUser);
      request.setReceiver(requestedUser);
      request.setProperty(propertyRepository.findById(propertyId));
      buddyRepo.save(request);
    }

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

      // Let's check to see if the recipient was also a sender of a request to the current sender
      Request otherRequest =
          buddyRepo.findBySenderAndReceiver(request.getReceiver(), request.getSender());
      // There was a pending request in both directions
      if (otherRequest != null) {

        buddyRepo.delete(otherRequest);
      }

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

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    User currentUser = userRepo.findByLogin(username);
    Searcher buddy = searcherRepo.findOne(buddyId);
    User buddyUser = userRepo.findOne(buddyId);
    
    ModelAndView modelAndView = new ModelAndView("/buddy/view-buddy");
    List<Request> acceptedRequests = buddyRepo.findBySenderAndConfirmed(currentUser, true);
    List<Request> acceptedRequests2 =
        buddyRepo.findByReceiverAndConfirmed(currentUser, true);
    
    List<Searcher> receivers = new ArrayList<>();
    for (Request req : acceptedRequests) {
      receivers.add(searcherRepo.findById(req.getReceiver().getId()));
    }
    
    List<Searcher> senders = new ArrayList<>();
    for (Request req : acceptedRequests2) {
      senders.add(searcherRepo.findById(req.getSender().getId()));
    }
    
    if (receivers.contains(buddy) || senders.contains(buddy)) {
      modelAndView.addObject("buddy", buddy);
      modelAndView.addObject("buddyUser", buddyUser);
      return modelAndView;
    }
    
    return new ModelAndView("redirect:/buddy/viewAll?notBuddy=true");
  }

  
}