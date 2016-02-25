package com.uni.c02015.persistence.repository;

import com.uni.c02015.domain.Message;

import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Integer> {

  /**
   * Find a role by ID.
   * @param id Integer
   * @return {@link Message}
   */
  Message findById(Integer id);
  
}