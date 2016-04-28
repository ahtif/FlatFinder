package com.uni.c02015.persistence.repository.buddy;

import com.uni.c02015.domain.User;
import com.uni.c02015.domain.buddy.Request;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RequestRepository extends CrudRepository<Request, Integer> {
  
  List<Request> findBySender(User sender);
  
  List<Request> findByReceiver(User receiver);
 
  List<Request> findByReceiverAndConfirmed(User receiver, boolean confirmed);
  
  List<Request> findBySenderAndConfirmed(User receiver, boolean confirmed);
  
  Request findBySenderAndReceiver(User sender, User receiver);

}