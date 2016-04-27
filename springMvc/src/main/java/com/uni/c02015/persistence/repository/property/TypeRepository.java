package com.uni.c02015.persistence.repository.property;

import com.uni.c02015.domain.property.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface TypeRepository extends CrudRepository<Type, Integer> {

  Type findById(Integer id);
}