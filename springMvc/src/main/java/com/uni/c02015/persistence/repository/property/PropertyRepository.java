package com.uni.c02015.persistence.repository.property;

import com.uni.c02015.domain.Landlord;
import com.uni.c02015.domain.property.Property;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PropertyRepository extends CrudRepository<Property, Integer> {

  Property findById(Integer id);

  List<Property> findByLandlord(Landlord landlord);
  
  Property findByStreetAndNumber(String street, String number);
 
}