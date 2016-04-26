package com.uni.c02015.persistence.repository;

import com.uni.c02015.domain.BuddyRequest;
import com.uni.c02015.domain.Searcher;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface BuddyRequestRepository extends CrudRepository<BuddyRequest, Integer> {
  
  List<BuddyRequest> findBySender(Searcher sender);
  
  List<BuddyRequest> findByReceiver(Searcher receiver);
 
  List<BuddyRequest> findByReceiverAndConfirmed(Searcher receiver, boolean confirmed);
  
  List<BuddyRequest> findBySenderAndConfirmed(Searcher receiver, boolean confirmed);
  
  BuddyRequest findBySenderAndReceiver(Searcher sender, Searcher receiver);
}