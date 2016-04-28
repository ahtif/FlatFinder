package com.uni.c02015.persistence.repository;

import com.uni.c02015.domain.Landlord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface LandlordRepository extends CrudRepository<Landlord, Integer> {

  Landlord findById(Integer id);
  
  Landlord findByFirstName(String firstName);
}