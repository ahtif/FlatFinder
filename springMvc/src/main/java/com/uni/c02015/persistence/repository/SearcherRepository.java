package com.uni.c02015.persistence.repository;

import com.uni.c02015.domain.Searcher;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface SearcherRepository extends CrudRepository<Searcher, Integer> {

  /**
   * Find a role by ID.
   * @param id Integer
   * @return {@link Searcher}
   */
  Searcher findById(Integer id);
  
}