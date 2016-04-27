package com.uni.c02015.persistence.repository;


import com.uni.c02015.domain.Message;
import com.uni.c02015.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface MessageRepository extends CrudRepository<Message, Integer> {
  
  Message findById(Integer id);

  List<Message> findByReceiver(User currentUser);
  
  List<Message> findBySender(User user);

  List<Message> findByReceiverAndIsRead(User user, boolean bool);
  
  Message findByMessage(String message);
  
  Message findByMessageAndReceiver(String message, User receiver);
  
  Message findByMessageAndSender(String message, User sender);
  
  Message findByMessageAndSenderAndReceiver(String message, User sender, User receiver);
}