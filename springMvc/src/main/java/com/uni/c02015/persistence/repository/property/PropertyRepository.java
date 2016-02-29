package com.uni.c02015.persistence.repository.property;

import com.uni.c02015.domain.property.Property;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface PropertyRepository extends CrudRepository<Property, Integer> {

  /**
   * Find a property by ID.
   * @param id Integer
   * @return {@link Property}
   */
  Property findById(Integer id);
}