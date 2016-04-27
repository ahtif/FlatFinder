package com.uni.c02015.persistence.repository.buddy;

import com.uni.c02015.domain.buddy.Request;
import com.uni.c02015.domain.Searcher;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface RequestRepository extends CrudRepository<Request, Integer> {
  
  List<Request> findBySender(Searcher sender);
  
  List<Request> findByReceiver(Searcher receiver);
 
  List<Request> findByReceiverAndConfirmed(Searcher receiver, boolean confirmed);
  
  List<Request> findBySenderAndConfirmed(Searcher receiver, boolean confirmed);
  
  Request findBySenderAndReceiver(Searcher sender, Searcher receiver);
}