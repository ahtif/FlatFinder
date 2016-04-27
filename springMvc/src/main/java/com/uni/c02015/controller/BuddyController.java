package com.uni.c02015.controller;

import com.uni.c02015.domain.BuddyRequest;
import com.uni.c02015.domain.Searcher;
import com.uni.c02015.domain.User;
import com.uni.c02015.persistence.repository.*;
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
  private BuddyRequestRepository buddyRepo;
  
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
    
    List<BuddyRequest> pendingRequests =
        buddyRepo.findByReceiverAndConfirmed(currentSearcher, false);
    modelAndView.addObject("pending", pendingRequests);
    
    List<BuddyRequest> acceptedRequests = buddyRepo.findBySenderAndConfirmed(currentSearcher, true);
    List<BuddyRequest> acceptedRequests2 =
        buddyRepo.findByReceiverAndConfirmed(currentSearcher, true);
    
    modelAndView.addObject("sentBuddies", acceptedRequests);
    modelAndView.addObject("acceptedBuddies", acceptedRequests2);
    
    return modelAndView;
    
  }
  /**
   * Take the user to a page show the list of all available buddies.
   * If the user has not opted in to the buddy system, then do not allow them to find buddies.
   */
  @RequestMapping("/buddy/findBuddies")
  public ModelAndView findBuddies() {
    
    ModelAndView buddyView = new ModelAndView("/buddy/findBuddies");
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    User currentUser = userRepo.findByLogin(username);
    Searcher searcher = searcherRepo.findById(currentUser.getId());
    if (searcher.getBuddyPref()) {
      List<Searcher> buddiesToRemove = new ArrayList<Searcher>();
      List<Searcher> buddies = searcherRepo.findByBuddyPref(true);
      for (Searcher buddy : buddies) {
        if (buddyRepo.findBySenderAndReceiver(searcher, buddy) != null
            || buddyRepo.findBySenderAndReceiver(buddy, searcher) != null) {
          buddiesToRemove.add(buddy);
        }
      }
      buddies.removeAll(buddiesToRemove);
      buddies.remove(searcher);
      buddyView = new ModelAndView("/buddy/findBuddies");
      buddyView.addObject("buddies", buddies);
      return buddyView;
    }
    buddyView.addObject("notBuddy", true);
    return buddyView;
  }
  
  /**
   * Create a new buddy up reqeust and redirect the the user to the view buddies page.
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
    
    BuddyRequest request = new BuddyRequest();
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
    
    BuddyRequest request = buddyRepo.findOne(id);
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
    
    BuddyRequest request = buddyRepo.findOne(id);
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
    List<BuddyRequest> acceptedRequests = buddyRepo.findBySenderAndConfirmed(currentSearcher, true);
    List<BuddyRequest> acceptedRequests2 =
        buddyRepo.findByReceiverAndConfirmed(currentSearcher, true);
    
    List<Searcher> receivers = new ArrayList<>();
    for (BuddyRequest req : acceptedRequests) {
      receivers.add(req.getReceiver());
    }
    
    List<Searcher> senders = new ArrayList<>();
    for (BuddyRequest req : acceptedRequests2) {
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