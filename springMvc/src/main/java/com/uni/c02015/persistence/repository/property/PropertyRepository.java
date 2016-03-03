package com.uni.c02015.persistence.repository.property;

import com.sun.tools.javac.util.List;
import com.uni.c02015.domain.Landlord;
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

  List<Property> findByLandlord(Landlord landlord);
}