package com.uni.c02015.persistence.repository.property;

import com.uni.c02015.domain.property.Type;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Component
public interface TypeRepository extends CrudRepository<Type, Integer> {

  /**
   * Find a type by ID.
   * @param id Integer
   * @return {@link Type}
   */
  Type findById(Integer id);
}