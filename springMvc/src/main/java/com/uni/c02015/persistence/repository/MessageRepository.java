package com.uni.c02015.persistence.repository;


import com.uni.c02015.domain.Message;
import com.uni.c02015.domain.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface MessageRepository extends CrudRepository<Message, Integer> {
  
  /**
   * Find a role by ID.
   * @param id Integer
   * @return {@link Message}
   */
  Message findById(Integer id);

  List<Message> findByReceiver(User currentUser);
  
}