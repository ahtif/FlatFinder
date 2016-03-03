package com.uni.c02015.persistence.repository.property;

import com.uni.c02015.domain.property.Property;
import com.uni.c02015.domain.property.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface TypeRepository extends CrudRepository<Type, Integer> {

  /**
   * Find a type by ID.
   * @param id Integer
   * @return {@link Type}
   */
  Type findById(Integer id);
}